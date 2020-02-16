package metier;

import java.io.Serializable;

public class BarreDeToit implements Option,Serializable{

	@Override
	public String toString() {
		return "Barre de Toit";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4563680616960302988L;

	@Override
	public Double getPrix() {
		// TODO Auto-generated method stub
		return 29.9;
	}

}
