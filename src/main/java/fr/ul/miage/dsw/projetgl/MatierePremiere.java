package fr.ul.miage.dsw.projetgl;

import fr.ul.miage.dsw.projetgl.database.MatierePremiereCollection;

public class MatierePremiere {

	public String nom;
	public Categorie categorie;
	public Boolean enPoids;

	public MatierePremiere(String nom) {
		this.nom = nom;
		// TODO Auto-generated constructor stub
	}
	
	public boolean save() {
		return MatierePremiereCollection.save(this);
	}
}
