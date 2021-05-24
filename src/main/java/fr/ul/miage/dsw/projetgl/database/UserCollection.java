package fr.ul.miage.dsw.projetgl.database;


import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import fr.ul.miage.dsw.projetgl.Serveur;
import fr.ul.miage.dsw.projetgl.Utilisateur;

public class UserCollection {
	
public static MongoCollection<Document> collection;
	
	public static boolean save(Utilisateur user) {
		if( UserCollection.exist(user)) 
			return false;
		
		Document userDocument = new Document();
		userDocument.append("Type", user.typeUser.toString());
		userDocument.append("Nom", user.nom);
		userDocument.append("Identifiant", user.identifiant);
		
		if(user instanceof Serveur) {
			Serveur server = (Serveur)user;
			if(server.tables != null) {
				userDocument.append("Tables", TableCollection.getTableNumbers(server.tables));
			}
		}
		
		UserCollection.collection.insertOne(userDocument);
		return true;
	}
	
	public static boolean exist(Utilisateur user) {
		return UserCollection.collection.countDocuments(new Document("Identifiant", user.identifiant)) > 0;
	}
	
	public static boolean exist(String identifiant) {
		return UserCollection.collection.countDocuments(new Document("Identifiant", identifiant)) > 0;
	}
	
	public void getTables () {
		
	}
	
	public static Utilisateur getUser(String identifiant) {
		Document doc = UserCollection.collection.find(new Document("Identifiant", identifiant)).first();
		if(doc != null) {
			return UserCollection.getUserFromDoc(doc);
		}
		return null;
	}

	public static ArrayList<Utilisateur> getAllUsers() {
		ArrayList<Utilisateur> users = new ArrayList<Utilisateur>();
		UserCollection.collection.find().forEach(
				doc -> {
					users.add(UserCollection.getUserFromDoc(doc));
				}
				);
		return users;
	}
	
	private static Utilisateur getUserFromDoc(Document doc) {
		Utilisateur user;
		if(doc.getString("Type").equalsIgnoreCase("serveur")) {
			user = new Serveur(doc.getString("Identifiant"), doc.getString("Nom"));
			((Serveur)user).tables = TableCollection.getTablesFromNumbers((ArrayList<Integer>) doc.get("Tables"));
		}else {
			user = new Utilisateur(doc.getString("Identifiant"), doc.getString("Nom"), doc.getString("Type"));
		}
		return user;
	}

}
