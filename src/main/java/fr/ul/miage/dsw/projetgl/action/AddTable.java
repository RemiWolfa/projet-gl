package fr.ul.miage.dsw.projetgl.action;

import fr.ul.miage.dsw.projetgl.Table;
import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.database.TableCollection;

public class AddTable implements UserAction{

	@Override
	public boolean execute() {
		System.out.println("Numero de la table à créer");
		int num = Tools.getIntegerInput(0, Integer.MAX_VALUE, 1);
		if(TableCollection.exist(new Table(num))){
			System.out.println("La table existe déjà");
			return false;
		}
		
		System.out.println("Nombre de couverts:");
		int nb = Tools.getIntegerInput(0, Integer.MAX_VALUE, 1);
		Table table = new Table(num);
		table.nbCouverts = nb;
		
		return table.save();
	}

}
