package metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Vehicule implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7768577331279933545L;
	protected Marque nomMarque;
	protected String nom;
	protected Moteur moteur;
	protected double prix;
	
	protected List<Option> options=new ArrayList<Option>();
	
	
	double getPrixTotalVehicule() {
		double coutTotalOptions=0;
		for(Option o:options) {
			coutTotalOptions+=o.getPrix();
		}
		return prix+coutTotalOptions;
	}
	@Override
	public String toString() {
		String ListeOption = null;
		for(Option o:options) {
			ListeOption+=o.toString()+" ("+o.getPrix()+"$),";	
		}
		
		return "Vehicule " + nomMarque + ": " + nom + " Moteur " + moteur + " (" + prix
				+ "$) [" + ListeOption + "] d'une valeur totale de "+this.getPrixTotalVehicule()+" $";
	}
	public void addOption(Option opt) {
		options.add(opt);
	}
	Marque getMarque() {
		return nomMarque;
	}
	List<Option> getOptions(){
		return options;
	}
	
	double getPrix() {
		return prix;
	}
	public Moteur getMoteur() {
		return moteur;
	}
	public void setMoteur(Moteur moteur) {
		this.moteur = moteur;
	}
	public Marque getNomMarque() {
		return nomMarque;
	}
	public String getNom() {
		return nom;
	}
	
}
