package metier;

public class MoteurDiesel extends Moteur{


	/**
	 * 
	 */
	private static final long serialVersionUID = -454016358743189322L;

	public MoteurDiesel(String cylindre, double prix) {
		super(cylindre, prix);
		type=TypeMoteur.DIESEL;
		// TODO Auto-generated constructor stub
	}

}
