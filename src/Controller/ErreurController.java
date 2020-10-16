package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ErreurController implements Initializable{

    @FXML
    private Label message;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		message.setText(Gestion_Ecole.Gestion_Ecole.messageErreur);
	}

}
