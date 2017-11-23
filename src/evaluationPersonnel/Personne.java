package evaluationPersonnel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Personne  {
	
	private int id;
	private String nom;
	private String prenom;
	private String evaluation;
	private Personne lePere;
	private List<Personne> lesFils;
	
	public Personne(int id, String nom, String prenom) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.lesFils = new ArrayList<Personne>();
	}
	
	public void setLePere(Personne pere) {
		this.lePere = pere;
	}
	
	public void addFils(Personne fils) {
		lesFils.add(fils);
	}
	
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public List<Personne> getLesFils(){
		return lesFils;
	}

	public String getNom() {
		return this.nom;
	}

	public int getId() {
		return this.id;
	}
	
	public void setLesFils(List<Personne> lesFils) {
		this.lesFils = lesFils;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public String getEvaluation() {		
		return this.evaluation;
	}

	public Personne getLePere() {
		return this.lePere;
	}
	
	public String getIdentite() {
		return this.nom + " " + this.prenom;
	}

}
