package fr.ul.miage.dsw.projetgl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.ul.miage.dsw.projetgl.database.CarteCollection;
import fr.ul.miage.dsw.projetgl.database.PlatCollection;

public class Carte {

	private Date dateCarte;
	private List<Plat> plats;

	public Carte() {
		this(Tools.skipTime(new Date()));
	}

	public Carte(Date date) {
		this.dateCarte = Tools.skipTime(date);
	}
	
	public Date getDate() {
		return this.dateCarte;
	}

	public List<Plat> getPlats() {
		return this.plats;
	}
	
	public void setPlats(ArrayList<Plat> plats) {
		this.plats = plats;
	}
	
	
	public boolean addPlat(Plat plat) {
		if(!PlatCollection.exist(plat))
			return false;
		this.plats.add(plat);
		System.out.println(plats.size());
		return true;
	}

	public boolean hasPlat(Plat plat) {
		System.out.print("size:"+this.plats.size());
		return this.plats.contains(plat);
	}
	
	
	public boolean save() {
		return CarteCollection.save(this);
	}

	
	public static Carte getTodayCarte() {
		Carte carte = new Carte();
		carte.setPlats(CarteCollection.getToDayPlats());
		return carte;
	}


}
