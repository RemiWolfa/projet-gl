package fr.ul.miage.dsw.projetgl.action;

import fr.ul.miage.dsw.projetgl.Carte;
import fr.ul.miage.dsw.projetgl.Plat;
import fr.ul.miage.dsw.projetgl.dashboard.DirecteurDashBoard;

public class ModifyMenu implements UserAction{

	@Override
	public boolean execute() {
		Carte carte = Carte.getTodayCarte();
		System.out.println("Plats de la carte:");
		for(Plat plat : carte.getPlats()) {
			System.out.println(plat.nom);
		}
		fillCarte(carte);
		return carte.save();
	}

	public static void fillCarte(Carte carte) {
		Plat plat;
		while((plat = ActionHelper.readPlat()) != null) {
			if(carte.hasPlat(plat))
				System.out.println("Le plat est déjà à la carte!");
			else
				carte.addPlat(plat);
		}
	}

}
