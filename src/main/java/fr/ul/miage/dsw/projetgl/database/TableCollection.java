package fr.ul.miage.dsw.projetgl.database;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import fr.ul.miage.dsw.projetgl.Table;
import fr.ul.miage.dsw.projetgl.enumeration.EtatTable;

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

	public static List<Table> updateTables(List<Table> oldTables) {
		ArrayList<Integer> tableNums = TableCollection.getTableNumbers(oldTables);
		return TableCollection.getTablesFromNumbers(tableNums);
	}

	public static List<Table> getTablesFromNumbers(ArrayList<Integer> tableNums) {		
		ArrayList<Table> tables = new ArrayList<Table>();
		if(tableNums == null || tableNums.size() == 0)
			return tables;
		
		Document in = new Document("$in", tableNums);
		Document requestDoc = new Document("Numero", in);
		TableCollection.collection.find(requestDoc).forEach(
				tableDoc -> {
					Table table = new Table(tableDoc.getInteger("Numero"));
					table.etat = EtatTable.valueOf(tableDoc.getString("Etat"));
					table.etage = tableDoc.getInteger("Etage");
					tables.add(table);
				}
				);
		return tables;
	}

	public static boolean updateState(Table table) {
		// TODO méthode à remplir
		return false;
	}

}
