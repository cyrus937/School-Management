package Gestion_Ecole;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Scolarite_Collectif {

	private SimpleIntegerProperty id;
	private SimpleStringProperty anneeScolaire;
	private SimpleStringProperty classe;
	private SimpleIntegerProperty septembre;
	private SimpleIntegerProperty octobre;
	private SimpleIntegerProperty novembre;
	private SimpleIntegerProperty decembre;
	private SimpleIntegerProperty janvier;
	private SimpleIntegerProperty fevrier;
	private SimpleIntegerProperty mars;
	private SimpleIntegerProperty avril;
	private SimpleIntegerProperty mai;
	private SimpleIntegerProperty juin;
	
	public Scolarite_Collectif(int id, String classe, String annee) {
		this.id = new SimpleIntegerProperty(id);
		this.classe = new SimpleStringProperty(classe);
		this.anneeScolaire = new SimpleStringProperty(annee);
		this.septembre = new SimpleIntegerProperty(0);
		this.octobre = new SimpleIntegerProperty(0);
		this.novembre = new SimpleIntegerProperty(0);
		this.decembre = new SimpleIntegerProperty(0);
		this.janvier = new SimpleIntegerProperty(0);
		this.fevrier = new SimpleIntegerProperty(0);
		this.mars = new SimpleIntegerProperty(0);
		this.avril = new SimpleIntegerProperty(0);
		this.mai = new SimpleIntegerProperty(0);
		this.juin = new SimpleIntegerProperty(0);
		
	}

	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);
	}

	public String getAnneeScolaire() {
		return anneeScolaire.get();
	}

	public void setAnneeScolaire(String anneeScolaire) {
		this.anneeScolaire.set(anneeScolaire);
	}

	public String getClasse() {
		return classe.get();
	}

	public void setClasse(String classe) {
		this.classe.set(classe);
	}

	public int getSeptembre() {
		return septembre.get();
	}

	public void setSeptembre(int septembre) {
		this.septembre.set(septembre);
	}

	public int getOctobre() {
		return octobre.get();
	}

	public void setOctobre(int octobre) {
		this.octobre.set(octobre);
	}

	public int getNovembre() {
		return novembre.get();
	}

	public void setNovembre(int novembre) {
		this.novembre.set(novembre);
	}

	public int getDecembre() {
		return decembre.get();
	}

	public void setDecembre(int decembre) {
		this.decembre.set(decembre);
	}

	public int getJanvier() {
		return janvier.get();
	}

	public void setJanvier(int janvier) {
		this.janvier.set(janvier);
	}

	public int getFevrier() {
		return fevrier.get();
	}

	public void setFevrier(int fevrier) {
		this.fevrier.set(fevrier);
	}

	public int getMars() {
		return mars.get();
	}

	public void setMars(int mars) {
		this.mars.set(mars);
	}

	public int getAvril() {
		return avril.get();
	}

	public void setAvril(int avril) {
		this.avril.set(avril);
	}

	public int getMai() {
		return mai.get();
	}

	public void setMai(int mai) {
		this.mai.set(mai);
	}

	public int getJuin() {
		return juin.get();
	}

	public void setJuin(int juin) {
		this.juin.set(juin);
	}

}
