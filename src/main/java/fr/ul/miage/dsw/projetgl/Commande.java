package fr.ul.miage.dsw.projetgl;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class Commande {
	
	public int num;
	public Date dateDebut;
	public Date dateFin;
	public Plat[] plats;
	public EtatCommande etatCommande;
	// pk stocker user ici ?
	public Utilisateur user;
	public Table table;
	
	//type commande plutot (à la place de boolean)
	public Commande saisirCommande(Plat[] plats, Table table) {
//		Commande cmd = new Commande();
//		// jr vais ecraser la liste des plats qu'on a d�j� ?
//		cmd.plats = plats;
//		cmd.table = table;
		return null;
		
	}
	 public List<Commande> listerCommandes(){
		 
		return null;
	 }

	 //envoyer()
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
