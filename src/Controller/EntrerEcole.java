package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ModelDAO.Model.Data;
import ModelDAO.Model.DataDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EntrerEcole implements Initializable{

	@FXML
    private TextField nom;

    @FXML
    private TextField abreviation;

    
    
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		
	}

	public void Valider() {
		
		try {
			DataDAO dataDAO = new DataDAO();
			Data ecole = dataDAO.find("ecole");
			Data abrev = dataDAO.find("abreviation");
			Data first = dataDAO.find("firstTime");

			ecole.setValeur(nom.getText());
			abrev.setValeur(abreviation.getText());
                        first.setValeur("Faux");
                        
			dataDAO.update(ecole);
			dataDAO.update(abrev);
                        dataDAO.update(first);
			dataDAO.close();
			
			if (ParametreController.changerEcole) {
				ParametreController.win.close();
				ParametreController.changerEcole = false;
			}
			else {
				Gestion_Ecole.Gestion_Ecole.window = new Stage();
				Gestion_Ecole.Gestion_Ecole.window.setTitle("Gestion");
				Gestion_Ecole.Gestion_Ecole.window.setResizable(false);
				Gestion_Ecole.Gestion_Ecole.window.getIcons().add(new Image("/Images/logo1.jpg"));
				
				BorderPane root = FXMLLoader.load(getClass().getResource("/Interface/acceuil.fxml"));
				Scene scene = new Scene(root);
				ChargementController.win.close();
				
				Gestion_Ecole.Gestion_Ecole.window.setScene(scene);
				Gestion_Ecole.Gestion_Ecole.window.show();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
