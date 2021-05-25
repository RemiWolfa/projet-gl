package fr.ul.miage.dsw.projetgl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;


import fr.ul.miage.dsw.projetgl.database.CarteCollection;
import fr.ul.miage.dsw.projetgl.database.PlatCollection;

public class Tools {

	public static Date skipTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String sDate= sdf.format(date);
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        Date date2;
		try {
			date2 = format.parse(sDate);
			return date2;
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return date;
	}

	public static Plat readPlat() {
		System.out.println("Rechercher plats dans : ");
		System.out.println("1. Catégorie");
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
	
	public static Date getDateInput() {//TODO ajouter des conditions
		Scanner scan = new Scanner(System.in);
		System.out.println("Jour:");
		int day = scan.nextInt();
		
		System.out.println("Mois:");
		int month = scan.nextInt();
		
		System.out.println("Année:");
		int year = scan.nextInt();
		
		System.out.println("Heure:");
		int hour = scan.nextInt();

		Date date = new GregorianCalendar(year, month - 1, day).getTime();
		date.setHours(hour);
		return date;
	}
	
	public static void error(String error) {
		System.out.println("Erreur : "+error);
	}

	public static String format(Date date) {
		// TODO faire un vrai format
		return date.toString();
	}

}
