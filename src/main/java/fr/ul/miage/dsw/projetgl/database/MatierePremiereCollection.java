package fr.ul.miage.dsw.projetgl.database;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import fr.ul.miage.dsw.projetgl.MatierePremiere;

public class MatierePremiereCollection {
	
	public static final String NOM_ATTRIBUT = "Nom";
	public static final String ENPOIDS_ATTRIBUT = "EnPoids";
	public static final String STOCK_ATTRIBUT = "Stock";

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


	public static ArrayList<String> getMatierePremiereNames(List<MatierePremiere> matierePremiere) {
		ArrayList<String> names = new ArrayList<String>();
		for(MatierePremiere mp : matierePremiere) {
			names.add(mp.nom);
		}
		return names;
	}

	public static void decrement(String nomMP, int quantity) {
		Document docRequest=new Document (NOM_ATTRIBUT, nomMP);
		Document update = new Document("$inc", new Document(STOCK_ATTRIBUT, -quantity));
		MatierePremiereCollection.collection.updateOne(docRequest, update);
	}

}
