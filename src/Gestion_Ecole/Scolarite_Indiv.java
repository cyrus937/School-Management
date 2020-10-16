package Gestion_Ecole;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Scolarite_Indiv {

	private SimpleStringProperty matricule;
	private SimpleStringProperty nom;
	private SimpleStringProperty classe;
	private SimpleStringProperty date;
	private SimpleIntegerProperty montantPaye;
	private SimpleIntegerProperty montantRestant;
	
	public Scolarite_Indiv(String Matricule, String nom, String classe, String date, int paye, int restant)
	{
		 this.matricule = new SimpleStringProperty(Matricule);
		 this.nom = new SimpleStringProperty(nom);
		 this.classe = new SimpleStringProperty(classe);
		 this.date = new SimpleStringProperty(date);
		 this.montantPaye = new SimpleIntegerProperty(paye);
		 this.montantRestant = new SimpleIntegerProperty(restant);
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

	public int getMontantPaye() {
		return montantPaye.get();
	}

	public void setPaye(int paye) {
		this.montantPaye.set(paye);
	}

	public int getMontantRestant() {
		return montantRestant.get();
	}

	public void setMontantRestant(int restant) {
		this.montantRestant.set(restant);
	}
	
}
