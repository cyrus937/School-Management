package Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ModelDAO.Model.Classe;
import ModelDAO.Model.ClasseDAO;
import ModelDAO.Model.Data;
import ModelDAO.Model.DataDAO;
import ModelDAO.Model.Eleve;
import ModelDAO.Model.EleveDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InscrireController implements Initializable{

    @FXML
    private TextField matricule;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private ComboBox<String> classe;

    ArrayList<Classe> listClasses;
    
    ClasseDAO classeDAO;
    
    String annee_Scolaire = "";
    
    public static Stage win;
    
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		DataDAO dataDAO = new DataDAO();
		Data data = dataDAO.find("annee_Scolaire");
		annee_Scolaire = data.getValeur();
		dataDAO.close();
		
		classeDAO = new ClasseDAO();
		listClasses = classeDAO.findAll(annee_Scolaire);
		for (Classe classes : listClasses) {
			classe.getItems().add(classes.getNom());
		}
		classeDAO.close();
		
	}

    public void Inscrire() {
    	Classe idClasse = null;
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy/MM/dd HH:mm:ss");
    	LocalDateTime now = LocalDateTime.now();
    	
    	String present = formatter.format(now);
    	
    	String mat = matricule.getText();
    	String name = nom.getText();
    	String surename = prenom.getText();
    	for (Classe classes : listClasses) {
			if (classes.getNom().equals(classe.getValue()))
			{
				idClasse = classes;
				break;
			}
		}
    	
    	EleveDAO eleveDAO = new EleveDAO();
    	
    	Eleve eleve = null;
    	eleve = eleveDAO.find(mat, annee_Scolaire);
    	
    	if (eleve != null) {
    		EleveController.win.close();
    		try {
    			Gestion_Ecole.Gestion_Ecole.messageErreur = "Le matricule que vous avez entre \nest deja utilise\nChangez le matricule de l'eleve entre.";
    			AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/erreur.fxml"));

    			win = new Stage();
    			win.initModality(Modality.APPLICATION_MODAL);
    						
    			Scene scene = new Scene(root);								
    			win.setScene(scene);
    			win.showAndWait();

    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	
    	else {
    		eleveDAO.create(new Eleve(mat, name, surename, present, 0, idClasse.getPension(), annee_Scolaire, 1, idClasse));
        	eleveDAO.close();
        	
        	idClasse.setNbEleve(idClasse.getNbEleve()+1);
        	idClasse.setMontantRestant(idClasse.getMontantRestant()+idClasse.getPension());
        	ClasseDAO classeDAO = new ClasseDAO();
        	classeDAO.update(idClasse);
        	
        	EleveController.win.close();
        	
    	}
    	
    }

}
