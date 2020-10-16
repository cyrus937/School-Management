package Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Gestion_Ecole.Scolarite_Indiv;
import ModelDAO.Model.Classe;
import ModelDAO.Model.ClasseDAO;
import ModelDAO.Model.Data;
import ModelDAO.Model.DataDAO;
import ModelDAO.Model.Eleve;
import ModelDAO.Model.EleveDAO;
import ModelDAO.Model.ScolariteCollective;
import ModelDAO.Model.ScolariteCollectiveDAO;
import ModelDAO.Model.ScolariteIndividuel;
import ModelDAO.Model.ScolariteIndividuelDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

//import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

public class Scolarite_IndivController implements Initializable{

    @FXML
    private AnchorPane pane;

    @FXML
    private AnchorPane result;
    
    @FXML
    private TableView<Scolarite_Indiv> table;

    @FXML
    private TableColumn<Scolarite_Indiv, String> matricule;

    @FXML
    private TableColumn<Scolarite_Indiv, String> nom;

    @FXML
    private TableColumn<Scolarite_Indiv, String> classe;

    @FXML
    private TableColumn<Scolarite_Indiv, String> date;

    @FXML
    private TableColumn<Scolarite_Indiv, Integer> paye;

    @FXML
    private TableColumn<Scolarite_Indiv, Integer> restant;

    @FXML
    private TextField search;

    @FXML
    private TextField recherche;

    @FXML
    private TextField matriculeText;

    @FXML
    private TextField nomText;

    @FXML
    private TextField classeText;

    @FXML
    private TextField restantText;

    @FXML
    private TextField paiment;

    @FXML
    private Label montantLabel;
    
    public ArrayList<String> nomPrenom = new ArrayList<String>();
    
    ArrayList<Eleve> listEleves;
    
    public static Stage win;
    
    public ObservableList<Scolarite_Indiv> list;
    
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		result.setOpacity(0);
		this.RemplirTable();
		
		DataDAO dataDAO = new DataDAO();
		Data data = dataDAO.find("annee_Scolaire");
		String annee_Scolaire = data.getValeur();
		dataDAO.close();
		
		EleveDAO eleveDAO = new EleveDAO();
		listEleves = eleveDAO.findAll(annee_Scolaire);
		for (Eleve eleve : listEleves) {
			nomPrenom.add(eleve.getNom() + " " + eleve.getPrenom());
		}
		eleveDAO.close();
		
		TextFields.bindAutoCompletion(recherche, nomPrenom);
		
		recherche.setOnKeyPressed(event ->{
			Eleve eleve = null;
			switch(event.getCode())
			{
			case ENTER:
				if (!recherche.getText().equals("") && nomPrenom.indexOf(recherche.getText())!=-1)
				{
					eleve = listEleves.get(nomPrenom.indexOf(recherche.getText()));
					matriculeText.setText(eleve.getMatricule());
					nomText.setText(eleve.getNom() + " " + eleve.getPrenom());
					classeText.setText(eleve.getClasse().getNom());
					restantText.setText(eleve.getMontantRestant()+"");
				}
				else {
					try {
						AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/erreurEleve.fxml"));

						win = new Stage();
						win.initModality(Modality.APPLICATION_MODAL);
									
						Scene scene = new Scene(root);								
						win.setScene(scene);
						win.showAndWait();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				break;
			default:
				break;
		}});
		
		this.Filter();
		
	}

	public void Filter() {
		FilteredList<Scolarite_Indiv> filterScolarite = new FilteredList<>(list, b->true);
		
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filterScolarite.setPredicate(scolarite -> {
				
				if ((newValue == null) || (newValue.isEmpty()))
					return true;
				
				String enter = newValue.toLowerCase();
				
				if (scolarite.getMatricule().toLowerCase().contains(enter))
					return true;
				if (scolarite.getNom().toLowerCase().contains(enter))
					return true;
				if (scolarite.getClasse().toLowerCase().contains(enter))
					return true;
				if (scolarite.getDate().toLowerCase().contains(enter))
					return true;
				if ((scolarite.getMontantPaye()+"").contains(enter))
					return true;
				if ((scolarite.getMontantRestant()+"").contains(enter))
					return true;
				return false;
			});			
		});
		
