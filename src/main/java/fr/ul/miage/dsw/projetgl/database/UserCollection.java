package fr.ul.miage.dsw.projetgl.database;


import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import fr.ul.miage.dsw.projetgl.Serveur;
import fr.ul.miage.dsw.projetgl.Utilisateur;
import fr.ul.miage.dsw.projetgl.enumeration.TypeUtilisateur;

public class UserCollection {
	
	public static final String TYPE_ATTRIBUT = "Type";
	public static final String NOM_ATTRIBUT = "Nom";
	public static final String IDENTIFIANT_ATTRIBUT = "Identifiant";
	public static final String TABLES_ATTRIBUT = "Tables";
	
public static MongoCollection<Document> collection;
	
	public static boolean save(Utilisateur user) {
		if( UserCollection.exist(user)) 
			return false;
		
		Document userDocument = new Document();
		userDocument.append(TYPE_ATTRIBUT, user.typeUser.toString());
		userDocument.append(NOM_ATTRIBUT, user.nom);
		userDocument.append(IDENTIFIANT_ATTRIBUT, user.getId());
		
		if(user instanceof Serveur) {
			Serveur server = (Serveur)user;
			if(server.tables != null) {
				userDocument.append(TABLES_ATTRIBUT, TableCollection.getTableNumbers(server.tables));
			}
		}
		
		UserCollection.collection.insertOne(userDocument);
		return true;
	}
	
	public static boolean updateTables(Serveur server) {
		if(!UserCollection.exist(server.getId()))
			return false;
		
		Document requestDoc = new Document(IDENTIFIANT_ATTRIBUT, server.getId());
		Document update = new Document("$set", new Document(TABLES_ATTRIBUT, TableCollection.getTableNumbers(server.tables)));
		
		UserCollection.collection.updateOne(requestDoc, update);
		return true;
	}
	
	public static boolean exist(Utilisateur user) {
		return UserCollection.exist(user.getId());
	}
	
	public static boolean exist(String identifiant) {
		return UserCollection.collection.countDocuments(new Document(IDENTIFIANT_ATTRIBUT, identifiant)) > 0;
	}
	
	public static Utilisateur getUser(String identifiant) {
		Document doc = UserCollection.collection.find(new Document(IDENTIFIANT_ATTRIBUT, identifiant)).first();
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
	
	public static ArrayList<Serveur> getAllServeurs() {
		ArrayList<Serveur> users = new ArrayList<Serveur>();
		UserCollection.collection.find(new Document(TYPE_ATTRIBUT, TypeUtilisateur.serveur.toString())).forEach(
				doc -> {
					users.add((Serveur)UserCollection.getUserFromDoc(doc));
				}
				);
		return users;
	}
	
	private static Utilisateur getUserFromDoc(Document doc) {
		Utilisateur user;
		if(doc.getString(TYPE_ATTRIBUT).equalsIgnoreCase("serveur")) {
			Serveur serveur =new Serveur(doc.getString(IDENTIFIANT_ATTRIBUT), doc.getString(NOM_ATTRIBUT));
			serveur.tables = TableCollection.getTablesFromNumbers((ArrayList<Integer>) doc.get(TABLES_ATTRIBUT));
			user = serveur;
		}else {
			user = new Utilisateur(doc.getString(IDENTIFIANT_ATTRIBUT), 
					doc.getString(NOM_ATTRIBUT), doc.getString(TYPE_ATTRIBUT));
		}
		return user;
	}

}
