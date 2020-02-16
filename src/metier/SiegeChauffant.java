package metier;

import java.io.Serializable;

public class SiegeChauffant implements Option,Serializable{

	@Override
	public String toString() {
		return "Siège Chauffant";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3968802261912259560L;

	@Override
	public Double getPrix() {
		// TODO Auto-generated method stub
		return 562.9;
	}

}
