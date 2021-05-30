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
import fr.ul.miage.dsw.projetgl.MatierePremiere;
import fr.ul.miage.dsw.projetgl.Plat;
import fr.ul.miage.dsw.projetgl.Reservation;
import fr.ul.miage.dsw.projetgl.Serveur;
import fr.ul.miage.dsw.projetgl.Table;
import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.enumeration.EtatCommande;
import fr.ul.miage.dsw.projetgl.enumeration.EtatReservation;

public class ReservationCollection {

	public static final String NUMERO_ATTRIBUT = "Numero";
	public static final String DATE_ARRIVEE_ATTRIBUT = "DateArrivee";
	public static final String DATE_DEPART_ATTRIBUT = "DateDepart";
	public static final String ETAT_ATTRIBUT = "Etat";
	public static final String TABLE_ATTRIBUT = "Table";
	public static final String COMMANDES_ATTRIBUT = "Commandes";

	public static final String COMMANDES_USERID_ATTRIBUT = "Utilisateur";
	public static final String COMMANDES_DATE_ATTRIBUT = "Date";
	public static final String COMMANDES_ETAT_ATTRIBUT = "Etat";
	public static final String COMMANDES_PLATS_ATTRIBUT = "Plats";


	public static MongoCollection<Document> collection;

	public static boolean save(Reservation reservation) {
		if(ReservationCollection.exist(reservation)) //-1 si pas enregistre donc false
			return false;

		Document reservationDocument = new Document();
		reservationDocument.append(NUMERO_ATTRIBUT, ReservationCollection.getNextNum());
		reservationDocument.append(DATE_ARRIVEE_ATTRIBUT, reservation.dateArrivee);
		reservationDocument.append(DATE_DEPART_ATTRIBUT, reservation.dateDepart);
		reservationDocument.append(ETAT_ATTRIBUT, reservation.etatReservation.toString());
		if(reservation.table != null)
			reservationDocument.append(TABLE_ATTRIBUT, reservation.table.num);
		if(reservation.getCommandes() != null && reservation.getCommandes().size() > 0)
			reservationDocument.append(COMMANDES_ATTRIBUT, ReservationCollection.getCommandesArray(reservation.getCommandes()));

		ReservationCollection.collection.insertOne(reservationDocument);

		reservation.numReservation = reservationDocument.getInteger(NUMERO_ATTRIBUT);
		return true;
	}

