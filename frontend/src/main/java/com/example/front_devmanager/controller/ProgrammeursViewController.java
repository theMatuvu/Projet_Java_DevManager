package com.example.front_devmanager.controller;

import com.example.front_devmanager.model.Programmeur;
import com.example.front_devmanager.service.ApiService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.List;

public class ProgrammeursViewController {

    @FXML
    private TableView<Programmeur> programmeursTable;
    @FXML
    private TableColumn<Programmeur, Integer> idColumn;
    @FXML
    private TableColumn<Programmeur, String> nomColumn;
    @FXML
    private TableColumn<Programmeur, String> prenomColumn;
    @FXML
    private TableColumn<Programmeur, String> pseudoColumn;
    @FXML
    private TableColumn<Programmeur, String> adresseColumn;
    @FXML
    private TableColumn<Programmeur, String> responsableColumn;
    @FXML
    private TableColumn<Programmeur, String> hobbyColumn;
    @FXML
    private TableColumn<Programmeur, Integer> naissanceColumn;
    @FXML
    private TableColumn<Programmeur, Double> salaireColumn;
    @FXML
    private TableColumn<Programmeur, Double> primeColumn;

    @FXML
    private TextField searchField;
    @FXML
    private Button addButton;
    @FXML
    private Button refreshButton;

