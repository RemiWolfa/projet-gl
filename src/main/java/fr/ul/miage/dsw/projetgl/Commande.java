package fr.ul.miage.dsw.projetgl;

import java.util.List;

import fr.ul.miage.dsw.projetgl.database.PlatCollection;
import fr.ul.miage.dsw.projetgl.enumeration.EtatCommande;

import java.util.ArrayList;
import java.util.Date;

public class Commande {

	public Date date;
	private List<Plat> plats;
	public EtatCommande etatCommande;
	
	public Utilisateur user;
	
	public Reservation reservation;

	public Commande(Reservation reservation) {
		this.reservation = reservation;
		this.plats = new ArrayList<Plat>();
		this.date = new Date();
	}
	
	public Commande(Utilisateur user, Reservation reservation) {
		this(reservation);
		this.user = user;
	}

	public boolean ajouterPlat(Plat plat) {
		if(PlatCollection.exist(plat)) {
			this.plats.add(plat);
			return true;
		}
		return false;
	}
	
	public List<Plat> getPlats(){
		return this.plats;
	}

	public void finaliserCommande(Commande cmd) {
		cmd.etatCommande = EtatCommande.conclue;
	}

	public static List<Commande> AfficherCommandesPretes() {
		return null;
	}


}
