package fr.ul.miage.dsw.projetgl.action;

import java.util.ArrayList;

import fr.ul.miage.dsw.projetgl.IncorrectParam;
import fr.ul.miage.dsw.projetgl.MatierePremiere;
import fr.ul.miage.dsw.projetgl.Text;
import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.database.MatierePremiereCollection;

public class UpdateStock implements UserAction{

	@Override
	public boolean execute() {
			ArrayList<MatierePremiere> mp =MatierePremiereCollection.getMatieresPremieres();
			int i=1;
			System.out.println(Text.SELECT_STOCK);
			
			for(MatierePremiere matiere : mp) {
				System.out.println(i+". "+matiere.nom+" \n ");
				i++;
			}
			
			try {
				int num = Tools.getIntegerInput(1,i-1,mp.size()+1);//TODO verifier si 0 (car 0-1 < -1)
				if(num > mp.size())
					return false;
				
				System.out.println("Quelle valeur de stock souhaitez vous entrer ?");
				int stock = Tools.getIntegerInput(0,Integer.MAX_VALUE,Integer.MAX_VALUE);
				return MatierePremiereCollection.setStock(mp.get(num-1).nom, stock);
			}
			catch(IncorrectParam e) {
				System.out.println(e.getMessage());
				return execute();
			}
		}

}
