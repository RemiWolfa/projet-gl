package fr.ul.miage.dsw.projetgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.ul.miage.dsw.projetgl.database.MatierePremiereCollection;
import fr.ul.miage.dsw.projetgl.database.PlatCollection;

public class Plat{


	public String nom;
	public List<MatierePremiere> matierePremieres;
	public Double prix;


	public Plat(String nom, Double prix){
		this();
		this.nom = nom;
		this.prix = prix;
	}
	public Plat() {
		this.matierePremieres = new ArrayList<MatierePremiere>();
	}

	//retourne faux si la matiere n'existait pas (a été créée)
	public boolean ajouterMatierePremiere(MatierePremiere matierePremiere) {
		boolean b = !MatierePremiereCollection.exist(matierePremiere);
		MatierePremiereCollection.save(matierePremiere);
		this.matierePremieres.add(matierePremiere);
		return b;
	}


	public boolean testStock(HashMap<MatierePremiere, Integer> fromCommande) {
		for(MatierePremiere mp : this.matierePremieres) {
			int min = 1;
			if(fromCommande.containsKey(mp))
				min+=fromCommande.get(mp);
			System.out.println("mp:"+mp.nom);
			if(MatierePremiereCollection.getStock(mp.nom) < min)
				return false;
		}
		return true;
	}

	public boolean equals(Object o) {
		if(!(o instanceof Plat))
			return false;

		Plat plat = (Plat)o;
		return plat.nom.equalsIgnoreCase(this.nom);

	}

	public boolean save() {
		return PlatCollection.save(this);
	}

	public static Plat trouverPlat(String nom) {
		return PlatCollection.getPlatByName(nom);
	}


	public static Plat trouverPlat(Categorie categorie) {
		return null;//TODO
	}

	public static Boolean exist(String nom, Double prix)  {
		return PlatCollection.exist(new Plat(nom, prix));
	}

}
