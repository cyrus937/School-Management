package Gestion_Ecole;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Gestion_Ecole extends Application{
	
	public static Stage wind;
	
	public static Stage window;
	
	public static String messageErreur = "";
	
	public static void main(String[] args) {
		Application.launch();

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//createConnection();
		wind = primaryStage;
		wind.setResizable(false);
		wind.initStyle(StageStyle.UNDECORATED);
		
		wind.getIcons().add(new Image("/Images/logo1.jpg"));
		Parent root = FXMLLoader.load(getClass().getResource("/Interface/chargement.fxml"));
		
		Scene scene = new Scene(root);
		
		wind.setScene(scene);
		wind.show();
		
	}

}
