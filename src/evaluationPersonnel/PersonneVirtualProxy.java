package evaluationPersonnel;

import java.sql.SQLException;
import java.util.ArrayList;

public class PersonneVirtualProxy implements PersonneInterface {

	private Personne personne;
	private int id;
	
	public PersonneVirtualProxy(int id) {
		this.id = id;
	}

	public Personne getPersonne() throws SQLException {
		if (personne == null) {
			personne = new PersonneMapper().getPersonne(this.id);
		}
		return personne;
	}

	@Override
	public String getNom() throws SQLException {
		if (personne == null) {
			personne = new PersonneMapper().getPersonne(this.id);
		}
		return personne.getNom();
	}

	@Override
	public int getId() throws SQLException {
		return this.id;
	}

	@Override
	public String getPrenom() throws SQLException {
		if (personne == null) {
			personne = new PersonneMapper().getPersonne(this.id);
		}
		return personne.getPrenom();
	}

	@Override
	public String getEvaluation() throws SQLException {
		if (personne == null) {
			personne = new PersonneMapper().getPersonne(this.id);
		}
		return personne.getEvaluation();
	}

	@Override
	public Personne getLePere() throws SQLException {
		if (personne == null) {
			personne = new PersonneMapper().getPersonne(this.id);
		}
		return personne.getLePere();
	}

	@Override
	public ArrayList<Personne> getLesFils() throws SQLException {
		if (personne == null) {
			personne = new PersonneMapper().getPersonne(this.id);
		}
		return personne.getLesFils();
	}

}
