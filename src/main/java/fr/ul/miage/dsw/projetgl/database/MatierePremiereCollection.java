package fr.ul.miage.dsw.projetgl.database;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

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


	public static ArrayList<String> getMatierePremiereNames(List<MatierePremiere> matierePremiere) {
		ArrayList<String> names = new ArrayList<String>();
		for(MatierePremiere mp : matierePremiere) {
			names.add(mp.nom);
		}
		return names;
	}

}
