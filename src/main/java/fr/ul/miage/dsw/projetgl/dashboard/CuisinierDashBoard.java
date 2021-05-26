package fr.ul.miage.dsw.projetgl.dashboard;

import fr.ul.miage.dsw.projetgl.IncorrectParam;
import fr.ul.miage.dsw.projetgl.Tools;

public class CuisinierDashBoard {

	public static void readCommand() {
		System.out.println("----------------");
		System.out.println("1. Visualiser une commande");
		System.out.println("2. Prévenir d'une commande");
		System.out.println("3. Consulter les stocks");

		System.out.println("4. Quitter");

		int i = Tools.getIntegerInput();
		Boolean verif = false;
		String numero = "";

		switch(i) {

		case 1:
			System.out.println("Numéro :");	

			while(!verif) {
				try {
					numero = Tools.getStringInput();
					verif=checkParamCommand(numero);
				}
				catch(IncorrectParam e) {
					System.out.println(e.getMessage());
				}

			}

			CuisinierDashBoard.seeCommand(numero);;
			break;
		case 2:
			CuisinierDashBoard.informCommand(numero);
			break;
		case 3:
			return;
		}
		readCommand();
	}

	public static Boolean checkParamCommand(String numero) throws IncorrectParam {
		try{
			int num =Integer.parseInt(numero);
		}
		catch(NumberFormatException e) {
			throw new IncorrectParam("Numéro de commande faux : " + numero );
		}

		return true;
	}

	public static void seeCommand(String numero) {

	}

	public static void informCommand(String numero) {

	}

	public static void checkStocks() {


	}

	public static void creatMeat() {


	}




}
