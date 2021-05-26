package fr.ul.miage.dsw.projetgl;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import fr.ul.miage.dsw.projetgl.database.MatierePremiereCollection;
import fr.ul.miage.dsw.projetgl.database.PlatCollection;

public class Plat extends Item{


	public static MongoCollection<Document> collection;
	public String nom;
	public Categorie categorie;
	public List<MatierePremiere> matierePremieres;


	public Plat(String nom){
		this();
		this.nom = nom;
	}
	public Plat() {
		this.matierePremieres = new ArrayList<MatierePremiere>();
	}

	//retourne faux si la matiere n'existait pas (a été créée)
	public boolean ajouterMatierePremiere(MatierePremiere matierePremiere) {
		boolean b = !MatierePremiereCollection.exist(matierePremiere);
		MatierePremiereCollection.save(matierePremiere);//si elle n'est pas en bd
		this.matierePremieres.add(matierePremiere);
		return b;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public static Plat definirPlat(List<MatierePremiere> mp, String nom, Categorie categorie) {
		Plat plat = new Plat(nom);
		plat.categorie = categorie;
		plat.matierePremieres = mp;
		return plat;
	}

	public boolean save() {
		return PlatCollection.save(this);
	}

	public static Plat trouverPlat(String nom) {
		 Plat plat = new Plat();
		 Document doc =Plat.collection.find(new Document ("Nom", nom)).first();
		 plat.nom = nom;
		// plat.categorie = doc.get("Catégorie")
		 return plat;
	}
	
	public static Plat trouverPlat(Categorie categorie) {
		 return null;
	}


	public static String listePlats(){
		String str = "";
		FindIterable<Document> doc =Plat.collection.find();
		for (Document d : doc) {
			str += d.get("Nom")+"\n";
		}
		return str;
	}
	
	public static Boolean checkNomPlat(String nom) throws IncorrectParam {
		
			if(!Plat.collection.find(new Document ("Nom", nom)).first().equals(null)) {
				return true;
			}
	
			throw new IncorrectParam("Le nom de plat n'existe pas : " + nom +" \n Veuillez retaper le nom.");
		
	}

}
