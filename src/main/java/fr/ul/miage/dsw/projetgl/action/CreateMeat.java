package fr.ul.miage.dsw.projetgl.action;

import fr.ul.miage.dsw.projetgl.Categorie;
import fr.ul.miage.dsw.projetgl.IncorrectParam;
import fr.ul.miage.dsw.projetgl.MatierePremiere;
import fr.ul.miage.dsw.projetgl.Plat;
import fr.ul.miage.dsw.projetgl.Text;
import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.database.CategorieCollection;
import fr.ul.miage.dsw.projetgl.database.MatierePremiereCollection;
import fr.ul.miage.dsw.projetgl.database.PlatCollection;

public class CreateMeat implements UserAction{

	@Override
	public boolean execute() {
		System.out.println("Entrez un nom:");
		String name = Tools.getStringInput();
		if(PlatCollection.exist(name)) {
			System.out.println(Text.MEAT_EXIST);
			return false;
		}

		Plat plat = new Plat(name);

		String input; 
		System.out.println(Text.ENTER_STUFF_NAME);
		while(!(input = Tools.getStringInput()).equalsIgnoreCase("exit")) {
			if(!"".equalsIgnoreCase(input)) {

				MatierePremiere mp = new MatierePremiere(input);
				if(!MatierePremiereCollection.exist(mp)) {
					createMatierePremiere(mp);
				}
				plat.ajouterMatierePremiere(mp);
				System.out.println(Text.ENTER_STUFF_NAME);
			}
		}

		System.out.println(Text.ENTER_CATEGORIE);
		String categorie = Tools.getStringInput();
		
		return saveMeat(plat, categorie);
	}
	
	private static boolean saveMeat(Plat plat, String categorieName) {
		Categorie categorie = new Categorie(categorieName);
		if(!CategorieCollection.exist(categorie)) {
			categorie.save();
			System.out.println(Text.CREATE_CATEGORIE);
		}else {
			categorie = CategorieCollection.getCategorie(categorieName);
		}
		categorie.addMeat(plat);
		categorie.save();
		
		return plat.save();
	}

	public static void createMatierePremiere(MatierePremiere mp) {
		System.out.println(Text.STUFF_CREATION);
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
