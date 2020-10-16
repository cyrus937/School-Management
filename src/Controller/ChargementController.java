package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ModelDAO.Model.Data;
import ModelDAO.Model.DataDAO;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class ChargementController implements Initializable{

	@FXML
    private ProgressBar progress;
	
	@FXML
    private Label info;
	
	Timeline counter;
	
	public int i = 0;
	
	public int j = 0;
	
	public static Stage win;
	
	public String firstTime = "";
	
	String authentifie = "";
	
	int nombre = 0;
	
	DataDAO dataDAO;
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		dataDAO = new DataDAO();
		firstTime =  dataDAO.find("firstTime").getValeur();
		authentifie = dataDAO.find("valider").getValeur();
		nombre = Integer.parseInt(dataDAO.find("nombre").getValeur());
		
		counter = new Timeline(new KeyFrame(Duration.millis(50), e -> UpdatePercentage()));
		counter.setCycleCount(Timeline.INDEFINITE);
		counter.play();
	}

	public void UpdatePercentage()
	{
		if (i<=100)
		{
			progress.setProgress(i/100.0);
			i++;
			if (j>=30)
				j=0;
			if (j/10==0)
				info.setText("Loading Information.");
			else if (j/10==1)
				info.setText("Loading Information..");
			else if (j/10==2)
				info.setText("Loading Information...");
			j++;
		}	
		else
		{
			counter.stop();
			Gestion_Ecole.Gestion_Ecole.wind.close();
			
			if (firstTime.equals("Vrai"))
			{
				try {
					
					AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/entrerEcole.fxml"));
						
					win = new Stage();
					win.initStyle(StageStyle.UNDECORATED);
					win.getIcons().add(new Image("/Images/logo1.jpg"));
					
					Scene scene = new Scene(root);
					win.setScene(scene);
					win.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				try {
					
					if (authentifie.equals("Vrai"))
					{
						Gestion_Ecole.Gestion_Ecole.window = new Stage();
						Gestion_Ecole.Gestion_Ecole.window.setTitle("Gestion");
						Gestion_Ecole.Gestion_Ecole.window.setResizable(false);
						Gestion_Ecole.Gestion_Ecole.window.getIcons().add(new Image("/Images/logo1.jpg"));
						
						BorderPane root = FXMLLoader.load(getClass().getResource("/Interface/acceuil.fxml"));
						Scene scene = new Scene(root);
						
						Gestion_Ecole.Gestion_Ecole.window.setScene(scene);
						Gestion_Ecole.Gestion_Ecole.window.show();
					}
					else
					{
						if (nombre > 0) {
							nombre--;
							Data data = dataDAO.find("nombre");
							data.setValeur(nombre+"");
							dataDAO.update(data);
							dataDAO.close();
							
							Gestion_Ecole.Gestion_Ecole.window = new Stage();
							Gestion_Ecole.Gestion_Ecole.window.setTitle("Gestion");
							Gestion_Ecole.Gestion_Ecole.window.setResizable(true);
							Gestion_Ecole.Gestion_Ecole.window.getIcons().add(new Image("/Images/logo1.jpg"));
							
							BorderPane root = FXMLLoader.load(getClass().getResource("/Interface/acceuil.fxml"));
							Scene scene = new Scene(root);
							
							Gestion_Ecole.Gestion_Ecole.window.setScene(scene);
							Gestion_Ecole.Gestion_Ecole.window.show();
						}
						else {
							Gestion_Ecole.Gestion_Ecole.window = new Stage();
							Gestion_Ecole.Gestion_Ecole.window.setTitle("Gestion");
							Gestion_Ecole.Gestion_Ecole.window.setResizable(true);
							Gestion_Ecole.Gestion_Ecole.window.getIcons().add(new Image("/Images/logo1.jpg"));
							
							AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/codeActivation.fxml"));
							Scene scene = new Scene(root);
							
							Gestion_Ecole.Gestion_Ecole.window.setScene(scene);
							Gestion_Ecole.Gestion_Ecole.window.show();
						}
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
							
		}
	}
}
