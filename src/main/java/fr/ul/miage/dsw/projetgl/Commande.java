package fr.ul.miage.dsw.projetgl;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class Commande {
	
	public int num;
	public Date date;
	public List<Plat> plats;
	public EtatCommande etatCommande;
	public Utilisateur user;
	public Table table;
	
	//type commande plutot (à la place de boolean)
	public Commande saisirCommande(Plat[] plats, Table table) {
		return null;
		
	}
	 public List<Commande> listerCommandes(){
		 
		return null;
	 }

	 public void passerCommande() {
		 
	 }
	 
	 public void finaliserCommande(Commande cmd) {
		 cmd.etatCommande = EtatCommande.conclue;
	 }
	 
	 public List<Commande> AfficherCommandesPretes() {
		 List<Commande> listCommande = new ArrayList<>();
		 if(this.etatCommande == null) {
			 listCommande.add(null);
		 }
		 return listCommande;
	 }
	 
	 
}
