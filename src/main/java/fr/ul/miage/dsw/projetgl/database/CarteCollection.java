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
import fr.ul.miage.dsw.projetgl.Plat;
import fr.ul.miage.dsw.projetgl.Tools;

public class CarteCollection {

	public static final String DATE_ATTRIBUT = "Date";
	public static final String PLATS_ATTRIBUT = "Plats";

	public static MongoCollection<Document> collection;

	public static boolean save(Carte carte) {
		if(CarteCollection.exist(carte))
			return CarteCollection.update(carte);

		Document carteDocument = new Document();
		carteDocument.append(DATE_ATTRIBUT, carte.getDate());
		if(carte.getPlats() != null)
			carteDocument.append(PLATS_ATTRIBUT, PlatCollection.getPlatNames(carte.getPlats()));

		CarteCollection.collection.insertOne(carteDocument);
		return true;
	}

	public static boolean update(Carte carte) {
		Document update = new Document("$set", new Document(PLATS_ATTRIBUT, PlatCollection.getPlatNames(carte.getPlats())));
		Document docRequest = new Document(DATE_ATTRIBUT, carte.getDate());
		CarteCollection.collection.updateOne(docRequest, update);
		return true;
	}


	public static boolean exist(Carte carte) {
		return CarteCollection.collection.countDocuments(new Document(DATE_ATTRIBUT, carte.getDate())) > 0;
	}


	public static ArrayList<Plat> getToDayPlats() {
		List<Bson> aggregates = Arrays.asList(//TODO
				Aggregates.match(Filters.eq(DATE_ATTRIBUT, Tools.skipTime(new Date()))),
				Aggregates.unwind("$"+PLATS_ATTRIBUT),
				Aggregates.lookup(PlatCollection.COLLECTION_NAME, PLATS_ATTRIBUT, "Nom", "Plat")
				);

		ArrayList<Plat> plats = new ArrayList<Plat>();
		
		

		CarteCollection.collection.aggregate(aggregates).forEach(
				AggDocument -> {
					if(AggDocument.containsKey("Plat")) {
						List<Document> documents = (List<Document>) AggDocument.get("Plat");
						if(documents.size() > 0) {
							Plat plat = new Plat(documents.get(0).getString(PlatCollection.NOM_ATTRIBUT), documents.get(0).getDouble(PlatCollection.PRIX_ATTRIBUT) );
							plats.add(plat);
						}
					}
				}
				);
		return plats;
	}

}
