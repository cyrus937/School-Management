package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ModelDAO.Model.DataDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class IntroAcceuilController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private Label ecole;

    @FXML
    private Label annee;

    public static Stage win;

    public static String next = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DataDAO dataDAO = new DataDAO();
        annee.setText(dataDAO.find("annee_Scolaire").getValeur());
        ecole.setText(dataDAO.find("ecole").getValeur());
        dataDAO.close();
    }

    public void NouvelleAnnee() {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/nouvelleAnnee.fxml"));

            win = new Stage();
            win.initStyle(StageStyle.UNDECORATED);

            Scene scene = new Scene(root);
            win.setScene(scene);
            win.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (next.equals("next")) {
            try {
                AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/creerClasse.fxml"));
                pane.getChildren().setAll(root);
                next = "";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
