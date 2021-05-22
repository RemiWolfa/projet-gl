package fr.ul.miage.dsw.projetgl.database;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import fr.ul.miage.dsw.projetgl.Commande;
import fr.ul.miage.dsw.projetgl.Plat;
import fr.ul.miage.dsw.projetgl.Reservation;
import fr.ul.miage.dsw.projetgl.Table;

public class ReservationCollection {
	
	public static MongoCollection<Document> collection;
	
	public static boolean save(Reservation reservation) {
		if(ReservationCollection.exist(reservation))
			return ReservationCollection.update(reservation);
		
		Document reservationDocument = new Document();
		reservationDocument.append("Numero", reservation.numReservation);
		reservationDocument.append("DateArrivee", reservation.dateArrivee);
		reservationDocument.append("DateDepart", reservation.dateDepart);
		if(reservation.table != null)
			reservationDocument.append("Table", reservation.table.num);
		if(reservation.commandes != null && reservation.commandes.size() > 0)
			reservationDocument.append("Commandes", ReservationCollection.getCommandesArray(reservation.commandes));
		
		ReservationCollection.collection.insertOne(reservationDocument);
		return true;
	}
	
	private static ArrayList<Document> getCommandesArray(List<Commande> commandes) {
		ArrayList<Document> commandeDocuments = new ArrayList<Document>();
		
		for(Commande commande : commandes) {
			Document commandeDocument = new Document();
			commandeDocument.append("Numero", commande.num);
			commandeDocument.append("Utilisateur", commande.user.identifiant);
			commandeDocument.append("Date", commande.date);
			commandeDocument.append("Etat", commande.etatCommande.toString());
			commandeDocument.append("Plats", PlatCollection.getPlatNames(commande.plats));
		
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
