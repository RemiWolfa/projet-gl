package fr.ul.miage.dsw.projetgl.database;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import fr.ul.miage.dsw.projetgl.Categorie;
import fr.ul.miage.dsw.projetgl.Item;
import fr.ul.miage.dsw.projetgl.MatierePremiere;
import fr.ul.miage.dsw.projetgl.Plat;

public class CategorieCollection {
	
	public static final String NOM_ATTRIBUT = "Nom";
	public static final String PLATS_ATTRIBUT = "Plats";
	public static final String CATEGORIES_ATTRIBUT = "Categories";
	
	public static MongoCollection<Document> collection;
	
	public static boolean save(Categorie categorie) {
		if(CategorieCollection.exist(categorie))
			return false;

		Document categorieDocument = new Document();
		categorieDocument.append(NOM_ATTRIBUT, categorie.nom);
		CategorieCollection.putChildrens(categorieDocument, categorie);
		

		CategorieCollection.collection.insertOne(categorieDocument);
		return true;
	}
	
	public static void putChildrens(Document categorieDocument, Categorie categorie) {
		ArrayList<String> categorieNames = new ArrayList<String>();
		ArrayList<String> platNames = new ArrayList<String>();
		
		for(Item item : categorie.categories) {
			if(item instanceof Plat) {
				platNames.add(((Plat) item).nom);
			}else if(item instanceof Categorie) {
				categorieNames.add(((Categorie) item).nom);
			}
		}
		
		if(categorieNames.size() > 0)
			categorieDocument.append(CATEGORIES_ATTRIBUT, categorieNames);
		
		if(platNames.size() > 0)
			categorieDocument.append(PLATS_ATTRIBUT, platNames);
	}


	public static boolean exist(Categorie categorie) {
		return CategorieCollection.collection.countDocuments(new Document(NOM_ATTRIBUT, categorie.nom)) > 0;
	}

}
