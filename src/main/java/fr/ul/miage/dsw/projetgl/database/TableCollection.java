package fr.ul.miage.dsw.projetgl.database;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import fr.ul.miage.dsw.projetgl.Table;

public class TableCollection {
	
	public static MongoCollection<Document> collection;
	
	public static boolean save(Table table) {
		if(TableCollection.exist(table))
			return false;
		Document tableDocument = new Document();
		tableDocument.append("Numero", table.num);
		tableDocument.append("Etat", table.etat);
		tableDocument.append("Etage", table.etage);
		
		TableCollection.collection.insertOne(tableDocument);
		return true;
	}
	
	public static boolean exist(Table table) {
		return TableCollection.collection.countDocuments(new Document("Numero", table.num)) > 0;
	}
	
	public void getTables () {
		
	}
	
	public static ArrayList<Integer> getTableNumbers(List<Table> tables){
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for(Table table : tables) {
			numbers.add(table.num);
		}
		return numbers;
	}

}
