package fr.ul.miage.dsw.projetgl.action;

import java.util.HashMap;

import fr.ul.miage.dsw.projetgl.database.ReservationCollection;

public class GetBestMeats implements UserAction{

	@Override
	public boolean execute() {
		HashMap<String,Double> map = new HashMap<String, Double>();
		map= ReservationCollection.bestProfitability();
		int i=1;

		System.out.println("Liste des plats les plus rentables : ");

		for(String name : map.keySet()) {
			double money = map.get(name);
			System.out.println(i+". "+name+" : "+money+" euros.\n");
			i++;
		}
		return true;
	}

}
