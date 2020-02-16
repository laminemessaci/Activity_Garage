package metier;

public class MoteurElectrique extends Moteur{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2364120408923327082L;

	public MoteurElectrique(String cylindre, double prix) {
		super(cylindre, prix);
		type=TypeMoteur.ELECTRIQUE;
		// TODO Auto-generated constructor stub
	}

}
