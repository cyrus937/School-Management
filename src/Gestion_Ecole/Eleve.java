package Gestion_Ecole;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Eleve {

	private SimpleStringProperty matricule;
	private SimpleStringProperty nom;
	private SimpleStringProperty prenom;
	private SimpleStringProperty classe;
	private SimpleStringProperty date;
	private SimpleIntegerProperty paye;
	private SimpleIntegerProperty restant;
	
	public Eleve(String Matricule,String nom, String prenom,String classe,String date, int paye, int restant)
	{
		 this.matricule = new SimpleStringProperty(Matricule);
		 this.nom = new SimpleStringProperty(nom);
		 this.prenom = new SimpleStringProperty(prenom);
		 this.classe = new SimpleStringProperty(classe);
		 this.date = new SimpleStringProperty(date);
		 this.paye = new SimpleIntegerProperty(paye);
		 this.restant = new SimpleIntegerProperty(restant);
	}

	public String getMatricule() {
		return matricule.get();
	}

	public void setMatricule(String matricule) {
		this.matricule.set(matricule);
	}

	public String getNom() {
		return nom.get();
	}

	public void setNom(String nom) {
		this.nom.set(nom);
	}

	public String getPrenom() {
		return prenom.get();
	}

	public void setPrenom(String prenom) {
		this.prenom.set(prenom);
	}

	public String getClasse() {
		return classe.get();
	}

	public void setClasse(String classe) {
		this.classe.set(classe);
	}

	public String getDate() {
		return date.get();
	}

	public void setDate(String date) {
		this.date.set(date);
	}

	public int getPaye() {
		return paye.get();
	}

	public void setPaye(int paye) {
		this.paye.set(paye);
	}

	public int getRestant() {
		return restant.get();
	}

	public void setRestant(int restant) {
		this.restant.set(restant);
	}
	
	
}
