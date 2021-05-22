package fr.ul.miage.dsw.projetgl;

import java.util.ArrayList;
import java.util.Date;

import fr.ul.miage.dsw.projetgl.database.DataBase;

public class Main {
	
	public static void main(String[] args) {
		DataBase.connect();
		
		new Table(1).save();
		Serveur serveur = new Serveur("0001");
		serveur.affecterTable(new Table(1));
		System.out.println(""+serveur.save());
		
		Reservation reservation = new Reservation();
		reservation.numReservation = 0;
		reservation.dateArrivee = new Date();
		reservation.table = new Table(1);
		Commande entre = new Commande();
		entre.date = new Date();
		entre.etatCommande = EtatCommande.conclue;
		entre.num = 0;
		
		ArrayList<Plat> plats = new ArrayList<Plat>();
		plats.add(new Plat("niçoise"));
		plats.add(new Plat("frites"));
		entre.plats = plats;
		entre.user = serveur;
		
		reservation.commandes = new ArrayList<Commande>();
		reservation.commandes.add(entre);
		
		reservation.etatReservation = EtatReservation.entree;
		
		reservation.save();
		
		Etage etage = new Etage();
		etage.numEtage = 0;
		etage.addTable(new Table(1));
		etage.save();
		
		Carte carte = new Carte();
		carte.dateCarte = new Date();
		carte.plats = new ArrayList<Plat>();
		carte.plats.add(new Plat("frites"));
		
		Plat pizza = new Plat("pizza");
		pizza.matierePremiere.add(new MatierePremiere("fromage"));
		pizza.save();
		
		carte.plats.add(pizza);
		
		
		carte.save();
		
		new MatierePremiere("fromage").save();
		
		Categorie categorie = new Categorie("viande");
		categorie.add(new Plat("steak"));
		categorie.add(new Categorie("viande blanche"));
		categorie.save();
	}

}
