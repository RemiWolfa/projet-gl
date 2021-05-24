package fr.ul.miage.dsw.projetgl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import fr.ul.miage.dsw.projetgl.database.CarteCollection;
import fr.ul.miage.dsw.projetgl.database.PlatCollection;

public class Tools {

	public static Date skipTime(Date date) {

		//FIXME ne retire pas le time
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Plat readPlat() {
		System.out.println("Rechercher plats dans : ");
		System.out.println("1 . Catégorie");
		System.out.println("2. Carte du jour");
		System.out.println("3. Par nom");
		System.out.println("4. Terminer");

		int i = Tools.getIntegerInput();

		ArrayList<Plat> plats = new ArrayList<Plat>();

		switch(i) {
		case 1:
			//TODO
			break;
		case 2:
			plats = CarteCollection.getToDayPlats();
			break;
		case 3:
			System.out.println("Entrez un nom à rechercher:");
			String name = Tools.getStringInput();
			plats = PlatCollection.getPlatsByName(name);
			break;
		case 4:
			return null;
		}

		for(int j = 0; j < plats.size(); j++) {//j+1 pour l'utilisateur
			System.out.println((j+1)+". "+plats.get(j).nom);
		}

		System.out.println((plats.size()+1)+". Retour");

		try {
			int input = getIntegerInput();
			if(input-1 == plats.size()) {
				return Tools.readPlat();
			}else {
				return plats.get(input-1);
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Nombre non valide");
		}
		return null;
	}

	public static int getIntegerInput() {
		Scanner scan = new Scanner(System.in);
		return scan.nextInt();//TODO
	}

	public static String getStringInput() {
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}

}
