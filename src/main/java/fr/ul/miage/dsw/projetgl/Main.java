package fr.ul.miage.dsw.projetgl;

public class Main {
  
  
  public static void main(String[] args) {
      try ( Scanner scanner = new Scanner( System.in ) ) {
     
      while(true){
        System.out.print( "Bonjour ! Que souhaitez vous faire ?" );
        String  line = scanner.nextLine();
        Commandes cmd = new Commandes();
        cmd.parser("connexion -numServeur 3 -table 1");
      }   
            
     }
  }
}
