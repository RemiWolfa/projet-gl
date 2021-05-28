package fr.ul.miage.dsw.projetgl.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;

import fr.ul.miage.dsw.projetgl.Commande;
import fr.ul.miage.dsw.projetgl.Plat;
import fr.ul.miage.dsw.projetgl.Reservation;
import fr.ul.miage.dsw.projetgl.Serveur;
import fr.ul.miage.dsw.projetgl.Table;
import fr.ul.miage.dsw.projetgl.enumeration.EtatCommande;
import fr.ul.miage.dsw.projetgl.enumeration.EtatReservation;

public class ReservationCollection {

	public static MongoCollection<Document> collection;

	public static boolean save(Reservation reservation) {
		if(ReservationCollection.exist(reservation)) //-1 si pas enregistre donc false
			return false;

		Document reservationDocument = new Document();
		reservationDocument.append("Numero", ReservationCollection.getNextNum());
		reservationDocument.append("DateArrivee", reservation.dateArrivee);
		reservationDocument.append("DateDepart", reservation.dateDepart);
		reservationDocument.append("Etat", reservation.etatReservation.toString());
		if(reservation.table != null)
			reservationDocument.append("Table", reservation.table.num);
		if(reservation.getCommandes() != null && reservation.getCommandes().size() > 0)
			reservationDocument.append("Commandes", ReservationCollection.getCommandesArray(reservation.getCommandes()));

		ReservationCollection.collection.insertOne(reservationDocument);

		reservation.numReservation = reservationDocument.getInteger("Numero");
		return true;
	}

	private static int getNextNum() {
		Document d = collection.aggregate(
				Arrays.asList(Aggregates.group(null, 
						Accumulators.max("Max", "$Numero"))
						)).first();
		if(d == null)
			return 0;
		else
			return d.getInteger("Max")+1;
	}

	private static ArrayList<Document> getCommandesArray(List<Commande> commandes) {
		ArrayList<Document> commandeDocuments = new ArrayList<Document>();

		for(Commande commande : commandes) {
			commandeDocuments.add(ReservationCollection.getCommande(commande));
		}
		return commandeDocuments;
	}

	private static Document getCommande(Commande commande) {
		Document commandeDocument = new Document();
		commandeDocument.append("Utilisateur", commande.userId);
		commandeDocument.append("Date", commande.date);
		commandeDocument.append("Etat", commande.etatCommande.toString());
		commandeDocument.append("Plats", PlatCollection.getPlatNames(commande.getPlats()));

		return commandeDocument;
	}

	public static ArrayList<Commande> getWaitingOrders(){
		List<Bson> aggregates = Arrays.asList(//TODO
				Aggregates.unwind("$Commandes"),
				Aggregates.match(new Document("Commandes.Etat", EtatCommande.passee.toString())),
				Aggregates.sort(new Document("Commandes.date", 1))
				);

		ArrayList<Commande> commandes = new ArrayList<Commande>();

		ReservationCollection.collection.aggregate(aggregates).forEach(
				ReservationDoc -> {
					Commande commande = new Commande(ReservationDoc.getInteger("Numero"));

					Document CommandeDoc = (Document) ReservationDoc.get("Commandes");

					commande.date = CommandeDoc.getDate("Date");
					commande.userId = CommandeDoc.getString("Utilisateur");

					List<String> platNames = (List<String>) CommandeDoc.get("Plats");
					commande.setPlats(PlatCollection.getPlatsFromNames(platNames));

					commandes.add(commande);
				}
				);
		return commandes;
	}

	public static List<Reservation> getCurrentReservations(Serveur serveur){
		List<Reservation> reservations = new ArrayList<Reservation>();

		Document docRequest = new Document("Table", new Document("$in", TableCollection.getTableNumbers(serveur.tables)));
		ArrayList<String> notPresentStates = new ArrayList<String>();
		notPresentStates.add(EtatReservation.enAttente.toString());
		notPresentStates.add(EtatReservation.terminee.toString());
		docRequest.append("Etat", new Document("$nin", notPresentStates));


		ReservationCollection.collection.find(docRequest).forEach(
				ReservationDoc -> {
					Reservation reservation = new Reservation(ReservationDoc.getDate("DateArrivee"));
					reservation.numReservation = ReservationDoc.getInteger("Numero");
					reservation.etatReservation = EtatReservation.valueOf(ReservationDoc.getString("Etat"));
					reservation.table = new Table(ReservationDoc.getInteger("Table"));
					reservations.add(reservation);
				});

		return reservations;
	}

