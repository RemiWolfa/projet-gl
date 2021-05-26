package fr.ul.miage.dsw.projetgl;


import java.util.ArrayList;
import java.util.Date;

import com.mongodb.internal.connection.Server;

import fr.ul.miage.dsw.projetgl.dashboard.CuisinierDashBoard;
import fr.ul.miage.dsw.projetgl.dashboard.DirecteurDashBoard;
import fr.ul.miage.dsw.projetgl.dashboard.MaitreHotelDashBoard;
import fr.ul.miage.dsw.projetgl.dashboard.ServeurDashBoard;
import fr.ul.miage.dsw.projetgl.database.CarteCollection;
import fr.ul.miage.dsw.projetgl.database.DataBase;
import fr.ul.miage.dsw.projetgl.enumeration.EtatCommande;
import fr.ul.miage.dsw.projetgl.enumeration.EtatReservation;


public class Main {
	
	public static void main(String[] args) {
		DataBase.connect();
		
		new Table(1).save();
		Serveur serveur = new Serveur("0001", "Robert");
		serveur.affecterTable(new Table(1));
		System.out.println("création d'un utilisateur:"+serveur.save());
		
		Connexion connexion = new Connexion();
		connexion.connexion("0001"); 
		
		Reservation reservation = new Reservation(new Date());
		reservation.table = new Table(1);
		Commande entre = new Commande(reservation.numReservation);
		entre.date = new Date();
		entre.etatCommande = EtatCommande.conclue;
		
		System.out.println("ajouter pizza:"+ entre.ajouterPlat(new Plat("pizza")));
		entre.ajouterPlat(new Plat("frites"));
		entre.userId = serveur.identifiant;
		
		reservation.ajouterCommande(entre);
		
		reservation.etatReservation = EtatReservation.entree;
		
		reservation.save();
		
		Etage etage = new Etage();
		etage.numEtage = 0;
		etage.addTable(new Table(1));
		etage.save();
		
		Carte carte = new Carte();
		carte.plats = new ArrayList<Plat>();
		carte.plats.add(new Plat("frites"));
		
		Plat pizza = new Plat("pizza");
		pizza.matierePremieres.add(new MatierePremiere("fromage"));
		pizza.save();
		
		carte.plats.add(pizza);
		
		
		carte.save();
		
		new MatierePremiere("fromage").save();
		
		Categorie categorie = new Categorie("viande");
		categorie.add(new Plat("steak"));
		categorie.add(new Categorie("viande blanche"));
		categorie.save();
		
		if(Utilisateur.connectedUser != null)
			System.out.println("Utilisateur de type:"+Utilisateur.connectedUser.typeUser);
		MaitreHotelDashBoard.readCommand();

	}

	 //public static void main(String[] args) {
	   /*   Scanner scanner = new Scanner( System.in);
	     
	      while(true){
	        System.out.print( "Bonjour ! Que souhaitez vous faire ? \n" );
	        String  line = scanner.nextLine();
	        Commandes cmd = new Commandes();
	        cmd.parser(line);
	      }   
	            
	     }
	  */
	}
