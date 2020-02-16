package metier;

public class MoteurHybride extends Moteur{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1372505173177620279L;

	public MoteurHybride(String cylindre, double prix) {
		super(cylindre, prix);
		type=TypeMoteur.HYBRIDE;
		
	}
}
