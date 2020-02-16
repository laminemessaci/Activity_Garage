package metier;

public class MoteurEssence extends Moteur{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8403482103757969395L;

	public MoteurEssence(String cylindre, double prix) {
		super(cylindre, prix);
		type=TypeMoteur.ESSENCE;
		// TODO Auto-generated constructor stub
	}

}
