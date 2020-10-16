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
import ModelDAO.Model.Classe;
import ModelDAO.Model.ClasseDAO;
import ModelDAO.Model.Data;
import ModelDAO.Model.DataDAO;
import ModelDAO.Model.Rubrique;
import ModelDAO.Model.RubriqueDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.control.TableView;

public class CreerBudgetController implements Initializable{

    @FXML
    private Button finish;
    
	@FXML
    private AnchorPane pane;
	
    @FXML
    private TableView<Budget_Previsionnel> tableActif;

    @FXML
    private TableColumn<Budget_Previsionnel, String> actif;

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
    private TableColumn<Budget_Previsionnel, String> passif;

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

    public ObservableList<Budget_Previsionnel> listBudget = FXCollections.observableArrayList ();
    
    public ObservableList<Budget_Previsionnel> listBudget1 = FXCollections.observableArrayList ();
    
    public ArrayList<Budget> list = new ArrayList<Budget>();
    
    public ArrayList<Budget> list1 = new ArrayList<Budget>();
    
    public RubriqueDAO rubriqueDAO = new RubriqueDAO();
    
    public BudgetPrevisionnelDAO budgetPrevDAO = new BudgetPrevisionnelDAO();
    
    public BudgetDAO budgetDAO = new BudgetDAO();
    
    public Budget budget;
    
    public int position;
    
    public static Stage win;
    
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		tableActif.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tablePassif.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		//collect and create the budget previsionnel table
		RubriqueDAO rubriqueDAO = new RubriqueDAO();
		ArrayList<Rubrique> rubriques = rubriqueDAO.findAll();
		rubriqueDAO.close();
		
		for (Rubrique rub : rubriques) {
			if (rub.getType().equals("actif")){
				listBudget.add(new Budget_Previsionnel(rub.getIdRubrique(), rub.getNom(), NouvelleAnneeController.anneeScolaire));
			}
			else {
				listBudget1.add(new Budget_Previsionnel(rub.getIdRubrique(), rub.getNom(), NouvelleAnneeController.anneeScolaire));
			}
		}
		
