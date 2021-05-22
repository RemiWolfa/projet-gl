package fr.ul.miage.dsw.projetgl.database;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import fr.ul.miage.dsw.projetgl.Serveur;
import fr.ul.miage.dsw.projetgl.Table;
import fr.ul.miage.dsw.projetgl.Utilisateur;

public class UserCollection {
	
public static MongoCollection<Document> collection;
	
	public static boolean save(Utilisateur user) {
		if( UserCollection.exist(user)) 
			return false;
		
		Document userDocument = new Document();
		userDocument.append("Type", user.typeUser.toString());
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
	
	public void getTables () {
		
	}
	

}