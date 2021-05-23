public class Commandes{
  
  public void parser(String cmd){
		  
		  String[] split = cmd.toLowerCase().split("-"); 
		  
		  String[] spl;
		  HashMap<String, String> map = new HashMap<String, String>();
		  for(int i=1; i<split.length;i++) {
			  
			  
				  spl = split[i].split(" ");
				  spl[0] = spl[0].replaceAll(" ", "");
				  spl[1] = spl[1].replaceAll(" ", "");
				  map.put(spl[0], spl[1]);
				  System.out.println(split[i]);
			  
		  }
		  
			  
			  try {
				 switch(split[0].replaceAll(" ", "")) {
				 case "connexion" :
					 int id = Integer.parseInt(map.get("id"));
					// connexion(id);
					 System.out.println(id);
					 
				 case "deconnexion" :
				 case "inscription" :
					 String nomUser = map.get("nomUser");
					 String mdp = map.get("mdp");
					 String mail = map.get("mail");
					 
				 case "affichercommande" :
					 int numero = Integer.parseInt(map.get("numero"));
				 case "prevenircommande" :
					 int num = Integer.parseInt(map.get("numero"));
				 case "commandetable" :
				 case "visualisercommande" :
				 case "transmettrecommande" :
				 case "afficherplatrentables" :
				 case "consulteraffectationserveur" :
				 case "modifierraffectationserveur" :
					 int numeroServeur = Integer.parseInt(map.get("numeroServeur"));
					 int numeroTable = Integer.parseInt(map.get("numeroTable"));
				 case "tablesdispo" :
				 case "tauxrotation" :
				 case "profitservice" :
				 case "stock" :
				 case "voirfactures" :
				 case "creeremploi" :
					 String emploi = map.get("emploi");
				 }
			  }
			  catch(Exception e) {
				  
			  }
	    
	  }
    
  }
    
  
  public class Connexion{
    private final int id;
  }
  
  
  public class Deconnexion{
  }

  public class Inscription {
    private final String nomUser;
    private final String mdp;
    private final String mail;
  }

  public class AfficherCommande {
    private final int numero;
  }

  public class PrevenirCommande {
    private final int numero;

  }

  public class CommandeTable{
    private final int numero;
    private final String plat;
    private final int id;
  }
    
  public class VisualiserCommande {
    private final int numero;

  }

  public class TransmettreCommande {
    private final int numero;

  }

  public class AfficherPlatRentables {

  }

  public class ConsulterAffectationServeur {

  }

  public class ModifierAffectationServeur {
    private final int numeroServeur;
    private final int numeroTable;

  }

  public class TablesDispo {

  }

  public class TauxRotation {

  }
  public class ProfitService {
    private final Date date;

  }
  public class Stock {

  }

  public class VoirFactures {

  }

  public class CreerEmploi {
    private final String nom;

  }
  
}