	public static boolean exist(Reservation reservation) {
		return ReservationCollection.exist(reservation.numReservation);
	}

	public static boolean exist(int numReservation) {
		return ReservationCollection.collection.countDocuments(new Document("Numero", numReservation)) > 0;
	}

	public static boolean updateState(Commande commande) {
		if(!exist(commande.reservationNum)) 
			return false;

		Document docRequest = new Document("Numero", commande.reservationNum);
		docRequest.append("Commandes.Date", commande.date);

		Document update = new Document("$set", new Document("Commandes.$.Etat", commande.etatCommande.toString()));

		ReservationCollection.collection.updateOne(docRequest, update);
		return true;
	}

	public static boolean saveOrder(Commande commande) {

		ReservationCollection.decrementStock(commande);

		Document doc = ReservationCollection.getCommande(commande);
		Document requestDoc = new Document("Numero", commande.reservationNum);

		Document update = new Document("$push", new Document("Commandes", doc));
		ReservationCollection.collection.updateOne(requestDoc, update);
		return false;
	}

	private static void decrementStock(Commande commande) {
		for(Plat plat : commande.getPlats()) {
			ArrayList<String> mps = PlatCollection.getMatierePremieres(plat);
			for(String nomMP : mps) {
				MatierePremiereCollection.decrement(nomMP,1);
			}
		}
	}


	public static HashMap<String,Double> bestProfitability(){

		List<Bson> list = Arrays.asList(
				Aggregates.unwind("$Commandes"),
				Aggregates.match(new Document("Commandes.Etat", "conclue")),
				Aggregates.unwind("$Commandes.Plats"),
				Aggregates.group("$Commandes.Plats", Accumulators.sum("nbPlats",1)),
				Aggregates.lookup("Plats", "_id", "Nom" , "Plat")
				);


		HashMap<String,Double> map = new HashMap<String,Double>();
		ReservationCollection.collection.aggregate(list).forEach(
				result-> {
					Document document = (Document) result;
					ArrayList<Document> listPlats = (ArrayList<Document>) document.get("Plat");
					if(listPlats != null && listPlats.size() > 0) {
						Document platDocument = listPlats.get(0);
						int nb = document.getInteger("nbPlats");
						Double prix = platDocument.getDouble("Prix");
						map.put(platDocument.getString("Nom"), nb*prix);
					}

				});

		
		return (HashMap<String,Double>)sort(map);
	}

	public static <K,V extends Comparable<? super V>> 
	List<Entry<K, V>> sort(Map<K,V> map) {

		List<Entry<K,V>> sortedEntries = new ArrayList<Entry<K,V>>(map.entrySet());

		Collections.sort(sortedEntries, 
				new Comparator<Entry<K,V>>() {
			@Override
			public int compare(Entry<K,V> e1, Entry<K,V> e2) {
				return e2.getValue().compareTo(e1.getValue());
			}
		}
				);

		return sortedEntries;
	}

	public static Double averageReservationTime() {
		FindIterable<Document> res = ReservationCollection.collection.find();

		Date dateArrivee = new Date();
		Date dateDepart = new Date();
		ArrayList<Long> listDate = new ArrayList<Long>();
		long diff;
		TimeUnit timeUnit = TimeUnit.MINUTES;


		for(Document doc : res) {
			dateArrivee = doc.getDate("dateArrivee");
			dateDepart = doc.getDate("dateDepart");
			diff = dateDepart.getTime() - dateArrivee.getTime();
			listDate.add(timeUnit.convert(diff,TimeUnit.MILLISECONDS));
		}

		Double average = listDate.stream().mapToDouble(num -> Double.parseDouble(num.toString())).average().getAsDouble();
		return average;

	}

}