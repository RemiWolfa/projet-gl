package fr.ul.miage.dsw.projetgl.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import fr.ul.miage.dsw.projetgl.MatierePremiere;

public class MatierePremiereCollection {
	
	public static final String NOM_ATTRIBUT = "Nom";
	public static final String ENPOIDS_ATTRIBUT = "EnPoids";
	public static final String STOCK_ATTRIBUT = "Stock";
	public static final String QUANTITY_ATTRIBUT = "Quantite";

	public static MongoCollection<Document> collection;

	public static boolean save(MatierePremiere mp) {
		return save(mp, 0);
	}
	
	public static boolean save(MatierePremiere mp, int stock) {
		if(MatierePremiereCollection.exist(mp))
			return false;

		Document mpDocument = new Document();
		mpDocument.append(NOM_ATTRIBUT, mp.nom);
		mpDocument.append(ENPOIDS_ATTRIBUT, mp.enPoids);
		mpDocument.append(STOCK_ATTRIBUT, stock);
		

		MatierePremiereCollection.collection.insertOne(mpDocument);
		return true;
	}


	public static boolean exist(MatierePremiere mp) {
		return MatierePremiereCollection.collection.countDocuments(new Document(NOM_ATTRIBUT, mp.nom)) > 0;
	}
	
	public static int getStock(String nom) {
		Document doc =MatierePremiereCollection.collection.find(new Document(NOM_ATTRIBUT, nom)).first();
		return doc.getInteger(STOCK_ATTRIBUT);
	}
	
	public static boolean setStock(String nom, int quantite) {
		Document docRequest=new Document (NOM_ATTRIBUT, nom);
		Document update = new Document("$set", new Document(STOCK_ATTRIBUT, quantite));
		MatierePremiereCollection.collection.updateOne(docRequest, update);
		return true;
	}
	
	public static ArrayList<MatierePremiere> getMatieresPremieres(){
		ArrayList<MatierePremiere> list =new ArrayList<MatierePremiere>();
		MatierePremiereCollection.collection.find().forEach(MatDoc -> {
			MatierePremiere mp= new MatierePremiere(MatDoc.getString(NOM_ATTRIBUT));
			mp.enPoids=MatDoc.getBoolean(ENPOIDS_ATTRIBUT);
			list.add(mp);
		});
		return list;
	}


	public static ArrayList<Document> getMatierePremiereNamesAndQuantiy(HashMap<MatierePremiere, Integer> matierePremieres) {
		ArrayList<Document> docs = new ArrayList<Document>();
		for(MatierePremiere mp : matierePremieres.keySet()) {
			int quantity = matierePremieres.get(mp);
			Document doc = new Document();
			doc.append(NOM_ATTRIBUT, mp.nom);
			doc.append(QUANTITY_ATTRIBUT, quantity);
			docs.add(doc);
		}
		return docs;
	}

	public static void decrement(String nomMP, int quantity) {
		Document docRequest=new Document (NOM_ATTRIBUT, nomMP);
		Document update = new Document("$inc", new Document(STOCK_ATTRIBUT, -quantity));
		MatierePremiereCollection.collection.updateOne(docRequest, update);
	}

	public static HashMap<String, Integer> getMatiereNamesAndQuantityFromDocs(List<Document> docs) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		if(docs == null)
			return map;
		
		for(Document doc : docs) {
			String name = doc.getString(NOM_ATTRIBUT);
			int quantity = doc.getInteger(QUANTITY_ATTRIBUT);
			map.put(name, quantity);
		}
		return map;
	}

}
