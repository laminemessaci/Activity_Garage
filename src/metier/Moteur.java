package metier;

import java.io.Serializable;

public abstract class Moteur implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3448611163105102131L;
	protected TypeMoteur type=null;
	protected String cylindre=null;
	protected double prix=0;
	
	public Moteur(String cylindre, double prix) {
		super();
		this.cylindre = cylindre;
		this.prix = prix;
	}

	@Override
	public String toString() {
		return type+" " + cylindre;
	}

	public double getPrix() {
		return prix;
	}
	
	

}
