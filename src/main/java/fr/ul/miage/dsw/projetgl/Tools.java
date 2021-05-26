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
