package fr.ul.miage.dsw.projetgl.database;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import fr.ul.miage.dsw.projetgl.Carte;
import fr.ul.miage.dsw.projetgl.Etage;

public class CarteCollection {

	public static MongoCollection<Document> collection;

	public static boolean save(Carte carte) {
		if(CarteCollection.exist(carte))
			return false;

		Document carteDocument = new Document();
		carteDocument.append("Date", carte.dateCarte);
		if(carte.plats != null)
			carteDocument.append("Plats", PlatCollection.getPlatNames(carte.plats));

		CarteCollection.collection.insertOne(carteDocument);
		return true;
	}


	public static boolean exist(Carte carte) {
		return CarteCollection.collection.countDocuments(new Document("Date", carte.dateCarte)) > 0;
	}

}
