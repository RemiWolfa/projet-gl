package fr.ul.miage.dsw.projetgl;

import java.util.List;

public class Stock {
	
	public MatierePremiere matierePremiere;
	
	//la taille du stock ?
	public int stock;
	public static List<Stock> listStock;
	
	
	public void incrementerStock(MatierePremiere mp) {
		
		//listStock.add();
		stock++;
	}
	
	public void decrementerStock() {
		
		stock--;
	}
	
	public List<Stock> chargerStock(){
		
		return null;
		
		
	}

}
