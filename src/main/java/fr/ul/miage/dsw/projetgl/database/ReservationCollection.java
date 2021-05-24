package fr.ul.miage.dsw.projetgl.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;

import fr.ul.miage.dsw.projetgl.Commande;
import fr.ul.miage.dsw.projetgl.Reservation;

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
			commandeDocument.append("Utilisateur", commande.user.identifiant);
			commandeDocument.append("Date", commande.date);
			commandeDocument.append("Etat", commande.etatCommande.toString());
			commandeDocument.append("Plats", PlatCollection.getPlatNames(commande.getPlats()));
		
			commandeDocuments.add(commandeDocument);
		}
		return commandeDocuments;
	}

	public static boolean exist(Reservation reservation) {
		return ReservationCollection.collection.countDocuments(new Document("Numero", reservation.numReservation)) > 0;
	}
	
	public static boolean update(Reservation reservation) {
		//TODO
		return true;
	}

}
