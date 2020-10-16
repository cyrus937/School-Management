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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CodeActivationController implements Initializable{

    @FXML
    private Button commencer;

    @FXML
    private TextField code1;

    @FXML
    private TextField code2;

    @FXML
    private TextField code3;
    
    @FXML
    private Label erreur;
    
    String code;
    
    int codeTime = 0;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		erreur.setOpacity(0);
		
	}
	
	public void Valider() {
		code = code1.getText() + "-" + code2.getText() + "-" + code3.getText();
		DataDAO dataDAO = new DataDAO();
		Data data = dataDAO.find("code1");
		
		if (data.getValeur().equals("OK")) {
			codeTime++;
			data = dataDAO.find("code2");
			
			if (data.getValeur().equals("OK")) {
				ParametreController.win.close();
				ParametreController.param = false;
			}
		}
		
		if (code.equals(data.getValeur()))
		{
			data.setValeur("OK");
			dataDAO.update(data);
			if (codeTime == 0) {
				data = dataDAO.find("nombre");
				data.setValeur(15+"");
				dataDAO.update(data);
			}
			else {
				data = dataDAO.find("valider");
				data.setValeur("Vrai");
				dataDAO.update(data);
			}
			dataDAO.close();
			
			try {
				if (ParametreController.param) {
					ParametreController.win.close();
					ParametreController.param = false;
				}
				else {
					Gestion_Ecole.Gestion_Ecole.window.close();
					Gestion_Ecole.Gestion_Ecole.window = new Stage();
					Gestion_Ecole.Gestion_Ecole.window.setTitle("Gestion");
					Gestion_Ecole.Gestion_Ecole.window.setResizable(true);
					Gestion_Ecole.Gestion_Ecole.window.getIcons().add(new Image("/Images/logo1.jpg"));
					
					BorderPane root = FXMLLoader.load(getClass().getResource("/Interface/acceuil.fxml"));
					Scene scene = new Scene(root);
					
					Gestion_Ecole.Gestion_Ecole.window.setScene(scene);
					Gestion_Ecole.Gestion_Ecole.window.show();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			erreur.setOpacity(1);
		}
	}


}