	private static int getNextNum() {
		Document d = collection.aggregate(
				Arrays.asList(Aggregates.group(null, 
						Accumulators.max("Max", "$"+NUMERO_ATTRIBUT))
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
		commandeDocument.append(COMMANDES_USERID_ATTRIBUT, commande.userId);
		commandeDocument.append(COMMANDES_DATE_ATTRIBUT, commande.date);
		commandeDocument.append(COMMANDES_ETAT_ATTRIBUT, commande.etatCommande.toString());
		commandeDocument.append(COMMANDES_PLATS_ATTRIBUT, PlatCollection.getPlatNames(commande.getPlats()));

		return commandeDocument;
	}

	public static ArrayList<Commande> getWaitingOrders(){
		List<Bson> aggregates = Arrays.asList(//TODO
				Aggregates.unwind("$"+COMMANDES_ATTRIBUT),
				Aggregates.match(new Document(COMMANDES_ATTRIBUT+"."+COMMANDES_ETAT_ATTRIBUT, EtatCommande.passee.toString())),
				Aggregates.sort(new Document(COMMANDES_ATTRIBUT+"."+COMMANDES_DATE_ATTRIBUT, 1))
				);

		ArrayList<Commande> commandes = new ArrayList<Commande>();

		ReservationCollection.collection.aggregate(aggregates).forEach(
				ReservationDoc -> {
					Commande commande = new Commande(ReservationDoc.getInteger(NUMERO_ATTRIBUT));

					Document CommandeDoc = (Document) ReservationDoc.get(COMMANDES_ATTRIBUT);

					commande.date = CommandeDoc.getDate(COMMANDES_DATE_ATTRIBUT);
					commande.userId = CommandeDoc.getString(COMMANDES_USERID_ATTRIBUT);

					List<String> platNames = (List<String>) CommandeDoc.get(COMMANDES_PLATS_ATTRIBUT);
					commande.setPlats(PlatCollection.getPlatsFromNames(platNames));

					commandes.add(commande);
				}
				);
		return commandes;
	}

	public static List<Reservation> getCurrentReservations(Serveur serveur){
		List<Reservation> reservations = new ArrayList<Reservation>();

		Document docRequest = new Document(TABLE_ATTRIBUT, 
				new Document("$in", TableCollection.getTableNumbers(serveur.tables)));

		ArrayList<String> notPresentStates = new ArrayList<String>();
		notPresentStates.add(EtatReservation.enAttente.toString());
		notPresentStates.add(EtatReservation.terminee.toString());
		docRequest.append(ETAT_ATTRIBUT, new Document("$nin", notPresentStates));


		ReservationCollection.collection.find(docRequest).forEach(
				ReservationDoc -> {
					Reservation reservation = new Reservation(ReservationDoc.getDate(DATE_ARRIVEE_ATTRIBUT));
					reservation.numReservation = ReservationDoc.getInteger(NUMERO_ATTRIBUT);
					reservation.etatReservation = EtatReservation.valueOf(ReservationDoc.getString(ETAT_ATTRIBUT));
					reservation.table = new Table(ReservationDoc.getInteger(TABLE_ATTRIBUT));
					reservations.add(reservation);
				});

		return reservations;
	}

	public static boolean exist(Reservation reservation) {
		return ReservationCollection.exist(reservation.numReservation);
	}

	public static boolean exist(int numReservation) {
		return ReservationCollection.collection.countDocuments(new Document(NUMERO_ATTRIBUT, numReservation)) > 0;
	}

	public static boolean updateState(Commande commande) {
		if(!exist(commande.reservationNum)) 
			return false;

		Document docRequest = new Document(NUMERO_ATTRIBUT, commande.reservationNum);
		docRequest.append(COMMANDES_ATTRIBUT+"."+COMMANDES_DATE_ATTRIBUT, commande.date);

		Document update = new Document("$set", new Document(COMMANDES_ATTRIBUT+".$."+COMMANDES_ETAT_ATTRIBUT, commande.etatCommande.toString()));

		ReservationCollection.collection.updateOne(docRequest, update);
		return true;
	}

	public static boolean saveOrder(Commande commande) {

		ReservationCollection.decrementStock(commande);

		Document doc = ReservationCollection.getCommande(commande);
		Document requestDoc = new Document(NUMERO_ATTRIBUT, commande.reservationNum);

		Document update = new Document("$push", new Document(COMMANDES_ATTRIBUT, doc));
		ReservationCollection.collection.updateOne(requestDoc, update);
		return false;
	}

	private static void decrementStock(Commande commande) {
		for(Plat plat : commande.getPlats()) {
			HashMap<String, Integer> mps = PlatCollection.getMatierePremieres(plat);
			for(String nomMP : mps.keySet()) {
				MatierePremiereCollection.decrement(nomMP,mps.get(nomMP));
			}
		}
	}


	public static HashMap<String,Double> bestProfitability(){

		List<Bson> list = Arrays.asList(
				Aggregates.unwind("$"+COMMANDES_ATTRIBUT),
				Aggregates.match(new Document(COMMANDES_ATTRIBUT+"."+COMMANDES_ETAT_ATTRIBUT, "conclue")),
				Aggregates.unwind("$"+COMMANDES_ATTRIBUT+"."+COMMANDES_PLATS_ATTRIBUT),
				Aggregates.group("$"+COMMANDES_ATTRIBUT+"."+COMMANDES_PLATS_ATTRIBUT
						, Accumulators.sum("nbPlats",1)),
				Aggregates.lookup("Plats", "_id", PlatCollection.NOM_ATTRIBUT , "Plat")
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

		return Tools.sortHashByValue(map);
	}



	public static Double averageReservationTime() {
		FindIterable<Document> res = ReservationCollection.collection.find();

		Date dateArrivee = new Date();
		Date dateDepart = new Date();
		ArrayList<Long> listDate = new ArrayList<Long>();
		long diff;
		TimeUnit timeUnit = TimeUnit.MINUTES;


		for(Document doc : res) {
			dateArrivee = doc.getDate(DATE_ARRIVEE_ATTRIBUT);
			dateDepart = doc.getDate(DATE_DEPART_ATTRIBUT);
			if(dateArrivee != null && dateDepart != null) {
				diff = dateDepart.getTime() - dateArrivee.getTime();
				listDate.add(timeUnit.convert(diff,TimeUnit.MILLISECONDS));
			}
		}

		if(listDate.size() == 0)
			return 0.0;

		Double average = listDate.stream().mapToDouble(num -> Double.parseDouble(num.toString())).average().getAsDouble();
		return average;

	}

}