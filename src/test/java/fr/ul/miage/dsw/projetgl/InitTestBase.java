package fr.ul.miage.dsw.projetgl;

import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

	import fr.ul.miage.dsw.projetgl.database.CarteCollection;
	import fr.ul.miage.dsw.projetgl.database.CategorieCollection;
	import fr.ul.miage.dsw.projetgl.database.MatierePremiereCollection;
	import fr.ul.miage.dsw.projetgl.database.PlatCollection;
	import fr.ul.miage.dsw.projetgl.database.ReservationCollection;
	import fr.ul.miage.dsw.projetgl.database.TableCollection;
	import fr.ul.miage.dsw.projetgl.database.UserCollection;

	public class InitTestBase {
		
		public static final String DATABASE_NAME = "test";
		public static final String CONNECTION_STRING = "mongodb+srv://dev:psw@cluster0.agvc1.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";
		public static MongoClient mongoClient;
		public static MongoDatabase database;
		public static final String[] collections = {"UtilisateursTest", "TablesTest", "CartesTest", "ReservationsTest", "MatierePremieresTest", "PlatsTest", "CategoriesTest"};


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
		
		private static void clearCollections(MongoDatabase database) {
			for(String collection : collections) {
				database.getCollection(collection).deleteMany(new Document());
			}
		}
		

		static void init() {
			System.out.println("######");
			mongoClient = MongoClients.create(CONNECTION_STRING);
			System.out.println("before get base");
	        database = mongoClient.getDatabase(DATABASE_NAME);
	        createCollectionsIfNotExist(database);
	        clearCollections(database);
	        TableCollection.collection = database.getCollection("TablesTest");
	        UserCollection.collection= database.getCollection("UtilisateursTest"); 
	        ReservationCollection.collection = database.getCollection("ReservationsTest");
	        CarteCollection.collection = database.getCollection("CartesTest");
	        PlatCollection.collection = database.getCollection("PlatsTest");
	        MatierePremiereCollection.collection = database.getCollection("MatierePremieresTest");
	        CategorieCollection.collection = database.getCollection("CategoriesTest");
		}

	}


