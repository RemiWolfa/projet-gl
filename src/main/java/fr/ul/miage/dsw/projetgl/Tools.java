package fr.ul.miage.dsw.projetgl;

import java.util.Calendar;
import java.util.Date;

public class Tools {

	public static Date skipTime(Date date) {
		
		//FIXME ne retire pas le time
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

}
