package evaluationPersonnel;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PersonneInterface {
	
	public ArrayList<Personne> getLesFils() throws SQLException;
	public String getNom() throws SQLException;
	public int getId() throws SQLException;
	public String getPrenom() throws SQLException;
	public String getEvaluation() throws SQLException;
	public Personne getLePere() throws SQLException;

}
