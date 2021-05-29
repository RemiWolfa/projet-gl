package fr.ul.miage.dsw.projetgl.database;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import fr.ul.miage.dsw.projetgl.Categorie;
import fr.ul.miage.dsw.projetgl.MatierePremiere;
import fr.ul.miage.dsw.projetgl.Plat;

public class CategorieCollection {

	public static final String NOM_ATTRIBUT = "Nom";
	public static final String PLATS_ATTRIBUT = "Plats";

	public static MongoCollection<Document> collection;

	public static boolean save(Categorie categorie) {
		if(CategorieCollection.exist(categorie))
			return CategorieCollection.update(categorie);

		Document categorieDocument = new Document();
		categorieDocument.append(NOM_ATTRIBUT, categorie.nom);
		CategorieCollection.putChildrens(categorieDocument, categorie);


		CategorieCollection.collection.insertOne(categorieDocument);
		return true;
	}

	public static boolean update(Categorie categorie) {
		Document docRequest = new Document(NOM_ATTRIBUT, categorie.nom);
		Document doc = new Document();
		CategorieCollection.putChildrens(doc, categorie);
		Document update = new Document("$set",doc);

		CategorieCollection.collection.updateOne(docRequest, update);
		return true;
	}

	public static void putChildrens(Document categorieDocument, Categorie categorie) {
		ArrayList<String> platNames = new ArrayList<String>();

		for(Plat plat : categorie.plats) {
			platNames.add(plat.nom);
		}

		if(platNames.size() > 0)
			categorieDocument.append(PLATS_ATTRIBUT, platNames);
	}


	public static ArrayList<Categorie> getAllCategories(){
		ArrayList<Categorie> categories = new ArrayList<Categorie>();
		CategorieCollection.collection.find().forEach(
				categorieDocument -> {
					Categorie categorie = new Categorie(categorieDocument.getString(NOM_ATTRIBUT));
					categorie.setPlats(PlatCollection.getPlatsFromNames((List<String>) categorieDocument.get(PLATS_ATTRIBUT)));
					categories.add(categorie);
				}
				);
		return categories;
	}




	public static boolean exist(Categorie categorie) {
		return CategorieCollection.exist(categorie.nom);
	}

	public static boolean exist(String categorie) {
		return CategorieCollection.collection.countDocuments(new Document(NOM_ATTRIBUT, categorie)) > 0;
	}

	public static Categorie getCategorie(String categorieName) {
		Document categorieDocument = CategorieCollection.collection.find(new Document(NOM_ATTRIBUT, categorieName)).first();
		Categorie categorie = new Categorie(categorieDocument.getString(NOM_ATTRIBUT));
		categorie.setPlats(PlatCollection.getPlatsFromNames((List<String>) categorieDocument.get(PLATS_ATTRIBUT)));
		return categorie;
	}

}
