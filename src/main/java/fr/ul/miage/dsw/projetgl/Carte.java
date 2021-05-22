package fr.ul.miage.dsw.projetgl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.ul.miage.dsw.projetgl.database.CarteCollection;
import fr.ul.miage.dsw.projetgl.database.PlatCollection;

public class Carte {

	public Date dateCarte;
	public ArrayList<Plat> plats;

	public Carte() {
		this(new Date());
	}

	public Carte(Date date) {
		this.dateCarte = Tools.skipTime(date);
	}


	public Date getDateCarte() {
		return dateCarte;
	}

	public void setDateCarte(Date dateCarte) {
		this.dateCarte = dateCarte;
	}

	public List<Plat> getPlats() {
		return plats;
	}

	@Override
	public String toString() {
		return "Carte [dateCarte=" + dateCarte + ", plats=" + plats + "]";
	}

	public boolean save() {
		return CarteCollection.save(this);
	}


}
