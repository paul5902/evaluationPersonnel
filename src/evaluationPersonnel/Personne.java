package evaluationPersonnel;

import java.util.ArrayList;

public class Personne implements PersonneInterface {
	
	private int id;
	private String nom;
	private String prenom;
	private String evaluation;
	private Personne lePere;
	private ArrayList<Personne> lesFils;
	
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

	@Override
	public ArrayList<Personne> getLesFils() {
		return this.lesFils;
	}

	@Override
	public String getNom() {
		return this.nom;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public String getPrenom() {
		return this.prenom;
	}

	@Override
	public String getEvaluation() {		
		return this.evaluation;
	}

	@Override
	public Personne getLePere() {
		return this.lePere;
	}

}
