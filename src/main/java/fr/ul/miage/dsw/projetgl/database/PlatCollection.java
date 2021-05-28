package fr.ul.miage.dsw.projetgl.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;

import fr.ul.miage.dsw.projetgl.Commande;
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
		return PlatCollection.collection.countDocuments(new Document("Nom", plat.nom)) > 0;
	}

	public static List<String> getPlatNames(List<Plat> plats) {
		ArrayList<String> names = new ArrayList<String>();
		for(Plat plat : plats) {
			names.add(plat.nom);
		}
		return names;
	}

	public static void bestProfitability(){

				List list = Arrays.asList(
						Aggregates.unwind("$Commandes"),
						Aggregates.match(new Document("$Commandes.etatCommande", "conclue")),
						Aggregates.unwind("$Commandes.plats"),
						Aggregates.group("$Plats", Accumulators.sum("nbPlats",1))
						);
				list.forEach(e->System.out.println(e.toString()));
				
}


			

				public static ArrayList<Plat> getPlatsByName(String name) {
					ArrayList<Plat> plats = new ArrayList<Plat>();

					PlatCollection.collection.find(new Document("Nom", new BasicDBObject("$regex", ".*"+name+".*"))).forEach(
							PlatDocument -> {
								plats.add(new Plat(PlatDocument.getString("Nom")));
							}
							);
					return plats;
				}


				public static List<Plat> getPlatsFromNames(List<String> platNames) {
					ArrayList<Plat> plats = new ArrayList<Plat>();
					if(platNames == null || platNames.size() == 0)
						return plats;

					PlatCollection.collection.find(new Document("Nom", new Document("$in", platNames))).forEach(
							PlatDocument -> {
								plats.add(new Plat(PlatDocument.getString("Nom")));
								//TODO ajouter matieresPremiere
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

				 }
