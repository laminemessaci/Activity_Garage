package metier;

import java.io.Serializable;

public class Climatisation implements Option,Serializable{

	@Override
	public String toString() {
		return "Climatisation";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7849952956301285008L;

	@Override
	public Double getPrix() {
		// TODO Auto-generated method stub
		return 347.3;
	}

}