		this.RemplirTableActif();
		this.RemplirTablePassif();
	}
	
	public void Ajouter (String mois, Rubrique rubrique, BudgetPrevisionnel bPre, int montant)
	{
			
		if (rubrique != null && bPre != null && montant > 0)
		{
			if (Contient(rubrique, bPre, mois))
			{
				budget = list.get(position);
				budget.setMontant(montant);
				list.set(position, budget);
			}
			else
				list.add(new Budget(mois,rubrique,bPre,montant));
		}
		if(montant == 0)
		{
			list1.clear();
			list1.addAll(list);
			for(Budget b : list1)
			{
				if(b.getMois().equals(mois) && b.getRubrique().getIdRubrique().equals(rubrique.getIdRubrique()) && b.getBudgetPrevisionnel().getIdBudgetPrev().equals(bPre.getIdBudgetPrev()))
				{
					list.remove(b);
					break;
				}
			}
		}
	}
	
	public boolean Contient(Rubrique r, BudgetPrevisionnel b, String m)
	{
		position = 0;
		for (Budget l : list)
		{
			if (l.getRubrique().equals(r) && l.getBudgetPrevisionnel().equals(b) && l.getMois().equals(m))
				return true;
			position++;
		}
		return false;
	}
	
	public void ChangeSeptActif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tableActif.getSelectionModel().getSelectedItem();
		selected.setSeptembre(Integer.parseInt(edittedCell.getNewValue().toString()));
		Ajouter("Septembre", rubriqueDAO.find(selected.getRubrique(), "actif"), NouvelleAnneeController.budgetPrevisionnel, selected.getSeptembre());
	}
	public void ChangeSeptPassif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tablePassif.getSelectionModel().getSelectedItem();
		selected.setSeptembre(Integer.parseInt(edittedCell.getNewValue().toString()));
		Ajouter("Septembre", rubriqueDAO.find(selected.getRubrique(), "passif"), NouvelleAnneeController.budgetPrevisionnel, selected.getSeptembre());
	}
	
	public void ChangeOctActif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tableActif.getSelectionModel().getSelectedItem();
		selected.setOctobre(Integer.parseInt(edittedCell.getNewValue().toString()));
		Ajouter("Octobre", rubriqueDAO.find(selected.getRubrique(), "actif"), NouvelleAnneeController.budgetPrevisionnel, selected.getOctobre());
	}
	public void ChangeOctPassif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tablePassif.getSelectionModel().getSelectedItem();
		selected.setOctobre(Integer.parseInt(edittedCell.getNewValue().toString()));
		Ajouter("Octobre", rubriqueDAO.find(selected.getRubrique(), "passif"), NouvelleAnneeController.budgetPrevisionnel, selected.getOctobre());
	}
	
	public void ChangeNovActif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tableActif.getSelectionModel().getSelectedItem();
		selected.setNovembre(Integer.parseInt(edittedCell.getNewValue().toString()));
		Ajouter("Novembre", rubriqueDAO.find(selected.getRubrique(), "actif"), NouvelleAnneeController.budgetPrevisionnel, selected.getNovembre());
	}
	public void ChangeNovPassif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tablePassif.getSelectionModel().getSelectedItem();
		selected.setNovembre(Integer.parseInt(edittedCell.getNewValue().toString()));
		Ajouter("Novembre", rubriqueDAO.find(selected.getRubrique(), "passif"), NouvelleAnneeController.budgetPrevisionnel, selected.getNovembre());
	}
	
	public void ChangeDecActif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tableActif.getSelectionModel().getSelectedItem();
		selected.setDecembre(Integer.parseInt(edittedCell.getNewValue().toString()));
		Ajouter("Decembre", rubriqueDAO.find(selected.getRubrique(), "actif"), NouvelleAnneeController.budgetPrevisionnel, selected.getDecembre());
	}
	public void ChangeDecPassif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tablePassif.getSelectionModel().getSelectedItem();
		selected.setDecembre(Integer.parseInt(edittedCell.getNewValue().toString()));
		Ajouter("Decembre", rubriqueDAO.find(selected.getRubrique(), "passif"), NouvelleAnneeController.budgetPrevisionnel, selected.getDecembre());
	}
	
	public void ChangeJanActif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tableActif.getSelectionModel().getSelectedItem();
		selected.setJanvier(Integer.parseInt(edittedCell.getNewValue().toString()));
		Ajouter("Janvier", rubriqueDAO.find(selected.getRubrique(), "actif"), NouvelleAnneeController.budgetPrevisionnel, selected.getJanvier());
	}
	public void ChangeJanPassif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tablePassif.getSelectionModel().getSelectedItem();
		selected.setJanvier(Integer.parseInt(edittedCell.getNewValue().toString()));
		Ajouter("Janvier", rubriqueDAO.find(selected.getRubrique(), "passif"), NouvelleAnneeController.budgetPrevisionnel, selected.getJanvier());
	}
	
	public void ChangeFevActif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tableActif.getSelectionModel().getSelectedItem();
		selected.setFevrier(Integer.parseInt(edittedCell.getNewValue().toString()));
		Ajouter("Fevrier", rubriqueDAO.find(selected.getRubrique(), "actif"), NouvelleAnneeController.budgetPrevisionnel, selected.getFevrier());
	}
	public void ChangeFevPassif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tablePassif.getSelectionModel().getSelectedItem();
		selected.setFevrier(Integer.parseInt(edittedCell.getNewValue().toString()));
		Ajouter("Fevrier", rubriqueDAO.find(selected.getRubrique(), "passif"), NouvelleAnneeController.budgetPrevisionnel, selected.getFevrier());
	}
	
	public void ChangeMarsActif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tableActif.getSelectionModel().getSelectedItem();
		selected.setFevrier(Integer.parseInt(edittedCell.getNewValue().toString()));
		Ajouter("Mars", rubriqueDAO.find(selected.getRubrique(), "actif"), NouvelleAnneeController.budgetPrevisionnel, selected.getMars());
	}
	public void ChangeMarsPassif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tablePassif.getSelectionModel().getSelectedItem();
		selected.setFevrier(Integer.parseInt(edittedCell.getNewValue().toString()));
		Ajouter("Mars", rubriqueDAO.find(selected.getRubrique(), "passif"), NouvelleAnneeController.budgetPrevisionnel, selected.getMars());
	}
	
	public void ChangeAvrilActif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tableActif.getSelectionModel().getSelectedItem();
		selected.setAvril(Integer.parseInt(edittedCell.getNewValue().toString()));
		Ajouter("Avril", rubriqueDAO.find(selected.getRubrique(), "actif"), NouvelleAnneeController.budgetPrevisionnel, selected.getAvril());
	}
	public void ChangeAvrilPassif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tablePassif.getSelectionModel().getSelectedItem();
		selected.setAvril(Integer.parseInt(edittedCell.getNewValue().toString()));
		Ajouter("Avril", rubriqueDAO.find(selected.getRubrique(), "passif"), NouvelleAnneeController.budgetPrevisionnel, selected.getAvril());
	}
	
	public void ChangeMaiActif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tableActif.getSelectionModel().getSelectedItem();
		selected.setMai(Integer.parseInt(edittedCell.getNewValue().toString()));
		Ajouter("Mai", rubriqueDAO.find(selected.getRubrique(), "actif"), NouvelleAnneeController.budgetPrevisionnel, selected.getMai());
	}
	public void ChangeMaiPassif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tablePassif.getSelectionModel().getSelectedItem();
		selected.setMai(Integer.parseInt(edittedCell.getNewValue().toString()));
		Ajouter("Mai", rubriqueDAO.find(selected.getRubrique(), "passif"), NouvelleAnneeController.budgetPrevisionnel, selected.getMai());
	}
	
	public void ChangeJuinActif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tableActif.getSelectionModel().getSelectedItem();
		selected.setJuin(Integer.parseInt(edittedCell.getNewValue().toString()));
		Ajouter("Juin", rubriqueDAO.find(selected.getRubrique(), "actif"), NouvelleAnneeController.budgetPrevisionnel, selected.getJuin());
	}
	public void ChangeJuinPassif(CellEditEvent<Budget_Previsionnel, Integer> edittedCell) {
		Budget_Previsionnel selected = tablePassif.getSelectionModel().getSelectedItem();
		selected.setJuin(Integer.parseInt(edittedCell.getNewValue().toString()));
		Ajouter("Juin", rubriqueDAO.find(selected.getRubrique(), "passif"), NouvelleAnneeController.budgetPrevisionnel, selected.getJuin());
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
		
		tableActif.setItems(listBudget);
		
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
		
		tablePassif.setItems(listBudget1);
		
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
	
	
	public void Terminer() {
		if (list != null && !list.isEmpty()){
			
                    finish.setDisable(true);
			DataDAO dataDAO = new DataDAO();
			Data anneeScolaire = dataDAO.find("annee_Scolaire");
			anneeScolaire.setValeur(NouvelleAnneeController.anneeScolaire);
	        dataDAO.update(anneeScolaire);
	        dataDAO.close();
	        
	        BudgetPrevisionnelDAO budgetPrevisionnelDAO = new BudgetPrevisionnelDAO();
	        budgetPrevisionnelDAO.create(NouvelleAnneeController.budgetPrevisionnel);
	        budgetPrevisionnelDAO.close();
	        
	        ClasseDAO classeDAO = new ClasseDAO();
	        
	        for (Classe classe : NouvelleAnneeController.listClasses) {
	        	classeDAO.create(classe);
	        }
	        classeDAO.close();
	        
			budgetDAO.create(list);
			
			try {
				AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/intro_acceuil.fxml"));
				pane.getChildren().setAll(root);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
