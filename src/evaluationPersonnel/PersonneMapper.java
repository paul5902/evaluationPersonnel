package evaluationPersonnel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonneMapper {

	Connection connection;

	public PersonneMapper() throws SQLException {
		this.connection = DriverManager.getConnection("oracle.univ-lille1.fr", "letailleur", "saturnin");
	}

	public Personne getPersonne(int id) throws SQLException {
		PreparedStatement state = connection.prepareStatement("SELECT nom,prenom FROM PERSONNE WHERE id=?");
		state.setInt(1, id);
		ResultSet result = state.executeQuery();
		result.next();
		return new Personne(id, result.getString(1), result.getString(2));

	}

	public Personne getPersonneAvecLiens(int id) throws SQLException {

		Personne personne = this.getPersonne(id);

		Personne pere = this.getPersonne(this.getIdPere(id));

		ArrayList<Integer> idsFils = this.getIdFils(id);

		for (int i = 0; i <= idsFils.size() - 1; i++) {
			Personne fils = this.getPersonne(idsFils.get(i));
			personne.addFils(fils);
		}

		personne.setLePere(pere);

		return personne;

	}

	public int getIdPere(int id) throws SQLException {
		PreparedStatement state = connection.prepareStatement("SELECT idPere FROM LIEN_PERSONNE WHERE idFils=?");
		state.setInt(1, id);
		ResultSet result = state.executeQuery();
		result.next();
		return result.getInt(1);

	}

	public ArrayList<Integer> getIdFils(int id) throws SQLException {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		PreparedStatement state = connection.prepareStatement("SELECT idFils FROM LIEN_PERSONNE WHERE idPere=?");
		state.setInt(1, id);
		ResultSet result = state.executeQuery();

		while (result.next()) {
			ids.add(result.getInt(1));
		}

		return ids;

	}

}
