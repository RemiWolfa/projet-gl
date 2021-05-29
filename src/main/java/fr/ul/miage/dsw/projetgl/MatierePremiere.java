package fr.ul.miage.dsw.projetgl;

import fr.ul.miage.dsw.projetgl.database.MatierePremiereCollection;

public class MatierePremiere {

	public String nom;
	public Boolean enPoids;

	public MatierePremiere(String nom) {
		this.nom = nom;
	}
	
	public boolean save() {
		return MatierePremiereCollection.save(this);
	}
	
	public boolean equals(Object object) {
		if(!(object instanceof MatierePremiereCollection))
			return false;
		
		MatierePremiere mp = (MatierePremiere) object;
		return mp.nom.equalsIgnoreCase(this.nom);
	}
}
