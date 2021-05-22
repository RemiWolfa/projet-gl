package fr.ul.miage.dsw.projetgl;

import java.util.Date;
import java.util.List;

public class Reservation {
	public int numReservation;
	public Date dateArrivee;
	public Date dateDepart;
	public List<Commande> commandes;
	public Table table;
	public EtatReservation etatReservation;
	public int getNumReservation() {
		return numReservation;
	}
	public void setNumReservation(int numReservation) {
		this.numReservation = numReservation;
	}
	public Date getDateArrivee() {
		return dateArrivee;
	}
	public void setDateArrivee(Date dateArrivee) {
		this.dateArrivee = dateArrivee;
	}
	public Date getDateDepart() {
		return dateDepart;
	}
	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}
	public List<Commande> getCommandes() {
		return commandes;
	}
	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	public EtatReservation getEtatReservation() {
		return etatReservation;
	}
	public void setEtatReservation(EtatReservation etatReservation) {
		this.etatReservation = etatReservation;
	}
	
	
	@Override
	public String toString() {
		return "Reservation [numReservation=" + numReservation + ", dateArrivee=" + dateArrivee + ", dateDepart="
				+ dateDepart + ", commandes=" + commandes + ", table=" + table + ", etatReservation=" + etatReservation
				+ "]";
	}
	
	
	

}
