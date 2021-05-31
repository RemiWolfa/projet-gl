package fr.ul.miage.dsw.projetgl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.ul.miage.dsw.projetgl.database.ReservationCollection;
import fr.ul.miage.dsw.projetgl.enumeration.EtatReservation;

public class Reservation {
	
	public int numReservation;//genere lors de la sauvegarde en bd
	public Date dateArrivee;
	public Date dateDepart;
	private List<Commande> commandes;
	public Table table;
	public EtatReservation etatReservation;
	
	public Reservation(Date dateArrivee) {
		this.setEtat(EtatReservation.enAttente);
		this.dateArrivee = dateArrivee;
		this.numReservation = -1;
		this.commandes = new ArrayList<Commande>();
	}
	
	public List<Commande> getCommandes() {
		return this.commandes;
	}
	
	public void setEtat(EtatReservation etatReservation) {
		this.etatReservation = etatReservation;
		if(etatReservation == EtatReservation.terminee)
			this.dateDepart = new Date();
	}
	
	public void ajouterCommande(Commande commande) {
		this.commandes.add(commande);
	}
	
	public boolean save() {
		return ReservationCollection.save(this);
	}

	public boolean updateState() {
		return ReservationCollection.updateState(this);
	}

	public void delete() {
		ReservationCollection.delete(this);
	}
	
	
	

}
