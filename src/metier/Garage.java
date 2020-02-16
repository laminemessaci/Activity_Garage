package metier;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class Garage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 466042685532373226L;
	
	String path="E:\\INGENIEUR_TELECOMMUNICATION_ET_RESEAUX\\COURS_S3\\JAVA\\TP\\garageOpenClassroom\\";
	protected static List<Vehicule> voitures=new ArrayList<Vehicule>();
	private ObjectInputStream ois;
	
	public Garage() {
		super();
		try {
			//saveGarageOnFile();
			voitures=readGarageFromFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur lors du chargement à partir du fichier:\n "+e.getMessage());
		}
		
	}
	@Override
	public String toString() {
		String affichage="------------------------------------\n"
						+"+      Garage OpenClassrooms       +\n"
						+"------------------------------------\n";
		for(Vehicule v:voitures) {
			String opt=new String();
			for(Option o:v.options) {
				opt+=o.toString()+" ("+o.getPrix()+"$),";
			}
			affichage+="\n"+"Vehicule " + v.nomMarque + ": " + v.nom + " Moteur " + v.moteur + " (" + v.prix
					+ "$) [" + opt + "] d'une valeur totale de "+v.getPrixTotalVehicule()+" $";
		}
		return affichage;
	}
	
	public void addVoiture(Vehicule vehicule) {
		voitures.add(vehicule);
		try {
			saveGarageOnFile();
		} catch (Exception e) {
			System.out.println("Erreur lors de la sauvegarde!!: \n"+e.getMessage());
		}
	}
	
	boolean saveGarageOnFile() throws Exception {
	      FileOutputStream fos = new FileOutputStream(path+"garage.txt");
	      ObjectOutputStream oos = new ObjectOutputStream(fos);
	      oos.writeObject(voitures);
	      oos.close();
	      return true;
		}
	
	List<Vehicule> readGarageFromFile() throws Exception {
		FileInputStream fis=new FileInputStream(path+"garage.txt");
		ois = new ObjectInputStream(fis);
		List<Vehicule> readObject = (List<Vehicule>) ois.readObject();
		return readObject;
	}
	

}
