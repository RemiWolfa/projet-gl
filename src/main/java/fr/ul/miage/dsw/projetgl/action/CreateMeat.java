package fr.ul.miage.dsw.projetgl.action;

import fr.ul.miage.dsw.projetgl.IncorrectParam;
import fr.ul.miage.dsw.projetgl.MatierePremiere;
import fr.ul.miage.dsw.projetgl.Plat;
import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.dashboard.CuisinierDashBoard;
import fr.ul.miage.dsw.projetgl.database.MatierePremiereCollection;
import fr.ul.miage.dsw.projetgl.database.PlatCollection;

public class CreateMeat implements UserAction{

	@Override
	public boolean execute() {
		System.out.println("Entrez un nom:");
		String name = Tools.getStringInput();
		if(PlatCollection.exist(name)) {
			System.out.println("Le plat existe déjà");
			return false;
		}

		Plat plat = new Plat(name);

		String input; 
		System.out.println("Entrez un nom de matiere première (exit pour arrêter):");
		while(!(input = Tools.getStringInput()).equalsIgnoreCase("exit")) {
			if(!"".equalsIgnoreCase(input)) {

				MatierePremiere mp = new MatierePremiere(input);
				if(!MatierePremiereCollection.exist(mp)) {
					createMatierePremiere(mp);
				}
				plat.ajouterMatierePremiere(mp);
				System.out.println("Entrez un nom de matiere première (exit pour arrêter):");
			}
		}

		plat.save();
		return true;
	}

	public static void createMatierePremiere(MatierePremiere mp) {
		System.out.println("La matière première n'existe pas, elle va être créer");
		System.out.println("1. quantité en poid");
		System.out.println("2. quantité en unité");

		try {
			int i = Tools.getIntegerInput();
			if(i == 1)
				mp.enPoids = true;

			mp.save();
		} catch (IncorrectParam e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
