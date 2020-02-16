package metier;

import java.io.Serializable;

public class VitreElectrique implements Option,Serializable{

	@Override
	public String toString() {
		return "Vitre Electrique";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -683709000288322455L;

	@Override
	public Double getPrix() {
		// TODO Auto-generated method stub
		return 212.3;
	}

}
