package fr.ul.miage.dsw.projetgl.database;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.OptionalDouble;
import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;

import fr.ul.miage.dsw.projetgl.Commande;
import fr.ul.miage.dsw.projetgl.Plat;
import fr.ul.miage.dsw.projetgl.Reservation;
import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.Utilisateur;
import fr.ul.miage.dsw.projetgl.enumeration.EtatCommande;

public class ReservationCollection {
	
	public static MongoCollection<Document> collection;
	
	public static boolean save(Reservation reservation) {
		if(ReservationCollection.exist(reservation)) //-1 si pas enregistre donc false
			return ReservationCollection.update(reservation);
		
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
			Document commandeDocument = new Document();
			commandeDocument.append("Utilisateur", commande.userId);
			commandeDocument.append("Date", commande.date);
			commandeDocument.append("Etat", commande.etatCommande.toString());
			commandeDocument.append("Plats", PlatCollection.getPlatNames(commande.getPlats()));
		
			commandeDocuments.add(commandeDocument);
		}
		return commandeDocuments;
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

	public static boolean exist(Reservation reservation) {
		return ReservationCollection.exist(reservation.numReservation);
	}
	
	public static boolean exist(int numReservation) {
		return ReservationCollection.collection.countDocuments(new Document("Numero", numReservation)) > 0;
	}
	
	public static boolean update(Reservation reservation) {
		//TODO
		return true;
	}
	
	public static Date meanReservation() {
		FindIterable<Document> res = ReservationCollection.collection.find();
		
		Date dateArrivee = new Date();
		Date dateDepart = new Date();
		ArrayList<Long> listDate = new ArrayList<Long>();
		long diff;
		TimeUnit timeUnit = TimeUnit.MINUTES;
		
		
		for(Document doc : res) {
			dateArrivee = doc.getDate("dateArrivee");
			dateDepart = doc.getDate("dateDepart");
			diff = dateArrivee.getTime() - dateDepart.getTime();
		    listDate.add(timeUnit.convert(diff,TimeUnit.MILLISECONDS));
		}
		Double average = listDate.stream().mapToDouble(num -> Double.parseDouble(num.toString())).average().getAsDouble();
		int hours = (average % 60);
		
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
	

}
