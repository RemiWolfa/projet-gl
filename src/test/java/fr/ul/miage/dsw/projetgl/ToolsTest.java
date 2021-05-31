package fr.ul.miage.dsw.projetgl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ToolsTest {
	
	@Test
	@DisplayName("Test : retourner le format de la date en string")
	public void assertEqualFormatDate() {
		String dateenString = "01/07/2021 01:12:30";
		Date date = new Date("01/07/2021 01:12:30");
		
		String resultatObtenue = Tools.format(date);
		String resultatAttendue = dateenString;
		
		assertEquals(resultatObtenue,resultatAttendue);
		
	}
	
	@Test
	@DisplayName("Test : trie d une hashMap")
    public void assertEqualsSortedHashMap() {
        HashMap<String,Double> map=new HashMap<String,Double>();
        HashMap<String,Double> result=new HashMap<String,Double>();
        map.put("Test3", 3.0);
        map.put("Test4", 4.0);
        map.put("Test2", 2.0);
        map.put("Test1", 1.0);

        map = Tools.sortHashByValue(map);

        int i=0;
        ArrayList<Double> list = new ArrayList<Double>();
        ArrayList<Double> listSorted = new ArrayList<Double>();

        for(String name : map.keySet()) {
             list.add(map.get(name));
        }

        listSorted.add(4.0);
        listSorted.add(3.0);
        listSorted.add(2.0); 
        listSorted.add(1.0);

        assertEquals(list,listSorted);


    }
	
	
	


}
