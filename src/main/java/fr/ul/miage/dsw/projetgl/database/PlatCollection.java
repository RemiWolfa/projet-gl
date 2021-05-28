package fr.ul.miage.dsw.projetgl.database;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

import fr.ul.miage.dsw.projetgl.MatierePremiere;
import fr.ul.miage.dsw.projetgl.Plat;

public class PlatCollection {

	public static MongoCollection<Document> collection;

	public static boolean save(Plat plat) {
		if(PlatCollection.exist(plat))
			return false;

		Document platDocument = new Document();
		platDocument.append("Nom", plat.nom);
		if(plat.matierePremieres != null)
			platDocument.append("MatierePremieres", MatierePremiereCollection.getMatierePremiereNames(plat.matierePremieres));

		PlatCollection.collection.insertOne(platDocument);
		return true;
	}


	public static boolean exist(Plat plat) {
		return PlatCollection.exist(plat.nom);
	}

	public static boolean exist(String nom) {
		return PlatCollection.collection.countDocuments(new Document("Nom", nom)) > 0;
	}
	
	public static List<String> getPlatNames(List<Plat> plats) {
		ArrayList<String> names = new ArrayList<String>();
		for(Plat plat : plats) {
			names.add(plat.nom);
		}
		return names;
	}


	public static ArrayList<Plat> getPlatsByName(String name) {
		ArrayList<Plat> plats = new ArrayList<Plat>();

		PlatCollection.collection.find(new Document("Nom", new BasicDBObject("$regex", ".*"+name+".*"))).forEach(
				PlatDocument -> {
					Plat plat = PlatCollection.getPlatFromDocument(PlatDocument);
					plats.add(plat);
				}
				);
		return plats;
	}


	private static Plat getPlatFromDocument(Document document) {
		Plat plat = new Plat(document.getString("Nom"));
		
		ArrayList<String> list = (ArrayList<String>) document.get("MatierePremieres");
		for(String nom : list) {
			plat.ajouterMatierePremiere(new MatierePremiere(nom));
		}
		return plat;
	}


	public static List<Plat> getPlatsFromNames(List<String> platNames) {
		ArrayList<Plat> plats = new ArrayList<Plat>();
		if(platNames == null || platNames.size() == 0)
			return plats;

		PlatCollection.collection.find(new Document("Nom", new Document("$in", platNames))).forEach(
				PlatDocument -> {
					Plat plat = PlatCollection.getPlatFromDocument(PlatDocument);
					plats.add(plat);
				}
				);
		return plats;
	}


	public static Plat getPlatByName(String nom) {
		Document doc = PlatCollection.collection.find(new Document("Nom", nom)).first();
		if(doc == null)
			return null;
		
		return new Plat(doc.getString("Name"));
	}


	public static ArrayList<String> getMatierePremieres(Plat plat) {
		Document doc = PlatCollection.collection.find(new Document("Nom", plat.nom)).first();
		return (ArrayList<String>) doc.get("MatierePremieres");
	}

}