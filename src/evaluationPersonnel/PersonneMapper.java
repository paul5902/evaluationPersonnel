package evaluationPersonnel;

import java.sql.Connection;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonneMapper {

	static Map<Integer, WeakReference<Personne>> personnes;
	private Connection connection;
	static PersonneMapper instance;

	private PersonneMapper() throws SQLException {
		try {
			this.connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fil.univ-lille1.fr:1521:filora",
					"letailleur", "saturnin");
		} catch (Exception e) {
			System.out.println(e);
		}
		personnes = new HashMap<Integer,WeakReference<Personne>>();
	}
	
	public static PersonneMapper getInstance() throws SQLException {
		if(instance == null) {
			instance = new PersonneMapper();
		}
		return instance;
	}

	public Personne getPersonne(Integer id) throws SQLException {
		if(id == null) {
			return null;
		}
		if (personnes.containsKey(id)) {
			return personnes.get(id).get();
		}
		else {
		PreparedStatement state = connection
				.prepareStatement("SELECT id,nom,prenom,evaluation FROM PERSONNE WHERE id=?");
		state.setInt(1, id);
		ResultSet result = state.executeQuery();
		result.next();
		return resultToPerson(result);
		}

	}

	public Personne resultToPerson(ResultSet result) throws SQLException {
			Personne laPersonne = new Personne(result.getInt(1), result.getString(2), result.getString(3));
			
			laPersonne.setEvaluation(result.getString(4));
			personnes.put(result.getInt(1), new WeakReference<Personne>(laPersonne));
			Personne lePere = getPersonne(getIdPere(result.getInt(1)));
			if(lePere!=null) {
				laPersonne.setLePere(lePere);
			}
			FilsFactory ff = new FilsFactory(result.getInt(1), new PersonneMapper());
			laPersonne.setLesFils(new VirtualProxyBuilder<List<Personne>>(List.class, ff).getProxy());
			laPersonne.add(UnitOfWork.getInstance());
			return laPersonne;

	}

	public ArrayList<Personne> getLesFils(int id) throws SQLException {
		ArrayList<Integer> lesIdFils = getIdFils(id);
		ArrayList<Personne> lesFils = new ArrayList<Personne>();
		for (int i = 0; i <= lesIdFils.size() - 1; i++) {
			Personne fils = getPersonne(lesIdFils.get(i));
			lesFils.add(fils);
		}
		return lesFils;
	}

	public Integer getIdPere(int id) throws SQLException {
		PreparedStatement state = connection.prepareStatement("SELECT idPere FROM LIEN_PERSONNE WHERE idFils=?");
		state.setInt(1, id);
		ResultSet result = state.executeQuery();
		if(result.next()){
			return ((BigDecimal)result.getObject(1)).intValue();
		}
		else {
			return null;
		}
		

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

	public void update(Personne p) throws SQLException {
		PreparedStatement state = connection.prepareStatement("UPDATE PERSONNE SET EVALUATION=? WHERE id=?");
		state.setString(1, p.getEvaluation());
		state.setInt(2,p.getId());
		state.executeQuery();
		
	}

}
