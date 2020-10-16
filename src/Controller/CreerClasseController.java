package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Gestion_Ecole.SalleClasse;
import ModelDAO.Model.Classe;
import ModelDAO.Model.ClasseDAO;
import ModelDAO.Model.Data;
import ModelDAO.Model.DataDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class CreerClasseController implements Initializable{

	@FXML
	private AnchorPane pane;
	 
    @FXML
    private ComboBox<String> classeCombo;

    @FXML
    private TextField classeInput;

    @FXML
    private TextField pensionInput;

    @FXML
    private TableView<SalleClasse> table;

    @FXML
    private TableColumn<SalleClasse, String> classe;

    @FXML
    private TableColumn<SalleClasse, Integer> pension;

    public ObservableList<SalleClasse> listClasses = FXCollections.observableArrayList ();
    
    ArrayList<Classe> Classes;
    
    ClasseDAO classeDAO;
    
    String annee_Scolaire = "";
    
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		DataDAO dataDAO = new DataDAO();
		Data data = dataDAO.find("annee_Scolaire");
		annee_Scolaire = data.getValeur();
		dataDAO.close();
		
		classeDAO = new ClasseDAO();
		Classes = classeDAO.findAll(annee_Scolaire);
		for (Classe classes : Classes) {
			classeCombo.getItems().add(classes.getNom());
		}
		
		this.RemplirTable();
	}
	
	public void ChoixClasse() {
		Classe idClasse = null;
		String choixClasse = classeCombo.getValue();
		
		for (Classe classes : Classes) {
			if (classes.getNom().equals(choixClasse))
			{
				idClasse = classes;
				break;
			}
		}
		
		classeInput.setText(idClasse.getNom());
		pensionInput.setText(idClasse.getPension()+"");
	}

	public void Valider() {
		if (!classeInput.getText().equals("") && !pensionInput.getText().equals(""))
		{
			try {
				listClasses.add(new SalleClasse(classeInput.getText(), 0, Integer.parseInt(pensionInput.getText()), 0, 0));
				this.RemplirTable();
			}catch(NumberFormatException e) {
				pensionInput.clear();
			}
			classeInput.clear();
			pensionInput.clear();
		}
	}
	
	public void Suivant() {
		try {
			NouvelleAnneeController.listClasses.clear();
			for (SalleClasse salle : listClasses)
			{
				NouvelleAnneeController.listClasses.add(new Classe(salle.getClasse(), salle.getNbEleve(), salle.getPension(), salle.getPaye(), salle.getRestant(), NouvelleAnneeController.anneeScolaire));
			}
			
			AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/creerRubrique.fxml"));
			pane.getChildren().setAll(root);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Annuler() {
		if (listClasses.size() > 0)
		{
			listClasses.remove(listClasses.size()-1);
			this.RemplirTable();
		}
	}

	public void RemplirTable() {
		
		classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
		pension.setCellValueFactory(new PropertyValueFactory<>("pension"));
		
		table.setItems(listClasses);
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
