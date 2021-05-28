package fr.ul.miage.dsw.projetgl.action;

import java.util.ArrayList;

import fr.ul.miage.dsw.projetgl.MatierePremiere;
import fr.ul.miage.dsw.projetgl.database.MatierePremiereCollection;

public class ShowStuff implements UserAction{

	@Override
	public boolean execute() {
		ArrayList<MatierePremiere> mp =MatierePremiereCollection.getMatieresPremieres();
		for(MatierePremiere matiere : mp) {
			System.out.println("Nom : "+matiere.nom+" \n "
					+ "Stock : " + MatierePremiereCollection.getStock(matiere.nom));
		}
		return true;
	}

}
