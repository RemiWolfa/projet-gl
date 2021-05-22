package fr.ul.miage.dsw.projetgl.database;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class TableCollection {
	
	public static MongoCollection<Document> collection;
	
	public boolean save() {
		Document tableDocument = new Document();
		
		return true;
	}
	
	public void getTables () {
		
	}

}
