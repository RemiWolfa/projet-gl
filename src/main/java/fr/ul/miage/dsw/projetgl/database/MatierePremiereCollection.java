package fr.ul.miage.dsw.projetgl.database;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import fr.ul.miage.dsw.projetgl.MatierePremiere;

public class MatierePremiereCollection {

	public static MongoCollection<Document> collection;

	public static boolean save(MatierePremiere mp) {
		if(MatierePremiereCollection.exist(mp))
			return false;

		Document mpDocument = new Document();
		mpDocument.append("Nom", mp.nom);
		mpDocument.append("EnPoid", mp.enPoids);
		if(mp.categorie != null)
			mpDocument.append("Categorie", mp.categorie.nom);
		

		MatierePremiereCollection.collection.insertOne(mpDocument);
		return true;
	}


	public static boolean exist(MatierePremiere mp) {
		return MatierePremiereCollection.collection.countDocuments(new Document("Nom", mp.nom)) > 0;
	}
	
	public static String getStock() {
		FindIterable<Document> doc =MatierePremiereCollection.collection.find();
		
		String res = "";
		
		for(Document d : doc) {
			
			res +="\n -----\n Nom "+d.get("Nom")+"\n";
			res += d.get("EnPoids").equals(false)?"Unités : ":"Poids en kg : "  +d.get("Stock")+"\n";
			
		}
		return res;
	}
	
	public static void setStock(String nom, int quantite) {
		Document doc =MatierePremiereCollection.collection.find(new Document ("Nom", nom)).first();
		
		doc.append("Stock", Integer.parseInt(doc.get("Stock").toString())  +quantite);
		
		
	}


	public static ArrayList<String> getMatierePremiereNames(List<MatierePremiere> matierePremiere) {
		ArrayList<String> names = new ArrayList<String>();
		for(MatierePremiere mp : matierePremiere) {
			names.add(mp.nom);
		}
		return names;
	}

}
