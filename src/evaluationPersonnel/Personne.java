package evaluationPersonnel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Personne implements IDomainObject {
	
	List<Observateur> obs;
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
		this.obs = new ArrayList<Observateur>();
	}
	
	public void add(Observateur o) {
		System.out.println("Personne: Ajout d'un observateur!");
		obs.add(o);
	}
	
	public void notifier() {
		System.out.println("Personne: event detecte! on notifie tous les observateurs...");
		for (Observateur o : obs) {
			o.action(this);
		}
	}
	
	public void setLePere(Personne pere) {
		this.lePere = pere;
	}
	
	public void addFils(Personne fils) {
		lesFils.add(fils);
	}
	
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
		notifier();
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
	
	public String toString() {
		return this.nom + " "+ this.prenom;
	}

	@Override
	public void accepter(Visiteur v) {
		v.visiter(this);
		
	}

}
