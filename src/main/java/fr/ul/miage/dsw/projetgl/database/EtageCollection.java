package fr.ul.miage.dsw.projetgl.database;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import fr.ul.miage.dsw.projetgl.Etage;

public class EtageCollection {

	public static MongoCollection<Document> collection;

	public static boolean save(Etage etage) {
		if(EtageCollection.exist(etage))
			return false;

		Document etageDocument = new Document();
		etageDocument.append("Numero", etage.numEtage);
		if(etage.tables != null)
			etageDocument.append("Tables", TableCollection.getTableNumbers(etage.tables));

		EtageCollection.collection.insertOne(etageDocument);
		return true;
	}


	public static boolean exist(Etage etage) {
		return EtageCollection.collection.countDocuments(new Document("Numero", etage.numEtage)) > 0;
	}

}
