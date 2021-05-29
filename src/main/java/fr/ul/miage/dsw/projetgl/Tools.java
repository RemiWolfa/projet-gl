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

	public static int getIntegerInput(int min, int max, int defaut){

		Scanner scan = new Scanner(System.in);
		System.out.println("Veuilez entrer une valeur de type integer entre "+min+" et "+max);
		while (!scan.hasNextInt()) scan.next();
		int i = scan.nextInt();
		if(i<min || i>max) {
			return defaut;
		}
		return i;


	}

	public static double getDoubleInput(double min, double max, double defaut){

		Scanner scan = new Scanner(System.in);
		System.out.println("Veuilez entrer une valeur de type double entre "+min+" et "+max);
		while (!scan.hasNextDouble()) scan.next();
		double i = scan.nextDouble();
		if(i<min || i>max) {
			return defaut;
		}
		return i;
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
		String pattern = "MM/dd/yyyy HH:mm:ss";
		DateFormat df = new SimpleDateFormat(pattern);
		String string = df.format(date);
		return string;
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
