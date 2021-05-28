package fr.ul.miage.dsw.projetgl;

public class Facture {

	public int numFacture;
	public Reservation reservation;
	
	public Facture(int numFacture, Reservation reservation) {
		this.numFacture = numFacture;
		this.reservation = reservation;
	}
	
	
}
