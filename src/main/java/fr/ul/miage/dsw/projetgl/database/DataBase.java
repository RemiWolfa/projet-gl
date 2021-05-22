package fr.ul.miage.dsw.projetgl.database;

import java.util.ArrayList;
import java.util.Arrays;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;


public class DataBase {

	public static final String DATABASE_NAME = "restaurant";
	public static final String CONNECTION_STRING = "mongodb+srv://dev:psw@cluster0.agvc1.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";

	public static final String[] collections = {"Utilisateurs", "Etages", "Tables", "Cartes", "Reservations", "MatierePremieres", "Plats", "Categories"};

	public static MongoClient mongoClient;

	public static MongoDatabase database;

	public static void connect() {
		DataBase.mongoClient = MongoClients.create(CONNECTION_STRING);
		DataBase.database = mongoClient.getDatabase(DATABASE_NAME);

		createCollectionsIfNotExist(database);
		
		TableCollection.collection = database.getCollection("tables");
	}

	private static void createCollectionsIfNotExist(MongoDatabase database) {
		ArrayList<String> collectionsToCreate = new ArrayList<String>(Arrays.asList(collections));
		database.listCollectionNames().forEach(
				collectionName -> {
					if(collectionsToCreate.contains(collectionName))
						collectionsToCreate.remove(collectionName);
				}
				);

		for(String collectionName : collectionsToCreate) {
			database.createCollection(collectionName);
		}
	}

}
