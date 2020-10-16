package Gestion_Ecole;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Rubriques {

	private SimpleIntegerProperty idRubrique;
	private SimpleStringProperty nom;
	private SimpleStringProperty type;
	
	public Rubriques (int id, String nom, String type) {
		this.idRubrique = new SimpleIntegerProperty(id);
		this.nom = new SimpleStringProperty(nom);
		this.type = new SimpleStringProperty(type);
	}

	public int getIdRubrique() {
		return idRubrique.get();
	}

	public void setIdRubrique(int idRubrique) {
		this.idRubrique.set(idRubrique);
	}

	public String getNom() {
		return nom.get();
	}

	public void setNom(String nom) {
		this.nom.set(nom);
	}

	public String getType() {
		return type.get();
	}

	public void setType(String type) {
		this.type.set(type);
	}
	
}