		SortedList<Scolarite_Indiv> sortedScolarite = new SortedList<>(filterScolarite);
		sortedScolarite.comparatorProperty().bind(table.comparatorProperty());
		table.setItems(sortedScolarite);
	}
	
	public void Terminer() {
		if (nomPrenom.indexOf(recherche.getText())!=-1)
		{
			Eleve eleve = listEleves.get(nomPrenom.indexOf(recherche.getText()));
			int paiement =  Integer.parseInt(paiment.getText());
			
			if (eleve.getMontantRestant() >= paiement)
			{
				DataDAO dataDAO = new DataDAO();
				Data data = dataDAO.find("annee_Scolaire");
				String annee_Scolaire = data.getValeur();
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy/MM/dd HH:mm:ss");
		    	LocalDateTime now = LocalDateTime.now();
		    	String present = formatter.format(now);
		    	
				eleve.setMontantPaye(eleve.getMontantPaye() + paiement);
				eleve.setMontantRestant(eleve.getMontantRestant() - paiement);
				EleveDAO eleveDAO = new EleveDAO();
				eleveDAO.update(eleve);
				montantLabel.setText(eleve.getMontantRestant()+" FCFA");
				
				Classe classe = eleve.getClasse();
				classe.setMontantRecu(classe.getMontantRecu() + paiement);
				classe.setMontantRestant(classe.getMontantRestant() - paiement);
				ClasseDAO classeDAO = new ClasseDAO();
				classeDAO.update(classe);
				
				ScolariteIndividuelDAO scolariteIndividuelDAO = new ScolariteIndividuelDAO();
				scolariteIndividuelDAO.create(new ScolariteIndividuel(present, paiement, eleve.getMontantRestant(), annee_Scolaire, 1, eleve));
				
				String month = this.ConvertMonthFrench(now.getMonth().toString().substring(0, 1)+ now.getMonth().toString().substring(1).toLowerCase());
				ScolariteCollectiveDAO scolariteCollectiveDAO = new ScolariteCollectiveDAO();
				scolariteCollectiveDAO.create(new ScolariteCollective(month, paiement, annee_Scolaire, classe));
				
				classeDAO.close();
				eleveDAO.close();
				scolariteIndividuelDAO.close();
				scolariteCollectiveDAO.close();
				
				result.setOpacity(1);
			}
			else {
				try {
					Gestion_Ecole.Gestion_Ecole.messageErreur = "Le paiement effectue est superieur au \nmontant restant a payer par l'eleve. \nVerifie la somme entree.";
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
			this.RemplirTable();
		}
	}
	
	public void OK() {
		if (!recherche.getText().equals("") && nomPrenom.indexOf(recherche.getText())!=-1)
		{
			Eleve eleve = null;
			eleve = listEleves.get(nomPrenom.indexOf(recherche.getText()));
			matriculeText.setText(eleve.getMatricule());
			nomText.setText(eleve.getNom() + " " + eleve.getPrenom());
			classeText.setText(eleve.getClasse().getNom());
			restantText.setText(eleve.getMontantRestant()+"");
		}
		else {
			try {
				AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/erreurEleve.fxml"));

				win = new Stage();
				win.initModality(Modality.APPLICATION_MODAL);
							
				Scene scene = new Scene(root);								
				win.setScene(scene);
				win.showAndWait();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void Clear() {
		recherche.clear();
		paiment.clear();
		result.setOpacity(0);
		matriculeText.clear();
		nomText.clear();
		classeText.clear();
		restantText.clear();
	}
	
	public void Retour() {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/intro_acceuil.fxml"));
			pane.getChildren().setAll(root);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void RemplirTable() {
		list = FXCollections.observableArrayList ();
		
		DataDAO dataDAO = new DataDAO();
		Data data = dataDAO.find("annee_Scolaire");
		String annee_Scolaire = data.getValeur();
		dataDAO.close();
		
		ScolariteIndividuelDAO scolariteIndividuelDAO = new ScolariteIndividuelDAO();
        ArrayList<ScolariteIndividuel> scolariteIndividuel = scolariteIndividuelDAO.findAll(annee_Scolaire);
        
        for (ScolariteIndividuel scolarite : scolariteIndividuel) {
        	list.add(new Scolarite_Indiv(scolarite.getEleve().getMatricule(), scolarite.getEleve().getNom() + " " + scolarite.getEleve().getPrenom(), scolarite.getEleve().getClasse().getNom(), scolarite.getDate(), scolarite.getMontantPaye(), scolarite.getMontantRestant()));
		}
        scolariteIndividuelDAO.close();
        
        matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
		classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
		nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		paye.setCellValueFactory(new PropertyValueFactory<>("montantPaye"));
		restant.setCellValueFactory(new PropertyValueFactory<>("montantRestant"));
		date.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		table.setItems(list);
	}

	public String ConvertMonthFrench(String month) {
		String french = "";
		
		switch (month) {
			case "September":
				french = "Septembre";
				break;
			case "October":
				french = "Octobre";
				break;
			case "November":
				french = "Novembre";
				break;
			case "December":
				french = "Decembre";
				break;
			case "January":
				french = "Janvier";
				break;
			case "February":
				french = "Fevrier";
				break;
			case "March":
				french = "Mars";
				break;
			case "April":
				french = "Avril";
				break;
			case "May":
				french = "Mai";
				break;
			case "June":
				french = "Juin";
				break;
			default:
				break;
		}
		
		return french;
	}
	
}
