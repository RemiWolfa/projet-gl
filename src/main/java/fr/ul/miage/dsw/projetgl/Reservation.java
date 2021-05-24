package fr.ul.miage.dsw.projetgl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.ul.miage.dsw.projetgl.database.ReservationCollection;

public class Reservation {
	
	public int numReservation;//genere lors de la sauvegarde
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
	
	public void setEtat(EtatReservation etatReservation) {
		this.etatReservation = etatReservation;
	}
	
	public void ajouterCommande(Commande commande) {
		this.commandes.add(commande);
	}
	
	public boolean save() {
		return ReservationCollection.save(this);
	}
	
	
	@Override
	public String toString() {
		return "Reservation [numReservation=" + numReservation + ", dateArrivee=" + dateArrivee + ", dateDepart="
				+ dateDepart + ", commandes=" + commandes + ", table=" + table + ", etatReservation=" + etatReservation
				+ "]";
	}
	
	
	public List<Commande> getCommandes() {
		return this.commandes;
	}
	
	

}
