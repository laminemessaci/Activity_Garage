package fr.ocr.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import voiture.Vehicule;
import voiture.option.Option;

public class VehiculeDAO extends DAO<Vehicule> {

	public VehiculeDAO(Connection conn) {
		super(conn);
	}

	public boolean create(Vehicule v) {

		try (PreparedStatement prep = connect
						.prepareStatement("INSERT INTO VEHICULE (id, nom, marque, moteur, prix) VALUES ?, ?, ?, ?, ?");
			PreparedStatement options = connect
					.prepareStatement("INSERT INTO vehicule_option (id_vehicule, id_option) VALUES ?, ?");
				){

			// quelques contr�les d'usage
			if (v.getNom().trim().equals(""))
				throw new DAOException(
						"VehiculeDAO : Erreur de creation en base de donn�e. Champ 'nom' obligatoire !");

			if (v.getMarque().getId() < 0)
				throw new DAOException(
						"VehiculeDAO : Erreur de creation en base de donn�e. Champ 'marque' obligatoire !");

			if (v.getMoteur().getId() < 0)
				throw new DAOException(
						"VehiculeDAO : Erreur de creation en base de donn�e. Champ 'moteur' obligatoire !");

			if (v.getPrix() <= 0 || v.getPrix() == null)
				throw new DAOException(
						"VehiculeDAO : Erreur de creation en base de donn�e. Champ 'prix' obligatoire !");

			// On d�marre notre transction
			connect.setAutoCommit(false);

			// Nous allons r�cup�rer le prochain ID
			ResultSet nextID = connect.prepareStatement(
					"CALL NEXT VALUE FOR seq_vehicule_id").executeQuery();

			if (nextID.next()) {
				int ID = nextID.getInt(1);

				
				prep.setInt(1, ID);
				prep.setString(2, v.getNom());
				prep.setInt(3, v.getMarque().getId());
				prep.setInt(4, v.getMoteur().getId());
				prep.setDouble(5, v.getPrix());

				prep.executeUpdate();

				// Nous avons enregistrer notre v�hicule en base
				// Nous allons maitenant sauvegarder ses options dans la table
				// de jointure
				List<Option> list = v.getOptions();

				for (Option o : list) {

					options.setInt(1, ID);
					options.setInt(2, o.getId());

					try {
						options.executeUpdate();
					} catch (SQLException e) {
						throw new DAOException(
								"VehiculeDAO : Erreur lors de la sauvegarde de l'option "
										+ o + " du vehicule ! \n"
										+ e.getMessage());
					}

				}
			} else {
				throw new DAOException(
						"VehiculeDAO : Impossible de lire le r�sultat de la recherche de s�quence !");
			}

		} catch (SQLException e) {
			new DAOException("VehiculeDAO : " + e.getMessage());
			try {
				connect.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		} catch (DAOException e) {
			try {
				connect.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}

		try {
			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean delete(Vehicule v) {
		try (PreparedStatement options = connect
					.prepareStatement("DELETE FROM vehicule_option WHERE id_vehicule = ?");
			PreparedStatement vehicule = connect
					.prepareStatement("DELETE FROM vehicule WHERE id = ?");
				){
			if (v.getId() < 1)
				throw new DAOException(
						"Impossible de supprimer un v�hicule non pr�sent en base de donn�es ! ");
			
			// On d�marre notre transction
			connect.setAutoCommit(false);
						
			options.setInt(1, v.getId());
			options.executeUpdate();
			
			
			vehicule.setInt(1, v.getId());
			vehicule.executeUpdate();
			
		} catch (DAOException e) {
			try {
				connect.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		} catch (SQLException e) {
			try {
				connect.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			new DAOException("VehiculeDAO : Erreur lors que la suppression d'un v�hicule");
			return false;
		}

		try {
			connect.commit();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return true;
	}

	public boolean update(Vehicule v) {
		return true;
	}

	public Vehicule find(int id) {
		Vehicule voit = new Vehicule();

		String query = "SELECT * FROM vehicule WHERE id = " + id;
		
		try (Statement state = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY)){
			
			ResultSet result = state.executeQuery(query);

			if (result.first())
				voit = new Vehicule(id, result.getString("nom"), new MarqueDAO(
						this.connect).find(result.getInt("marque")),
						new MoteurDAO(this.connect).find(result
								.getInt("moteur")), getOptions(id),
						result.getDouble("prix"));
		} catch (SQLException e) {
			new DAOException("VehiculeDAO : " + e.getMessage());
		}
		return voit;
	}

	public List<Vehicule> findAll() {
		List<Vehicule> list = new ArrayList<>();

		try (Statement state = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY)){
			ResultSet result = state.executeQuery(
					"SELECT * FROM vehicule ORDER BY nom");
			while (result.next()) {
				int id = result.getInt("id");
				list.add(new Vehicule(id, result.getString("nom"),
						new MarqueDAO(this.connect).find(result
								.getInt("marque")), new MoteurDAO(this.connect)
								.find(result.getInt("moteur")), getOptions(id),
						result.getDouble("prix")));
			}
		} catch (SQLException e) {
			new DAOException("VehiculeDAO : " + e.getMessage());
		}

		return list;
	}

	private List<Option> getOptions(int id) {
		List<Option> list = new ArrayList<>();

		try (Statement state = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY)){
			ResultSet result = state.executeQuery(
					"SELECT id_option FROM vehicule_option WHERE id_vehicule = "
							+ id);
			while (result.next())
				list.add(new OptionDAO(this.connect).find(result
						.getInt("id_option")));
		} catch (SQLException e) {
			new DAOException("TypeMoteurDAO : " + e.getMessage());
		}

		return list;
	}
}
