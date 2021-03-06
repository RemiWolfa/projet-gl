package fr.ul.miage.dsw.projetgl;

import java.util.HashMap;

import fr.ul.miage.dsw.projetgl.database.MatierePremiereCollection;
import fr.ul.miage.dsw.projetgl.database.PlatCollection;

public class Plat{


	public String nom;
	public HashMap<MatierePremiere, Integer> matierePremieres;
	public Double prix;


	public Plat(String nom, Double prix){
		this();
		this.nom = nom;
		this.prix = prix;
	}
	public Plat() {
		this.matierePremieres = new HashMap<MatierePremiere, Integer>();
	}

	//retourne faux si la matiere n'existait pas (a été créée)
	public boolean ajouterMatierePremiere(MatierePremiere matierePremiere, int quantity) {
		if(matierePremieres.containsKey(matierePremiere)) 
				return false;
		boolean b = MatierePremiereCollection.exist(matierePremiere);
		if(!b) 		
				MatierePremiereCollection.save(matierePremiere);
		this.matierePremieres.put(matierePremiere, quantity);
		return b;
	}


	public boolean testStock(HashMap<MatierePremiere, Integer> fromCommande) {
		for(MatierePremiere mp : this.matierePremieres.keySet()) {
			int quantity = this.matierePremieres.get(mp);
			int min = 1;
			if(fromCommande.containsKey(mp))
				min+=fromCommande.get(mp);
			System.out.println("mp:"+mp.nom);
			if(MatierePremiereCollection.getStock(mp.nom)-quantity <= min)
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

	public static Boolean exist(String nom, Double prix)  {
		return PlatCollection.exist(new Plat(nom, prix));
	}

}
