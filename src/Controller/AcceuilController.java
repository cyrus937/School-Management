package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ModelDAO.Model.DataDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;


public class AcceuilController implements Initializable{

	@FXML
    private AnchorPane pane;
    
    @FXML
    private Label abrev;

    @FXML
    private ProgressIndicator progress;
    
    
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		DataDAO dataDAO = new DataDAO();
		abrev.setText(dataDAO.find("abreviation").getValeur());
		dataDAO.close();
		
		progress.setOpacity(0);
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/intro_acceuil.fxml"));
			pane.getChildren().setAll(root);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void Acceuil() {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/intro_acceuil.fxml"));
			pane.getChildren().setAll(root);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Historique() {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/historique.fxml"));
			pane.getChildren().setAll(root);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Parametre() {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/parametre.fxml"));
			pane.getChildren().setAll(root);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void Classe() {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/salledeClasse.fxml"));
			pane.getChildren().setAll(root);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Eleve() {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/eleve.fxml"));
			pane.getChildren().setAll(root);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Frais_Individuel() {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/scolarite_Indiv.fxml"));
			pane.getChildren().setAll(root);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Frais_Collectif() {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/scolarite_Collectif.fxml"));
			pane.getChildren().setAll(root);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Budget() {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/budget_Previsionel.fxml"));
			pane.getChildren().setAll(root);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Quitter() {
		Gestion_Ecole.Gestion_Ecole.window.close();
	}

}
