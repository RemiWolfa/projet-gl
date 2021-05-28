package fr.ul.miage.dsw.projetgl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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

	public static int getIntegerInput() throws IncorrectParam{

		try {
			Scanner scan = new Scanner(System.in);
			int i = scan.nextInt();
			if(i<0) {
				throw new IncorrectParam("La valeur ne peut pas être négative.");
			}
			return i;
		}
		catch(NumberFormatException e) {
			throw new IncorrectParam("Veuillez entrer une chaîne de caractère.");
		}
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

	public static HashMap<String, Double> sortHashByValue(HashMap<String, Double> hm)
	{
		List<Map.Entry<String, Double> > list =
				new LinkedList<Map.Entry<String, Double> >(hm.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Double> >() {
			public int compare(Map.Entry<String, Double> o1,
					Map.Entry<String, Double> o2)
			{
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
		for (Map.Entry<String, Double> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}

}
