package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Gestion_Ecole.Budget_Previsionnel;
import ModelDAO.Model.Budget;
import ModelDAO.Model.BudgetDAO;
import ModelDAO.Model.BudgetPrevisionnel;
import ModelDAO.Model.BudgetPrevisionnelDAO;
import ModelDAO.Model.Data;
import ModelDAO.Model.DataDAO;
import ModelDAO.Model.Rubrique;
import ModelDAO.Model.RubriqueDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.IntegerStringConverter;

public class BudgetPrevisionnelController implements Initializable{

	@FXML
    private AnchorPane pane;
	
    @FXML
    private TableView<Budget_Previsionnel> tableActif;
    
    @FXML
    private TableColumn<Budget_Previsionnel, Integer> actif;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> septActif;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> octActif;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> novActif;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> decActif;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> janActif;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> fevActif;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> marsActif;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> avrilActif;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> maiActif;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> juinActif;

    @FXML
    private TableView<Budget_Previsionnel> tablePassif;
    
    @FXML
    private TableColumn<Budget_Previsionnel, Integer> passif;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> septPassif;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> octPassif;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> novPassif;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> decPassif;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> janPassif;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> fevPassif;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> marsPassif;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> avrilPassif;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> maiPassif;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> juinPassif;

    @FXML
    private TableView<Budget_Previsionnel> tableSolde;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> solde;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> septSolde;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> octSolde;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> novSolde;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> decSolde;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> janSolde;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> fevSolde;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> marsSolde;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> avrilSolde;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> maiSolde;

    @FXML
    private TableColumn<Budget_Previsionnel, Integer> juinSolde;

    @FXML
    private Label total;
    
    public ObservableList<Budget_Previsionnel> listBudgetActif = FXCollections.observableArrayList ();
    
    public ObservableList<Budget_Previsionnel> listBudgetPassif = FXCollections.observableArrayList ();
    
    public ObservableList<Budget_Previsionnel> listBudgetSolde = FXCollections.observableArrayList ();

    public RubriqueDAO rubriqueDAO = new RubriqueDAO();
    
    String annee_Scolaire = "";
    
    int indexActif = -1;
    
    int indexPassif = -1;
    
    BudgetPrevisionnel budgetPrevisionnel = null;
    
    ArrayList<Budget> listBudget = null;
    
    BudgetDAO BbudgetDAO = null;
    
	@Override
	public void initialize(URL url, ResourceBundle rs) {
		tableActif.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tablePassif.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableSolde.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		DataDAO dataDAO = new DataDAO();
		Data data = dataDAO.find("annee_Scolaire");
		annee_Scolaire = data.getValeur();
		dataDAO.close();
		
		if (!annee_Scolaire.equals(""))
		{
			BudgetPrevisionnelDAO budgetPrevisionnelDAO = new BudgetPrevisionnelDAO();
	        budgetPrevisionnel = budgetPrevisionnelDAO.find(annee_Scolaire);
	        budgetPrevisionnelDAO.close();
	        
	        BbudgetDAO = new BudgetDAO();
	        listBudget = BbudgetDAO.findAll(budgetPrevisionnel.getIdBudgetPrev());
	        
	        String rubrique = "";
	        for (Budget budget : listBudget)
	        {
	        	if (!budget.getRubrique().getNom().equals(rubrique)) 
	        	{
	        		rubrique = budget.getRubrique().getNom();
	        		if (budget.getRubrique().getType().equals("actif"))
	        		{
	        			indexActif++;
	        			listBudgetActif.add(new Budget_Previsionnel(budget.getBudgetPrevisionnel().getIdBudgetPrev(), budget.getRubrique().getNom(), budget.getBudgetPrevisionnel().getAnneeScolaire()));
	        			this.SetMois(listBudgetActif, budget.getMois(), budget.getMontant(), "actif");
	        		}
	        		else
	        		{
	        			indexPassif++;
	        			listBudgetPassif.add(new Budget_Previsionnel(budget.getBudgetPrevisionnel().getIdBudgetPrev(), budget.getRubrique().getNom(), budget.getBudgetPrevisionnel().getAnneeScolaire()));
	        			this.SetMois(listBudgetPassif, budget.getMois(), budget.getMontant(), "passif");
	        		}
	        	}
	        	else {
	        		if (budget.getRubrique().getType().equals("actif"))
	        			this.SetMois(listBudgetActif, budget.getMois(), budget.getMontant(), "actif");
	        		else
	        			this.SetMois(listBudgetPassif, budget.getMois(), budget.getMontant(), "passif");
	        	}
	        }
		}
        
        this.RemplirTableActif();
        this.RemplirTablePassif();
        this.RemplirTableSolde();
        
	}
	
