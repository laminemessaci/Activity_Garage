package metier;

import java.io.Serializable;

public class GPS implements Option, Serializable{

	@Override
	public String toString() {
		return "GPS";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6431139884958655718L;

	@Override
	public Double getPrix() {
		// TODO Auto-generated method stub
		return 113.5;
	}

}
