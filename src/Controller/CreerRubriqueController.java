package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Gestion_Ecole.Rubriques;
import ModelDAO.Model.Rubrique;
import ModelDAO.Model.RubriqueDAO;
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

public class CreerRubriqueController implements Initializable{

	@FXML
	private AnchorPane pane;
	 
    @FXML
    private TextField recherche;

    @FXML
    private ComboBox<String> type;

    @FXML
    private TableView<Rubriques> table;

    @FXML
    private TableColumn<Rubriques, Integer> id;

    @FXML
    private TableColumn<Rubriques, String> rubrique;

    @FXML
    private TableColumn<Rubriques, String> categorie;

    public ObservableList<Rubriques> listRubrique = FXCollections.observableArrayList ();
    
    public ArrayList<Rubriques> newRubrique;
    
    int max = 0;
    
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		type.getItems().addAll("Actif", "Passif");
		
		
		RubriqueDAO rubriqueDAO = new RubriqueDAO();
		ArrayList<Rubrique> rubriques = rubriqueDAO.findAll();
		rubriqueDAO.close();
		
		for (Rubrique rub : rubriques) {
			listRubrique.add(new Rubriques(rub.getIdRubrique(), rub.getNom(), rub.getType().toLowerCase()));
		}
		
		this.RemplirTable();
		max = listRubrique.size();
		newRubrique = new ArrayList<Rubriques>();
	}

	public void Valider() {
		max++;
		listRubrique.add(new Rubriques(max, recherche.getText(), type.getValue().toLowerCase()));
		newRubrique.add(new Rubriques(max, recherche.getText(), type.getValue().toLowerCase()));
		this.RemplirTable();
		recherche.clear();
	}
	
	public void Suivant() {
		try {
			RubriqueDAO rubriqueDAO = new RubriqueDAO();
			
			for (Rubriques rub : newRubrique) {
				rubriqueDAO.create(new Rubrique(rub.getNom(), rub.getType()));
			}
			rubriqueDAO.close();
			
			AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/creerBudgetPrevisionnel.fxml"));
			pane.getChildren().setAll(root);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void RemplirTable() {
		
		id.setCellValueFactory(new PropertyValueFactory<>("idRubrique"));
		rubrique.setCellValueFactory(new PropertyValueFactory<>("nom"));
		categorie.setCellValueFactory(new PropertyValueFactory<>("type"));
		
		table.setItems(listRubrique);
	}

	public void Retour() {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/creerClasse.fxml"));
			pane.getChildren().setAll(root);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
