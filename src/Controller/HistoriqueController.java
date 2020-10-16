package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ModelDAO.Model.BudgetPrevisionnel;
import ModelDAO.Model.BudgetPrevisionnelDAO;
import ModelDAO.Model.Data;
import ModelDAO.Model.DataDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class HistoriqueController implements Initializable{

    @FXML
    private AnchorPane pane;

    @FXML
    private AnchorPane info;

    @FXML
    private Label annee;

    @FXML
    private ComboBox<String> comboAnnee;

    public String annee_Scolaire;
    
    DataDAO dataDAO;
    
    Data data;
    
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		info.setOpacity(0);
		
		dataDAO = new DataDAO();
		data = dataDAO.find("annee_Scolaire");
		annee_Scolaire = data.getValeur();
		annee.setText(annee_Scolaire);
		
		comboAnnee.getItems().add(annee_Scolaire);
		BudgetPrevisionnelDAO budgetPrevisionnelDAO = new BudgetPrevisionnelDAO();
		for (BudgetPrevisionnel l : budgetPrevisionnelDAO.findAll_2()) {
			if (!l.getAnneeScolaire().equals(annee_Scolaire))
				comboAnnee.getItems().add(l.getAnneeScolaire());
		}
		
	}
    
	public void Valider() {
		String newAnnee = comboAnnee.getValue();
		annee.setText(newAnnee);
		
		data.setValeur(newAnnee);;
		dataDAO.update(data);
		
		info.setOpacity(1);
	}
	
	public void Retour() {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/intro_acceuil.fxml"));
			pane.getChildren().setAll(root);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
}