package fr.ul.miage.dsw.projetgl;

import java.util.List;

import fr.ul.miage.dsw.projetgl.database.PlatCollection;
import fr.ul.miage.dsw.projetgl.database.ReservationCollection;
import fr.ul.miage.dsw.projetgl.enumeration.EtatCommande;

import java.util.ArrayList;
import java.util.Date;

public class Commande {

	public Date date;
	private List<Plat> plats;
	public EtatCommande etatCommande;
	public boolean isPriority = false;
	
	public String userId;
	
	public int reservationNum;

	public Commande(int reservationNum) {
		this.reservationNum = reservationNum;
		this.plats = new ArrayList<Plat>();
		this.date = new Date();
		this.etatCommande = EtatCommande.passee;
	}
	
	public Commande(String userId, int reservationNum) {
		this(reservationNum);
		this.userId = userId;
	}
	
	
	public List<Plat> getPlats(){
		return this.plats;
	}

	public void setPlats(List<Plat> plats) {
		this.plats = plats;
	}
	
	public boolean ajouterPlat(Plat plat) {
		if(PlatCollection.exist(plat)) {
			this.plats.add(plat);
			return true;
		}
		return false;
	}
	
	public boolean setToFinish(Commande cmd) {
		cmd.etatCommande = EtatCommande.conclue;
		return ReservationCollection.updateState(this);
	}

	public boolean setToReady() {
		if(etatCommande == EtatCommande.prete)
			return false;
		this.etatCommande = EtatCommande.prete;
		return ReservationCollection.updateState(this);
	}

	public boolean save() {
		return ReservationCollection.saveOrder(this);
	}


}
