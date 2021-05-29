package fr.ul.miage.dsw.projetgl.action;

import fr.ul.miage.dsw.projetgl.Carte;
import fr.ul.miage.dsw.projetgl.Plat;
import fr.ul.miage.dsw.projetgl.Text;
import fr.ul.miage.dsw.projetgl.dashboard.DirecteurDashBoard;

public class ModifyMenu implements UserAction{

	@Override
	public boolean execute() {
		Carte carte = Carte.getTodayCarte();
		System.out.println(Text.MENU_MEATS);
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
				System.out.println(Text.MEAT_ALREADY_MENU);
			else
				carte.addPlat(plat);
		}
	}

}
