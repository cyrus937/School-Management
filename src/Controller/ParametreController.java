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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ParametreController implements Initializable{

    @FXML
    private Label annee;

    public static Stage win;
    
    public static boolean param = false;
    
    public static boolean changerEcole = false;
    
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		DataDAO dataDAO = new DataDAO();
		Data data = dataDAO.find("annee_Scolaire");
		annee.setText(data.getValeur());
	}

    public void ChangerEcole() {
    	changerEcole = true;
    	
    	try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/entrerEcole.fxml"));

			win = new Stage();
			win.initModality(Modality.APPLICATION_MODAL);
						
			Scene scene = new Scene(root);								
			win.setScene(scene);
			win.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void CodeActivation() {
    	param = true;
    	
    	try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/codeActivation.fxml"));

			win = new Stage();
			win.initModality(Modality.APPLICATION_MODAL);
						
			Scene scene = new Scene(root);								
			win.setScene(scene);
			win.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
}
