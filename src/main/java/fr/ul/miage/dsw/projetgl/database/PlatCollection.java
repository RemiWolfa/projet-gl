package fr.ul.miage.dsw.projetgl.database;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import fr.ul.miage.dsw.projetgl.Plat;

public class PlatCollection {

	public static MongoCollection<Document> collection;

	public static boolean save(Plat plat) {
		if(PlatCollection.exist(plat))
			return false;

		Document platDocument = new Document();
		platDocument.append("Nom", plat.nom);
		if(plat.matierePremiere != null)
			platDocument.append("Plats", MatierePremiereCollection.getMatierePremiereNames(plat.matierePremiere));
		
		PlatCollection.collection.insertOne(platDocument);
		return true;
	}


	public static boolean exist(Plat plat) {
		return PlatCollection.collection.countDocuments(new Document("Nom", plat.nom)) > 0;
	}
	
	public static List<String> getPlatNames(ArrayList<Plat> plats) {
		ArrayList<String> names = new ArrayList<String>();
		for(Plat plat : plats) {
			names.add(plat.nom);
		}
		return names;
	}

}
