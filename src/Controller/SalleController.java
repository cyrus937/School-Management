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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class SalleController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<SalleClasse> table;

    @FXML
    private TableColumn<SalleClasse, String> classe;

    @FXML
    private TableColumn<SalleClasse, Integer> nbEleve;

    @FXML
    private TableColumn<SalleClasse, Integer> pension;

    @FXML
    private TableColumn<SalleClasse, Integer> montantRecu;

    @FXML
    private TableColumn<SalleClasse, Integer> montantRestant;

    @FXML
    private TextField search;

    @FXML
    private ComboBox<String> trier;

    public ObservableList<SalleClasse> listSalle;
    
    String annee_Scolaire = "";
    
    public ArrayList<Classe> listClasses = null;
    
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		trier.getItems().addAll("Tout", "Pension Terminee", "Pension Incomplete");
		
		this.RemplirTable();
		this.Filtre();
	}

	public void Filtre() {
		FilteredList<SalleClasse> filterSalle = new FilteredList<>(listSalle, b->true);
		
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filterSalle.setPredicate(eleve -> {
				
				if ((newValue == null) || (newValue.isEmpty()))
					return true;
				
				String enter = newValue.toLowerCase();
				
				if (eleve.getClasse().toLowerCase().contains(enter))
					return true;
				if ((eleve.getPaye()+"").contains(enter))
					return true;
				if ((eleve.getRestant()+"").contains(enter))
					return true;
				if ((eleve.getNbEleve()+"").contains(enter))
					return true;
				if ((eleve.getPension()+"").contains(enter))
					return true;
				return false;
			});			
		});
		
		SortedList<SalleClasse> sortedSalle = new SortedList<>(filterSalle);
		sortedSalle.comparatorProperty().bind(table.comparatorProperty());
		table.setItems(sortedSalle);
		
	}
	
	public void RemplirTable() {
		listSalle = FXCollections.observableArrayList ();
		
		DataDAO dataDAO = new DataDAO();
		Data data = dataDAO.find("annee_Scolaire");
		String annee_Scolaire = data.getValeur();
		
		ClasseDAO classeDAO = new ClasseDAO();
		ArrayList<Classe> listClasses = classeDAO.findAll(annee_Scolaire);
		for (Classe classe : listClasses) {
			listSalle.add(new SalleClasse(classe.getNom(), classe.getNbEleve(), classe.getPension(), classe.getMontantRecu(), classe.getMontantRestant()));
		}
		
		classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
		nbEleve.setCellValueFactory(new PropertyValueFactory<>("nbEleve"));
		pension.setCellValueFactory(new PropertyValueFactory<>("pension"));
		montantRecu.setCellValueFactory(new PropertyValueFactory<>("paye"));
		montantRestant.setCellValueFactory(new PropertyValueFactory<>("restant"));
		
		table.setItems(listSalle);
	}
	
	public void Triage() {
		
		listSalle = FXCollections.observableArrayList ();
		
		DataDAO dataDAO = new DataDAO();
		Data data = dataDAO.find("annee_Scolaire");
		annee_Scolaire = data.getValeur();
		dataDAO.close();
		
		ClasseDAO classeDAO = new ClasseDAO();
		if (trier.getValue().equals("Pension Terminee"))
			listClasses = classeDAO.findAll_1(annee_Scolaire);
		else if (trier.getValue().equals("Pension Incomplete"))
			listClasses = classeDAO.findAll_2(annee_Scolaire);
		else
			listClasses = classeDAO.findAll(annee_Scolaire);
		
		for (Classe classe : listClasses) {
			listSalle.add(new SalleClasse(classe.getNom(), classe.getNbEleve(), classe.getPension(), classe.getMontantRecu(), classe.getMontantRestant()));
		}
		classeDAO.close();
		
		classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
		nbEleve.setCellValueFactory(new PropertyValueFactory<>("nbEleve"));
		pension.setCellValueFactory(new PropertyValueFactory<>("pension"));
		montantRecu.setCellValueFactory(new PropertyValueFactory<>("paye"));
		montantRestant.setCellValueFactory(new PropertyValueFactory<>("restant"));
		
		table.setItems(listSalle);
		
		Filtre();
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

