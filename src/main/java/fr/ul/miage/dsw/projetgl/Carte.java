package fr.ul.miage.dsw.projetgl;

import java.util.Date;
import java.util.List;

public class Carte {
	
	public Date dateCarte;
	public List<Plat> plats;
	
	
	
	
	
	
	public Date getDateCarte() {
		return dateCarte;
	}

	public void setDateCarte(Date dateCarte) {
		this.dateCarte = dateCarte;
	}

	public List<Plat> getPlats() {
		return plats;
	}

	public void setPlats(List<Plat> plats) {
		this.plats = plats;
	}

	@Override
	public String toString() {
		return "Carte [dateCarte=" + dateCarte + ", plats=" + plats + "]";
	}
	
	

}
