package fr.ul.miage.dsw.projetgl.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;

import fr.ul.miage.dsw.projetgl.Carte;
import fr.ul.miage.dsw.projetgl.Etage;
import fr.ul.miage.dsw.projetgl.Plat;
import fr.ul.miage.dsw.projetgl.Tools;

public class CarteCollection {

	public static MongoCollection<Document> collection;

	public static boolean save(Carte carte) {
		if(CarteCollection.exist(carte))
			return false;

		Document carteDocument = new Document();
		carteDocument.append("Date", carte.getDate());
		if(carte.plats != null)
			carteDocument.append("Plats", PlatCollection.getPlatNames(carte.plats));

		CarteCollection.collection.insertOne(carteDocument);
		return true;
	}


	public static boolean exist(Carte carte) {
		return CarteCollection.collection.countDocuments(new Document("Date", carte.getDate())) > 0;
	}


	public static ArrayList<Plat> getToDayPlats() {
		List<Bson> aggregates = Arrays.asList(//TODO
				Aggregates.match(Filters.eq("Date", Tools.skipTime(new Date()))),
				Aggregates.unwind("$Plats"),
				Aggregates.lookup("Plats", "Plats", "Nom", "Plat")
				);

		ArrayList<Plat> plats = new ArrayList<Plat>();

		CarteCollection.collection.aggregate(aggregates).forEach(
				AggDocument -> {
					if(AggDocument.containsKey("Plat")) {
						List<Document> documents = (List<Document>) AggDocument.get("Plat");
						if(documents.size() > 0) {
							Plat plat = new Plat(documents.get(0).getString("Nom"));
							plats.add(plat);
						}
					}
				}
				);
		return plats;
	}

}
