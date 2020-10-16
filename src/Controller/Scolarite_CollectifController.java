package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Gestion_Ecole.Scolarite_Collectif;
import ModelDAO.Model.Classe;
import ModelDAO.Model.ClasseDAO;
import ModelDAO.Model.Data;
import ModelDAO.Model.DataDAO;
import ModelDAO.Model.ScolariteCollective;
import ModelDAO.Model.ScolariteCollectiveDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class Scolarite_CollectifController implements Initializable{

	@FXML
    private AnchorPane pane;
	
    @FXML
    private TableView<Scolarite_Collectif> table;

    @FXML
    private TableColumn<Scolarite_Collectif, String> classe;

    @FXML
    private TableColumn<Scolarite_Collectif, Integer> septembre;

    @FXML
    private TableColumn<Scolarite_Collectif, Integer> octobre;

    @FXML
    private TableColumn<Scolarite_Collectif, Integer> novembre;

    @FXML
    private TableColumn<Scolarite_Collectif, Integer> decembre;

    @FXML
    private TableColumn<Scolarite_Collectif, Integer> janvier;

    @FXML
    private TableColumn<Scolarite_Collectif, Integer> fevrier;

    @FXML
    private TableColumn<Scolarite_Collectif, Integer> mars;

    @FXML
    private TableColumn<Scolarite_Collectif, Integer> avril;

    @FXML
    private TableColumn<Scolarite_Collectif, Integer> mai;

    @FXML
    private TableColumn<Scolarite_Collectif, Integer> juin;

    @FXML
    private TextField search;
    
    public ObservableList<Scolarite_Collectif> listScolarite;

    String annee_Scolaire = "";
    
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		this.RemplirTable();
		this.Filtre();
	}

	public void Filtre(){
		FilteredList<Scolarite_Collectif> filterCollectif = new FilteredList<>(listScolarite, b->true);
		
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filterCollectif.setPredicate(collectif -> {
				
				if ((newValue == null) || (newValue.isEmpty()))
					return true;
				
				String enter = newValue.toLowerCase();
				
				if (collectif.getClasse().toLowerCase().contains(enter))
					return true;
				
				return false;
			});			
		});
		
		SortedList<Scolarite_Collectif> sortedCollectif = new SortedList<>(filterCollectif);
		sortedCollectif.comparatorProperty().bind(table.comparatorProperty());
		table.setItems(sortedCollectif);
	}
	
	public void RemplirTable() {
		int i = 0;
		listScolarite = FXCollections.observableArrayList ();
		
		DataDAO dataDAO = new DataDAO();
		Data data = dataDAO.find("annee_Scolaire");
		annee_Scolaire = data.getValeur();
		dataDAO.close();
		
		ScolariteCollectiveDAO scolariteCollectiveDAO = new ScolariteCollectiveDAO();
		ArrayList<ScolariteCollective> list = scolariteCollectiveDAO.findAll_2(annee_Scolaire);
		
		ClasseDAO classeDAO = new ClasseDAO();
		for (Classe classe : classeDAO.findAll(annee_Scolaire)) {
			listScolarite.add(new Scolarite_Collectif(classe.getIdClasse(), classe.getNom(), classe.getAnneeScolaire()));
		}
		
		for (ScolariteCollective scol : list)
		{
			if (scol.getClasse().getNom().equals(listScolarite.get(i).getClasse())) {
				this.SetMois(scol.getMois(), scol.getMontantPercu(), i);
			}
			else {
				i++;
				for (int j=i; j<listScolarite.size(); j++) {
					if (scol.getClasse().getNom().equals(listScolarite.get(j).getClasse())) {
						i = j;
						this.SetMois(scol.getMois(), scol.getMontantPercu(), i);
						break;
					}
				}
			}
		}
		
		classeDAO.close();
		scolariteCollectiveDAO.close();
		
		classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
		septembre.setCellValueFactory(new PropertyValueFactory<>("septembre"));
		octobre.setCellValueFactory(new PropertyValueFactory<>("octobre"));
		novembre.setCellValueFactory(new PropertyValueFactory<>("novembre"));
		decembre.setCellValueFactory(new PropertyValueFactory<>("decembre"));
		janvier.setCellValueFactory(new PropertyValueFactory<>("janvier"));
		fevrier.setCellValueFactory(new PropertyValueFactory<>("fevrier"));
		mars.setCellValueFactory(new PropertyValueFactory<>("mars"));
		avril.setCellValueFactory(new PropertyValueFactory<>("avril"));
		mai.setCellValueFactory(new PropertyValueFactory<>("mai"));
		juin.setCellValueFactory(new PropertyValueFactory<>("juin"));
		
		table.setItems(listScolarite);
	}
	
	
	public void SetMois(String mois, int montant, int i) {
		Scolarite_Collectif collectif = listScolarite.get(i);
		
		switch (mois) 
		{
			case "Septembre":
				collectif.setSeptembre(montant);
				break;
			case "Octobre":
				collectif.setOctobre(montant);
				break;
			case "Novembre":
				collectif.setNovembre(montant);
				break;
			case "Decembre":
				collectif.setDecembre(montant);
				break;
			case "Janvier":
				collectif.setJanvier(montant);
				break;
			case "Fevrier":
				collectif.setFevrier(montant);
				break;
			case "Mars":
				collectif.setMars(montant);
				break;
			case "Avril":
				collectif.setAvril(montant);
				break;
			case "Mai":
				collectif.setMai(montant);
				break;
			case "Juin":
				collectif.setJuin(montant);
				break;
			default:
				break;
		}
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
