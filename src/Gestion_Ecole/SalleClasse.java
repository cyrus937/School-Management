package Gestion_Ecole;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SalleClasse {

	private SimpleStringProperty classe;
	private SimpleIntegerProperty nbEleve;
	private SimpleIntegerProperty pension;
	private SimpleIntegerProperty paye;
	private SimpleIntegerProperty restant;
	
	public SalleClasse(String classe,int nbEleve, int pension, int paye, int restant)
	{
		 this.classe = new SimpleStringProperty(classe);
		 this.nbEleve = new SimpleIntegerProperty(nbEleve);
		 this.pension = new SimpleIntegerProperty(pension);
		 this.paye = new SimpleIntegerProperty(paye);
		 this.restant = new SimpleIntegerProperty(restant);
	}

	
	public String getClasse() {
		return classe.get();
	}

	public void setClasse(String classe) {
		this.classe.set(classe);
	}

	public int getNbEleve() {
		return nbEleve.get();
	}

	public void setNbEleve(int nbEleve) {
		this.nbEleve.set(nbEleve);
	}

	public int getPension() {
		return pension.get();
	}

	public void setPension(int pension) {
		this.restant.set(pension);
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
