package fr.ul.miage.dsw.projetgl.action;

import java.util.List;


import fr.ul.miage.dsw.projetgl.Table;
import fr.ul.miage.dsw.projetgl.Text;
import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.database.TableCollection;
import fr.ul.miage.dsw.projetgl.enumeration.EtatTable;

public class ShowTableStates implements UserAction{

	@Override
	public boolean execute() {
		List<Table> tables = TableCollection.getAllTables();

		int i = 1;
		for(Table table : tables) {
			System.out.println(i+". Table numéro:"+table.num+" : "+table.etat);
			i++;
		}
		System.out.println(i+1+". Retour");

		int input = Tools.getIntegerInput(1, i+1, i+1);
		if(input > 0 && input < i+1) {
			Table table = tables.get(input-1);
			modifyTableState(table);
		}

		return true;
	}

	private void modifyTableState(Table table) {
		System.out.println(Text.MODIFY_TABLE_STATE);
		System.out.println("1. Table sale");
		System.out.println("2. Table propre/dressée");
		System.out.println("3. Table occupée");
		System.out.println("4. Retour");

		int input = Tools.getIntegerInput(1, 4, 4);
		switch(input){
		case 1:
			table.setEtat(EtatTable.sale);
			break;
		case 2:
			table.setEtat(EtatTable.propre);
			break;
		case 3:
			table.setEtat(EtatTable.occupee);
			break;

		default :
			return;
		}
		
		System.out.println(Text.TABLE_STATE_UPDATED);
		TableCollection.updateState(table);
	}


}
