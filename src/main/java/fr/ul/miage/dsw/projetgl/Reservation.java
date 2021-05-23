package fr.ul.miage.dsw.projetgl;

import java.util.Date;
import java.util.List;

import fr.ul.miage.dsw.projetgl.database.ReservationCollection;

public class Reservation {
	
	public int numReservation;
	public Date dateArrivee;
	public Date dateDepart;
	public List<Commande> commandes;
	public Table table;
	public EtatReservation etatReservation;
	
	public boolean save() {
		return ReservationCollection.save(this);
	}
	
	
	@Override
	public String toString() {
		return "Reservation [numReservation=" + numReservation + ", dateArrivee=" + dateArrivee + ", dateDepart="
				+ dateDepart + ", commandes=" + commandes + ", table=" + table + ", etatReservation=" + etatReservation
				+ "]";
	}
	
	
	

}
