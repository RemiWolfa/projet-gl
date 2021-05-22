public class Commandes{
  
  
  public class connexion{
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