	public void SetMois(ObservableList<Budget_Previsionnel> list, String mois, int montant, String type) {
		Budget_Previsionnel budget;
		if (type.equals("actif"))
			budget = list.get(indexActif);
		else
			budget = list.get(indexPassif);
		
		switch (mois) 
		{
			case "Septembre":
				budget.setSeptembre(montant);
				break;
			case "Octobre":
				budget.setOctobre(montant);
				break;
			case "Novembre":
				budget.setNovembre(montant);
				break;
			case "Decembre":
				budget.setDecembre(montant);
				break;
			case "Janvier":
				budget.setJanvier(montant);
				break;
			case "Fevrier":
				budget.setFevrier(montant);
				break;
			case "Mars":
				budget.setMars(montant);
				break;
			case "Avril":
				budget.setAvril(montant);
				break;
			case "Mai":
				budget.setMai(montant);
				break;
			case "Juin":
				budget.setJuin(montant);
				break;
			default:
				break;
		}
	}
	
	public void ChangeSeptActif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tableActif.getSelectionModel().getSelectedItem();
		if (!selected.getRubrique().equals("---Totale---")){
			selected.setSeptembre(Integer.parseInt(edittedCell.getNewValue().toString()));
			Ajouter("Septembre", rubriqueDAO.find(selected.getRubrique(), "actif"), budgetPrevisionnel, selected.getSeptembre());
		}
	}
	public void ChangeSeptPassif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tablePassif.getSelectionModel().getSelectedItem();
		if (!selected.getRubrique().equals("---Totale---")) {
			selected.setSeptembre(Integer.parseInt(edittedCell.getNewValue().toString()));
			Ajouter("Septembre", rubriqueDAO.find(selected.getRubrique(), "passif"), budgetPrevisionnel, selected.getSeptembre());
		}
	}
	
	public void ChangeOctActif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tableActif.getSelectionModel().getSelectedItem();
		if (!selected.getRubrique().equals("---Totale---")) {
			selected.setOctobre(Integer.parseInt(edittedCell.getNewValue().toString()));
			Ajouter("Octobre", rubriqueDAO.find(selected.getRubrique(), "actif"), budgetPrevisionnel, selected.getOctobre());
		}
	}
	public void ChangeOctPassif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tablePassif.getSelectionModel().getSelectedItem();
		if (!selected.getRubrique().equals("---Totale---")) {
			selected.setOctobre(Integer.parseInt(edittedCell.getNewValue().toString()));
			Ajouter("Octobre", rubriqueDAO.find(selected.getRubrique(), "passif"), budgetPrevisionnel, selected.getOctobre());
		}
	}
	
	public void ChangeNovActif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tableActif.getSelectionModel().getSelectedItem();
		if (!selected.getRubrique().equals("---Totale---")) {
			selected.setNovembre(Integer.parseInt(edittedCell.getNewValue().toString()));
			Ajouter("Novembre", rubriqueDAO.find(selected.getRubrique(), "actif"), budgetPrevisionnel, selected.getNovembre());
		}
	}
	public void ChangeNovPassif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tablePassif.getSelectionModel().getSelectedItem();
		if (!selected.getRubrique().equals("---Totale---")) {
			selected.setNovembre(Integer.parseInt(edittedCell.getNewValue().toString()));
			Ajouter("Novembre", rubriqueDAO.find(selected.getRubrique(), "passif"), budgetPrevisionnel, selected.getNovembre());
		}
	}
	
	public void ChangeDecActif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tableActif.getSelectionModel().getSelectedItem();
		if (!selected.getRubrique().equals("---Totale---")) {
			selected.setDecembre(Integer.parseInt(edittedCell.getNewValue().toString()));
			Ajouter("Decembre", rubriqueDAO.find(selected.getRubrique(), "actif"), budgetPrevisionnel, selected.getDecembre());
		}
	}
	public void ChangeDecPassif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tablePassif.getSelectionModel().getSelectedItem();
		if (!selected.getRubrique().equals("---Totale---")) {
			selected.setDecembre(Integer.parseInt(edittedCell.getNewValue().toString()));
			Ajouter("Decembre", rubriqueDAO.find(selected.getRubrique(), "passif"), budgetPrevisionnel, selected.getDecembre());
		}
	}
	public void ChangeJanActif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tableActif.getSelectionModel().getSelectedItem();
		if (!selected.getRubrique().equals("---Totale---")) {
			selected.setJanvier(Integer.parseInt(edittedCell.getNewValue().toString()));
			Ajouter("Janvier", rubriqueDAO.find(selected.getRubrique(), "actif"), budgetPrevisionnel, selected.getJanvier());
		}
	}
	public void ChangeJanPassif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tablePassif.getSelectionModel().getSelectedItem();
		if (!selected.getRubrique().equals("---Totale---")) {
			selected.setJanvier(Integer.parseInt(edittedCell.getNewValue().toString()));
			Ajouter("Janvier", rubriqueDAO.find(selected.getRubrique(), "passif"), budgetPrevisionnel, selected.getJanvier());
		}
	}
	public void ChangeFevActif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tableActif.getSelectionModel().getSelectedItem();
		if (!selected.getRubrique().equals("---Totale---")) {
			selected.setFevrier(Integer.parseInt(edittedCell.getNewValue().toString()));
			Ajouter("Fevrier", rubriqueDAO.find(selected.getRubrique(), "actif"), budgetPrevisionnel, selected.getFevrier());
		}
	}
	public void ChangeFevPassif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tablePassif.getSelectionModel().getSelectedItem();
		if (!selected.getRubrique().equals("---Totale---")) {
			selected.setFevrier(Integer.parseInt(edittedCell.getNewValue().toString()));
			Ajouter("Fevrier", rubriqueDAO.find(selected.getRubrique(), "passif"), budgetPrevisionnel, selected.getFevrier());
		}
	}
	public void ChangeMarsActif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tableActif.getSelectionModel().getSelectedItem();
		if (!selected.getRubrique().equals("---Totale---")) {
			selected.setMars(Integer.parseInt(edittedCell.getNewValue().toString()));
			Ajouter("Mars", rubriqueDAO.find(selected.getRubrique(), "actif"), budgetPrevisionnel, selected.getMars());
		}
	}
	public void ChangeMarsPassif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tablePassif.getSelectionModel().getSelectedItem();
		if (!selected.getRubrique().equals("---Totale---")) {
			selected.setMars(Integer.parseInt(edittedCell.getNewValue().toString()));
			Ajouter("Mars", rubriqueDAO.find(selected.getRubrique(), "passif"), budgetPrevisionnel, selected.getMars());
		}
	}
	public void ChangeAvrilActif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tableActif.getSelectionModel().getSelectedItem();
		if (!selected.getRubrique().equals("---Totale---")) {
			selected.setAvril(Integer.parseInt(edittedCell.getNewValue().toString()));
			Ajouter("Avril", rubriqueDAO.find(selected.getRubrique(), "actif"), budgetPrevisionnel, selected.getAvril());
		}
	}
	public void ChangeAvrilPassif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tablePassif.getSelectionModel().getSelectedItem();
		if (!selected.getRubrique().equals("---Totale---")) {
			selected.setAvril(Integer.parseInt(edittedCell.getNewValue().toString()));
			Ajouter("Avril", rubriqueDAO.find(selected.getRubrique(), "passif"), budgetPrevisionnel, selected.getAvril());
		}
	}
	public void ChangeMaiActif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tableActif.getSelectionModel().getSelectedItem();
		if (!selected.getRubrique().equals("---Totale---")) {
			selected.setMai(Integer.parseInt(edittedCell.getNewValue().toString()));
			Ajouter("Mai", rubriqueDAO.find(selected.getRubrique(), "actif"), budgetPrevisionnel, selected.getMai());
		}
	}
	public void ChangeMaiPassif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tablePassif.getSelectionModel().getSelectedItem();
		if (!selected.getRubrique().equals("---Totale---")) {
			selected.setMai(Integer.parseInt(edittedCell.getNewValue().toString()));
			Ajouter("Mai", rubriqueDAO.find(selected.getRubrique(), "passif"), budgetPrevisionnel, selected.getMai());
		}
	}
	public void ChangeJuinActif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tableActif.getSelectionModel().getSelectedItem();
		if (!selected.getRubrique().equals("---Totale---")){
			selected.setJuin(Integer.parseInt(edittedCell.getNewValue().toString()));
			Ajouter("Juin", rubriqueDAO.find(selected.getRubrique(), "actif"), budgetPrevisionnel, selected.getJuin());
		}
	}
	public void ChangeJuinPassif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tablePassif.getSelectionModel().getSelectedItem();
		if (!selected.getRubrique().equals("---Totale---")) {
			selected.setJuin(Integer.parseInt(edittedCell.getNewValue().toString()));
			Ajouter("Juin", rubriqueDAO.find(selected.getRubrique(), "passif"), budgetPrevisionnel, selected.getJuin());
		}
	}
	
	public void Ajouter(String mois, Rubrique rubrique, BudgetPrevisionnel bPre, int montant)
	{
		boolean absent = true;

		for (Budget bud : listBudget)
		{
			if (bud.getBudgetPrevisionnel().getIdBudgetPrev()==bPre.getIdBudgetPrev() && bud.getMois().equals(mois) && bud.getRubrique().getNom().equals(rubrique.getNom())) {
				bud.setMontant(montant);
				BbudgetDAO.update(bud);
				absent = false;
				break;
			}
		}
		if (absent)
			BbudgetDAO.create(new Budget(mois, rubrique, bPre, montant));
	}
	
	public void Actualiser() {
		listBudgetActif.remove(listBudgetActif.size()-1);
		listBudgetPassif.remove(listBudgetPassif.size()-1);
                listBudgetSolde.remove(listBudgetSolde.size()-1);
		this.RemplirTableActif();
		this.RemplirTablePassif();
		this.RemplirTableSolde();
	}
	
	public void RemplirTableActif() {		
		
		actif.setCellValueFactory(new PropertyValueFactory<>("rubrique"));
		septActif.setCellValueFactory(new PropertyValueFactory<>("septembre"));
		octActif.setCellValueFactory(new PropertyValueFactory<>("octobre"));
		novActif.setCellValueFactory(new PropertyValueFactory<>("novembre"));
		decActif.setCellValueFactory(new PropertyValueFactory<>("decembre"));
		janActif.setCellValueFactory(new PropertyValueFactory<>("janvier"));
		fevActif.setCellValueFactory(new PropertyValueFactory<>("fevrier"));
		marsActif.setCellValueFactory(new PropertyValueFactory<>("mars"));
		avrilActif.setCellValueFactory(new PropertyValueFactory<>("avril"));
		maiActif.setCellValueFactory(new PropertyValueFactory<>("mai"));
		juinActif.setCellValueFactory(new PropertyValueFactory<>("juin"));
		
		tableActif.setItems(listBudgetActif);
		
		tableActif.setEditable(true);
		septActif.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		octActif.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		novActif.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		decActif.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		janActif.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		fevActif.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		marsActif.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		avrilActif.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		maiActif.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		juinActif.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
	}
	
	public void RemplirTablePassif() {
		
		passif.setCellValueFactory(new PropertyValueFactory<>("rubrique"));
		septPassif.setCellValueFactory(new PropertyValueFactory<>("septembre"));
		octPassif.setCellValueFactory(new PropertyValueFactory<>("octobre"));
		novPassif.setCellValueFactory(new PropertyValueFactory<>("novembre"));
		decPassif.setCellValueFactory(new PropertyValueFactory<>("decembre"));
		janPassif.setCellValueFactory(new PropertyValueFactory<>("janvier"));
		fevPassif.setCellValueFactory(new PropertyValueFactory<>("fevrier"));
		marsPassif.setCellValueFactory(new PropertyValueFactory<>("mars"));
		avrilPassif.setCellValueFactory(new PropertyValueFactory<>("avril"));
		maiPassif.setCellValueFactory(new PropertyValueFactory<>("mai"));
		juinPassif.setCellValueFactory(new PropertyValueFactory<>("juin"));
		
		tablePassif.setItems(listBudgetPassif);
		
		tablePassif.setEditable(true);
		septPassif.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		octPassif.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		novPassif.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		decPassif.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		janPassif.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		fevPassif.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		marsPassif.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		avrilPassif.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		maiPassif.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		juinPassif.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
	}

	public void RemplirTableSolde() {
		
		Budget_Previsionnel Solde = new Budget_Previsionnel(0, "Solde", annee_Scolaire);
		Budget_Previsionnel total1;
		Budget_Previsionnel total2 = new Budget_Previsionnel(0, "---Totale---", annee_Scolaire);
		
		for (Budget_Previsionnel budget : listBudgetActif) {
			Solde.setSeptembre(Solde.getSeptembre() + budget.getSeptembre());
			Solde.setOctobre(Solde.getOctobre() + budget.getOctobre());
			Solde.setNovembre(Solde.getNovembre() + budget.getNovembre());
			Solde.setDecembre(Solde.getDecembre() + budget.getDecembre());
			Solde.setJanvier(Solde.getJanvier() + budget.getJanvier());
			Solde.setFevrier(Solde.getFevrier() + budget.getFevrier());
			Solde.setMars(Solde.getMars() + budget.getMars());
			Solde.setAvril(Solde.getAvril() + budget.getAvril());
			Solde.setMai(Solde.getMai() + budget.getMai());
			Solde.setJuin(Solde.getJuin() + budget.getJuin());
		}
		total1 = new Budget_Previsionnel(Solde);
		total1.setRubrique("---Totale---");
		listBudgetActif.add(total1);
		
		for (Budget_Previsionnel budget : listBudgetPassif) {
			Solde.setSeptembre(Solde.getSeptembre() - budget.getSeptembre());
			Solde.setOctobre(Solde.getOctobre() - budget.getOctobre());
			Solde.setNovembre(Solde.getNovembre() - budget.getNovembre());
			Solde.setDecembre(Solde.getDecembre() - budget.getDecembre());
			Solde.setJanvier(Solde.getJanvier() - budget.getJanvier());
			Solde.setFevrier(Solde.getFevrier() - budget.getFevrier());
			Solde.setMars(Solde.getMars() - budget.getMars());
			Solde.setAvril(Solde.getAvril() - budget.getAvril());
			Solde.setMai(Solde.getMai() - budget.getMai());
			Solde.setJuin(Solde.getJuin() - budget.getJuin());
			
			total2.setSeptembre(total2.getSeptembre() + budget.getSeptembre());
			total2.setOctobre(total2.getOctobre() + budget.getOctobre());
			total2.setNovembre(total2.getNovembre() + budget.getNovembre());
			total2.setDecembre(total2.getDecembre() + budget.getDecembre());
			total2.setJanvier(total2.getJanvier() + budget.getJanvier());
			total2.setFevrier(total2.getFevrier() + budget.getFevrier());
			total2.setMars(total2.getMars() + budget.getMars());
			total2.setAvril(total2.getAvril() + budget.getAvril());
			total2.setMai(total2.getMai() + budget.getMai());
			total2.setJuin(total2.getJuin() + budget.getJuin());
		}
		
		listBudgetPassif.add(total2);
		
		total.setText(Solde.getSeptembre()+Solde.getOctobre()+Solde.getNovembre()+Solde.getDecembre()+Solde.getJanvier()+Solde.getFevrier()+Solde.getMars()+Solde.getAvril()+Solde.getMai()+Solde.getJuin()+" FCFA");
		
		listBudgetSolde.add(Solde);
		
		solde.setCellValueFactory(new PropertyValueFactory<>("rubrique"));
		septSolde.setCellValueFactory(new PropertyValueFactory<>("septembre"));
		octSolde.setCellValueFactory(new PropertyValueFactory<>("octobre"));
		novSolde.setCellValueFactory(new PropertyValueFactory<>("novembre"));
		decSolde.setCellValueFactory(new PropertyValueFactory<>("decembre"));
		janSolde.setCellValueFactory(new PropertyValueFactory<>("janvier"));
		fevSolde.setCellValueFactory(new PropertyValueFactory<>("fevrier"));
		marsSolde.setCellValueFactory(new PropertyValueFactory<>("mars"));
		avrilSolde.setCellValueFactory(new PropertyValueFactory<>("avril"));
		maiSolde.setCellValueFactory(new PropertyValueFactory<>("mai"));
		juinSolde.setCellValueFactory(new PropertyValueFactory<>("juin"));
		
		tableSolde.setItems(listBudgetSolde);
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
