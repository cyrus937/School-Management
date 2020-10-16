package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ModelDAO.Model.BudgetPrevisionnel;
import ModelDAO.Model.BudgetPrevisionnelDAO;
import ModelDAO.Model.Classe;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NouvelleAnneeController implements Initializable {

    @FXML
    private TextField annee2;

    @FXML
    private TextField annee1;

    @FXML
    private CheckBox check_oui;

    @FXML
    private Button commencer;

    public static String anneeScolaire;

    public static BudgetPrevisionnel budgetPrevisionnel;

    public static ArrayList<Classe> listClasses = new ArrayList<Classe>();

    public static Stage win;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        commencer.setDisable(true);
        annee1.setDisable(true);
        annee2.setDisable(true);
    }

    public void Check() {
        if (check_oui.isSelected()) {
            commencer.setDisable(false);
            annee1.setDisable(false);
            annee2.setDisable(false);
        } else {
            commencer.setDisable(true);
            annee1.setDisable(true);
            annee2.setDisable(true);
        }
    }

    public void Commencer() {
        BudgetPrevisionnelDAO budgetPrevisionnelDAO = new BudgetPrevisionnelDAO();
        anneeScolaire = annee1.getText() + "/" + annee2.getText();

        if (!budgetPrevisionnelDAO.searchAnnee(anneeScolaire)) {
            int max = budgetPrevisionnelDAO.Max();
            budgetPrevisionnel = new BudgetPrevisionnel(max + 1, anneeScolaire, 1);

            IntroAcceuilController.next = "next";
            IntroAcceuilController.win.close();
        } else {
            IntroAcceuilController.win.close();
            try {
                Gestion_Ecole.Gestion_Ecole.messageErreur = "Cette année scolaire existe déjà \nVerifie saisir la bonne année scolaire.";
                AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/erreur.fxml"));

                win = new Stage();
                win.setTitle("Error");
                win.initModality(Modality.APPLICATION_MODAL);

                Scene scene = new Scene(root);
                win.setScene(scene);
                win.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        budgetPrevisionnelDAO.close();
    }

}
