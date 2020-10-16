package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Gestion_Ecole.Eleve;
import ModelDAO.Model.Data;
import ModelDAO.Model.DataDAO;
import ModelDAO.Model.EleveDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EleveController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<Eleve> table;

    @FXML
    private TableColumn<Eleve, String> matricule;

    @FXML
    private TableColumn<Eleve, String> nom;

    @FXML
    private TableColumn<Eleve, String> prenom;

    @FXML
    private TableColumn<Eleve, String> classe;

    @FXML
    private TableColumn<Eleve, String> date;

    @FXML
    private TableColumn<Eleve, Integer> paye;

    @FXML
    private TableColumn<Eleve, Integer> restant;

    @FXML
    private TextField search;

    @FXML
    private Button inscrire;

    @FXML
    private ComboBox<String> trier;

    public ObservableList<Eleve> listEleve;

    public static Stage win;

    public ArrayList<ModelDAO.Model.Eleve> listEleves = null;

    public String annee_Scolaire;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DataDAO dataDAO = new DataDAO();
        Data data = dataDAO.find("annee_Scolaire");
        annee_Scolaire = data.getValeur();
        dataDAO.close();

        if (annee_Scolaire.isEmpty()) {
            inscrire.setDisable(true);
        }

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        trier.getItems().addAll("Tout", "Inscription Terminee", "Inscription Incomplete");
        this.RemplirTable();

        this.Filtre();
    }

    public void Filtre() {
        FilteredList<Eleve> filterEleve = new FilteredList<>(listEleve, b -> true);

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filterEleve.setPredicate(eleve -> {

                if ((newValue == null) || (newValue.isEmpty())) {
                    return true;
                }

                String enter = newValue.toLowerCase();

                if (eleve.getMatricule().toLowerCase().contains(enter)) {
                    return true;
                }
                if (eleve.getNom().toLowerCase().contains(enter)) {
                    return true;
                }
                if (eleve.getPrenom().toLowerCase().contains(enter)) {
                    return true;
                }
                if (eleve.getClasse().toLowerCase().contains(enter)) {
                    return true;
                }
                if (eleve.getDate().toLowerCase().contains(enter)) {
                    return true;
                }
                if ((eleve.getPaye() + "").contains(enter)) {
                    return true;
                }
                if ((eleve.getRestant() + "").contains(enter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Eleve> sortedEleve = new SortedList<>(filterEleve);
        sortedEleve.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedEleve);
    }

    public void Triage() {

        listEleve = FXCollections.observableArrayList();

        DataDAO dataDAO = new DataDAO();
        Data data = dataDAO.find("annee_Scolaire");
        annee_Scolaire = data.getValeur();

        EleveDAO eleveDAO = new EleveDAO();
        if (trier.getValue().equals("Inscription Terminee")) {
            listEleves = eleveDAO.findAll1(annee_Scolaire);
        } else if (trier.getValue().equals("Inscription Incomplete")) {
            listEleves = eleveDAO.findAll2(annee_Scolaire);
        } else {
            listEleves = eleveDAO.findAll(annee_Scolaire);
        }

        for (ModelDAO.Model.Eleve eleve : listEleves) {
            listEleve.add(new Eleve(eleve.getMatricule(), eleve.getNom(), eleve.getPrenom(), eleve.getClasse().getNom(), eleve.getDateInscription(), eleve.getMontantPaye(), eleve.getMontantRestant()));
        }
        eleveDAO.close();

        matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        paye.setCellValueFactory(new PropertyValueFactory<>("paye"));
        restant.setCellValueFactory(new PropertyValueFactory<>("restant"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        table.setItems(listEleve);

        Filtre();
    }

    public void Inscription() {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/Interface/inscrireEleve.fxml"));

            win = new Stage();
            win.initModality(Modality.APPLICATION_MODAL);

            Scene scene = new Scene(root);
            win.setScene(scene);
            win.showAndWait();

            this.RemplirTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void RemplirTable() {
        listEleve = FXCollections.observableArrayList();

        DataDAO dataDAO = new DataDAO();
        Data data = dataDAO.find("annee_Scolaire");
        String annee_Scolaire = data.getValeur();
        dataDAO.close();

        EleveDAO eleveDAO = new EleveDAO();
        ArrayList<ModelDAO.Model.Eleve> listEleves = eleveDAO.findAll(annee_Scolaire);
        for (ModelDAO.Model.Eleve eleve : listEleves) {
            listEleve.add(new Eleve(eleve.getMatricule(), eleve.getNom(), eleve.getPrenom(), eleve.getClasse().getNom(), eleve.getDateInscription(), eleve.getMontantPaye(), eleve.getMontantRestant()));
        }
        eleveDAO.close();

        matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        paye.setCellValueFactory(new PropertyValueFactory<>("paye"));
        restant.setCellValueFactory(new PropertyValueFactory<>("restant"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        table.setItems(listEleve);
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
