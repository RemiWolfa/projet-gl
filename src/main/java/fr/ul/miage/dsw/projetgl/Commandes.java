package fr.ul.miage.dsw.projetgl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Commandes{
	  
		
	  
	  public void parser(String cmd){
		  
		  String[] split = cmd.toLowerCase().split("-"); 
		  HashMap<String, String> map = new HashMap<String, String>();
		  String[] spl;
		  for(int i=1; i<split.length;i++) {
			  
			  try {
			  
			  
				  spl = split[i].split(" ");
				  spl[0] = spl[0].replaceAll(" ", "");
				  spl[1] = spl[1].replaceAll(" ", "");
				  map.put(spl[0], spl[1]);
			  }
			  catch (ArrayIndexOutOfBoundsException e) {
				  System.out.println("Les commandes doivent être sous la forme : methode -arg value -arg2 value2");
			  }
				  
		  }
		  
		 
			 try {

			 String num;
			  
			 switch(split[0].replaceAll(" ", "")) {
				
				 
			 case "connexion" :
					 String id = map.get("id");
					 Connexion co = new Connexion();
					 if(co.checkParamConnexion(id)) {
						 
					 }
					 break;
					 
				 case "deconnexion" :
					 break;
				 case "inscription" :
					 String nomUser = map.get("nomuser");
					 String mdp = map.get("mdp");
					 String mail = map.get("mail");
					 Inscription ins = new Inscription();
					 if(ins.checkParamInscription(nomUser, mdp, mail)) {
						 
					 }
					 break;
					 
				 case "affichercommande" :
					 String numero = map.get("numero");
					 AfficherCommande ac= new AfficherCommande();
					 if(ac.checkParamCommande(numero)) {
						 
					 }
					 break;
				 case "prevenircommande" :
					 num = map.get("numero");
					 PrevenirCommande pc = new PrevenirCommande();
					 if(pc.checkParamCommande(num)) {
						 
					 }
					 break;
					
				 case "commandetable" :
					 num= map.get("numero");
					 String plat= map.get("plat");
					 String idC= map.get("id");
					 
					 CommandeTable ct = new CommandeTable();
					 if(ct.checkParamCommande(num, plat, idC)) {
						 
					 }
					 break;
				 case "visualisercommande" :
					 num = map.get("numero");
					 VisualiserCommande vc = new VisualiserCommande();
					 if(vc.checkParamCommande(num)) {
						 
					 }
					 break;
				 case "transmettrecommande" :
					 
					 num = map.get("numero");
					 TransmettreCommande tm = new TransmettreCommande();
					 if(tm.checkParamCommande(num)){
						 
					 }
					 break;
					 
				 case "afficherplatrentables" :
					 break;
				 case "consulteraffectationserveur" :
					 break;
				 case "modifierraffectationserveur" :
					 String numeroServeur = map.get("numeroServeur");
					 String numeroTable = map.get("numeroTable");
					 
					 ModifierAffectationServeur mas = new ModifierAffectationServeur();
					 if(mas.checkParamServeur(numeroServeur, numeroTable)) {
						 
					 }
				 case "tablesdispo" :
					 break;
				 case "tauxrotation" :
					 break;
				 case "profitservice" :
					 String date = map.get("date");
					 ProfitService ps = new ProfitService();
					 if(ps.checkDate(date)) {
						 
					 }
					 break;
				 case "stock" :
					 break;
				 case "voirfactures" :
					 break;
				 case "creeremploi" :
					 String nomEmploi = map.get("nomEmploi");
					 CreerEmploi ce = new CreerEmploi();
					 if(ce.checkNomEmploi(nomEmploi)) {
						 
					 }
					 break;
				 }
			 }
			 catch(IncorrectParam e) {
				 System.out.println(e.getMessage());
			
			  }
			 
	  }
	  
	  
	  
	  public class Deconnexion{
	  }

	  public class Inscription {

	    
	    public Boolean checkParamInscription(String nomUser, String mdp, String mail) throws IncorrectParam {
	    	
	    	String regex= "^(.+)@(.+)$";
	    	
	    	Pattern pattern = Pattern.compile(regex);

            Matcher matcher = pattern.matcher(mail);
            
            
            
            if (!matcher.matches() || nomUser.isBlank() || mdp.isBlank()) {
            	throw new IncorrectParam("Format du mail incorrect : " + mail);
            }

            return true;
	    	
	    }
	    
	  }

	  public class AfficherCommande {
		  public Boolean checkParamCommande(String numero) throws IncorrectParam {
		    	try{
		    		int num =Integer.parseInt(numero);
		    	}
		    	catch(NumberFormatException e) {
		    		throw new IncorrectParam("Numéro de commande faux : " + numero );
		    	}
		    	
		    	return true;
		    }
	  }

	  public class PrevenirCommande {
		  public Boolean checkParamCommande(String numero) throws IncorrectParam {
		    	try{
		    		int num =Integer.parseInt(numero);
		    	}
		    	catch(NumberFormatException e) {
		    		throw new IncorrectParam("Numéro de commande faux : " + numero );
		    	}
		    	
		    	return true;
		    }

	  }

	  public class CommandeTable{
	   
	    public Boolean checkParamCommande(String numero, String plat, String id) throws IncorrectParam {
	    	try{
	    		int num =Integer.parseInt(numero);
	    		int identifiant = Integer.parseInt(id);
	    	}
	    	catch(NumberFormatException e) {
	    		throw new IncorrectParam("Numéro de commande : "+numero+" ou d'identifiant : "+id+" faux ");
	    	}
	    	if(numero.isBlank() || plat.isBlank() || id.isBlank()) {
	    		throw new IncorrectParam("Paramètre(s) manquant(s)");
	    	}
	    	
	    	return true;
	    }
	  }
	    
	  public class VisualiserCommande {
		  public Boolean checkParamCommande(String numero) throws IncorrectParam {
		    	try{
		    		int num =Integer.parseInt(numero);
		    	}
		    	catch(NumberFormatException e) {
		    		throw new IncorrectParam("Numéro de commande faux : " + numero );
		    	}
		    	
		    	return true;
		    }

	  }

	  public class TransmettreCommande {
		  public Boolean checkParamCommande(String numero) throws IncorrectParam {
		    	try{
		    		int num =Integer.parseInt(numero);
		    	}
		    	catch(NumberFormatException e) {
		    		throw new IncorrectParam("Numéro de commande faux : " + numero );
		    	}
		    	
		    	return true;
		    }

	  }

	  public class AfficherPlatRentables {

	  }

	  public class ConsulterAffectationServeur {

	  }

	  public class ModifierAffectationServeur {
		  public Boolean checkParamServeur(String numeroServeur, String numeroTable) throws IncorrectParam {
		    	try{
		    		int num =Integer.parseInt(numeroServeur);
		    		int num2 =Integer.parseInt(numeroTable);
		    	}
		    	catch(NumberFormatException e) {
		    		throw new IncorrectParam("Numéro du serveur : "+numeroServeur+" ou de table : "+numeroTable+" faux ");
		    	}
		    	
		    	return true;
		    }
	  }

	  public class TablesDispo {

	  }

	  public class TauxRotation {

	  }
	  public class ProfitService {
		String format = "yyyy/MM/dd";
		
		public Boolean checkDate(String date) throws IncorrectParam {
			DateFormat sdf = new SimpleDateFormat(format);

		      try {
		          sdf.parse(date);
		      } catch (ParseException e) {
		    	  throw new IncorrectParam("Format de la date fausse "+date +"\n Rappel, le format est "+format);
		      }
		        return true;
		    }

	    

	  }
	  public class Stock {

	  }

	  public class VoirFactures {

	  }

	  public class CreerEmploi {
	    
	    public Boolean checkNomEmploi(String nom) throws IncorrectParam {
   	
	    	if (nom.isBlank()) {
		       throw new IncorrectParam("Veuillez entrer un nom");
	        }
	    	return true;
	    	
	    }

	  }
	  
	}