    private final ApiService apiService = new ApiService();
    private ObservableList<Programmeur> programmeursList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTableColumns();
        setupSearchFilter();
        loadProgrammeurs();
    }

    private void setupTableColumns() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        pseudoColumn.setCellValueFactory(new PropertyValueFactory<>("pseudo"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        responsableColumn.setCellValueFactory(new PropertyValueFactory<>("responsable"));
        hobbyColumn.setCellValueFactory(new PropertyValueFactory<>("hobby"));
        naissanceColumn.setCellValueFactory(new PropertyValueFactory<>("naissance"));
        salaireColumn.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        primeColumn.setCellValueFactory(new PropertyValueFactory<>("prime"));

        salaireColumn.setCellFactory(col -> new TableCell<Programmeur, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : String.format("%.2f €", item));
            }
        });

        primeColumn.setCellFactory(col -> new TableCell<Programmeur, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : String.format("%.2f €", item));
            }
        });

        // Menu contextuel pour actions
        ContextMenu contextMenu = new ContextMenu();
        MenuItem modifierItem = new MenuItem("Modifier");
        MenuItem modifierSalaireItem = new MenuItem("Modifier le salaire");
        MenuItem supprimerItem = new MenuItem("Supprimer");
        
        modifierItem.setOnAction(e -> {
            Programmeur selected = programmeursTable.getSelectionModel().getSelectedItem();
            if (selected != null) editProgrammeur(selected);
        });
        
        modifierSalaireItem.setOnAction(e -> {
            Programmeur selected = programmeursTable.getSelectionModel().getSelectedItem();
            if (selected != null) updateSalaire(selected);
        });
        
        supprimerItem.setOnAction(e -> {
            Programmeur selected = programmeursTable.getSelectionModel().getSelectedItem();
            if (selected != null) deleteProgrammeur(selected);
        });
        
        contextMenu.getItems().addAll(modifierItem, modifierSalaireItem, supprimerItem);
        programmeursTable.setContextMenu(contextMenu);

        programmeursTable.setItems(programmeursList);
    }

    private void setupSearchFilter() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                programmeursTable.setItems(programmeursList);
            } else {
                ObservableList<Programmeur> filtered = programmeursList.filtered(p ->
                        p.getNom().toLowerCase().contains(newValue.toLowerCase()) ||
                        p.getPrenom().toLowerCase().contains(newValue.toLowerCase()) ||
                        p.getPseudo().toLowerCase().contains(newValue.toLowerCase())
                );
                programmeursTable.setItems(filtered);
            }
        });
    }

    @FXML
    private void loadProgrammeurs() {
        try {
            System.out.println("Chargement des programmeurs...");
            programmeursList.clear();
            List<Programmeur> programmeurs = apiService.getAllProgrammeurs();
            programmeursList.addAll(programmeurs);
            System.out.println(programmeurs.size() + " programmeur(s) chargé(s)");
        } catch (java.net.ConnectException e) {
            e.printStackTrace();
            showErrorAlert("Erreur de connexion", 
                "Impossible de se connecter à l'API.\nVérifiez que le backend est démarré sur http://localhost:8080");
        } catch (Exception e) {
            e.printStackTrace();
            showErrorAlert("Erreur", "Impossible de charger les programmeurs: " + e.getMessage());
        }
    }

    @FXML
    private void handleAddProgrammeur() {
        Dialog<Programmeur> dialog = createProgrammeurDialog(null);
        dialog.showAndWait().ifPresent(programmeur -> {
            try {
                apiService.createProgrammeur(programmeur);
                loadProgrammeurs();
            } catch (Exception e) {
                showErrorAlert("Erreur", "Impossible de créer le programmeur: " + e.getMessage());
            }
        });
    }

    @FXML
    private void handleEditProgrammeur() {
        Programmeur selected = programmeursTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showErrorAlert("Erreur", "Veuillez sélectionner un programmeur");
            return;
        }
        editProgrammeur(selected);
    }

    @FXML
    private void handleUpdateSalaire() {
        Programmeur selected = programmeursTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showErrorAlert("Erreur", "Veuillez sélectionner un programmeur");
            return;
        }
        updateSalaire(selected);
    }

    @FXML
    private void handleDeleteProgrammeur() {
        Programmeur selected = programmeursTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showErrorAlert("Erreur", "Veuillez sélectionner un programmeur");
            return;
        }
        deleteProgrammeur(selected);
    }

    private void editProgrammeur(Programmeur programmeur) {
        if (programmeur == null) return;
        
        Dialog<Programmeur> dialog = createProgrammeurDialog(programmeur);
        dialog.showAndWait().ifPresent(updatedProgrammeur -> {
            try {
                // Utilisation de l'endpoint de mise à jour complète
                apiService.updateProgrammeur(updatedProgrammeur);
                loadProgrammeurs();
            } catch (Exception e) {
                showErrorAlert("Erreur", "Impossible de mettre à jour: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    private void updateSalaire(Programmeur programmeur) {
        if (programmeur == null) return;

        TextInputDialog dialog = new TextInputDialog(String.valueOf(programmeur.getSalaire()));
        dialog.setTitle("Modifier le salaire");
        dialog.setHeaderText("Modification du salaire de " + programmeur.getPrenom() + " " + programmeur.getNom());
        dialog.setContentText("Nouveau salaire:");

        dialog.showAndWait().ifPresent(salaireStr -> {
            try {
                double newSalaire = Double.parseDouble(salaireStr);
                apiService.updateProgrammeurSalaire(programmeur.getId(), newSalaire);
                loadProgrammeurs();
            } catch (NumberFormatException e) {
                showErrorAlert("Erreur", "Veuillez entrer un nombre valide");
            } catch (Exception e) {
                showErrorAlert("Erreur", "Impossible de mettre à jour le salaire: " + e.getMessage());
            }
        });
    }

    private void deleteProgrammeur(Programmeur programmeur) {
        if (programmeur == null) return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Supprimer le programmeur");
        alert.setContentText("Êtes-vous sûr de vouloir supprimer " + programmeur.getPrenom() + " " + programmeur.getNom() + " ?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    apiService.deleteProgrammeur(programmeur.getId());
                    loadProgrammeurs();
                } catch (Exception e) {
                    showErrorAlert("Erreur", "Impossible de supprimer le programmeur: " + e.getMessage());
                }
            }
        });
    }

    private Dialog<Programmeur> createProgrammeurDialog(Programmeur programmeur) {
        Dialog<Programmeur> dialog = new Dialog<>();
        dialog.setTitle(programmeur == null ? "Nouveau Programmeur" : "Modifier Programmeur");

        ButtonType saveButtonType = new ButtonType("Enregistrer", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        VBox content = new VBox(10);
        content.setPrefWidth(400);

        TextField idField = new TextField(programmeur != null ? programmeur.getId().toString() : "");
        TextField nomField = new TextField(programmeur != null ? programmeur.getNom() : "");
        TextField prenomField = new TextField(programmeur != null ? programmeur.getPrenom() : "");
        TextField pseudoField = new TextField(programmeur != null ? programmeur.getPseudo() : "");
        TextField adresseField = new TextField(programmeur != null ? programmeur.getAdresse() : "");
        TextField responsableField = new TextField(programmeur != null ? programmeur.getResponsable() : "");
        TextField hobbyField = new TextField(programmeur != null ? programmeur.getHobby() : "");
        TextField naissanceField = new TextField(programmeur != null ? String.valueOf(programmeur.getNaissance()) : "");
        TextField salaireField = new TextField(programmeur != null ? String.valueOf(programmeur.getSalaire()) : "");
        TextField primeField = new TextField(programmeur != null ? String.valueOf(programmeur.getPrime()) : "");

        content.getChildren().addAll(
                new Label("ID:"), idField,
                new Label("Nom:"), nomField,
                new Label("Prénom:"), prenomField,
                new Label("Pseudo:"), pseudoField,
                new Label("Adresse:"), adresseField,
                new Label("Responsable:"), responsableField,
                new Label("Hobby:"), hobbyField,
                new Label("Année de naissance:"), naissanceField,
                new Label("Salaire:"), salaireField,
                new Label("Prime:"), primeField
        );

        dialog.getDialogPane().setContent(content);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                try {
                    Programmeur p = new Programmeur();
                    p.setId(Integer.parseInt(idField.getText()));
                    p.setNom(nomField.getText());
                    p.setPrenom(prenomField.getText());
                    p.setPseudo(pseudoField.getText());
                    p.setAdresse(adresseField.getText());
                    p.setResponsable(responsableField.getText());
                    p.setHobby(hobbyField.getText());
                    p.setNaissance(Integer.parseInt(naissanceField.getText()));
                    p.setSalaire(Double.parseDouble(salaireField.getText()));
                    p.setPrime(Double.parseDouble(primeField.getText()));
                    return p;
                } catch (NumberFormatException e) {
                    showErrorAlert("Erreur", "Veuillez entrer des valeurs valides");
                    return null;
                }
            }
            return null;
        });

        return dialog;
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
