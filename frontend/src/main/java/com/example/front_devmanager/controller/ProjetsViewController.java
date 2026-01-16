package com.example.front_devmanager.controller;

import com.example.front_devmanager.model.Programmeur;
import com.example.front_devmanager.model.Projet;
import com.example.front_devmanager.service.ApiService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjetsViewController {

    @FXML
    private TableView<Projet> projetsTable;
    @FXML
    private TableColumn<Projet, Integer> idColumn;
    @FXML
    private TableColumn<Projet, String> intituleColumn;
    @FXML
    private TableColumn<Projet, String> participantsColumn;
    @FXML
    private TableColumn<Projet, LocalDate> dateDebutColumn;
    @FXML
    private TableColumn<Projet, LocalDate> dateFinPrevueColumn;
    @FXML
    private TableColumn<Projet, String> etatColumn;

    @FXML
    private TextField searchField;
    @FXML
    private Button addButton;
    @FXML
    private Button refreshButton;

    private final ApiService apiService = new ApiService();
    private ObservableList<Projet> projetsList = FXCollections.observableArrayList();
    private List<Programmeur> tousLesProgrammeurs;

    @FXML
    public void initialize() {
        chargerProgrammeurs();
        setupTableColumns();
        setupSearchFilter();
        loadProjets();
    }

    private void chargerProgrammeurs() {
        try {
            tousLesProgrammeurs = apiService.getAllProgrammeurs();
        } catch (Exception e) {
            tousLesProgrammeurs = FXCollections.observableArrayList();
            showErrorAlert("Erreur", "Impossible de charger la liste des programmeurs");
        }
    }

    private void setupTableColumns() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        intituleColumn.setCellValueFactory(new PropertyValueFactory<>("intitule"));
        dateDebutColumn.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        dateFinPrevueColumn.setCellValueFactory(new PropertyValueFactory<>("dateFinPrevue"));
        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));

        // Colonne des participants
        participantsColumn.setCellValueFactory(cellData -> {
            Set<Programmeur> programmeurs = cellData.getValue().getProgrammeurs();
            if (programmeurs == null || programmeurs.isEmpty()) {
                return new javafx.beans.property.SimpleStringProperty("Aucun");
            }
            String noms = programmeurs.stream()
                    .map(p -> p.getPrenom() + " " + p.getNom())
                    .collect(Collectors.joining(", "));
            return new javafx.beans.property.SimpleStringProperty(noms);
        });

        // Menu contextuel et double-clic
        ContextMenu contextMenu = new ContextMenu();
        MenuItem participantsItem = new MenuItem("Gérer les participants");
        MenuItem modifierItem = new MenuItem("Modifier le projet");
        MenuItem supprimerItem = new MenuItem("Supprimer");
        
        participantsItem.setOnAction(e -> {
            Projet selected = projetsTable.getSelectionModel().getSelectedItem();
            if (selected != null) gererParticipants(selected);
        });
        
        modifierItem.setOnAction(e -> {
            Projet selected = projetsTable.getSelectionModel().getSelectedItem();
            if (selected != null) editProjet(selected);
        });
        
        supprimerItem.setOnAction(e -> {
            Projet selected = projetsTable.getSelectionModel().getSelectedItem();
            if (selected != null) deleteProjet(selected);
        });
        
        contextMenu.getItems().addAll(participantsItem, modifierItem, supprimerItem);
        projetsTable.setContextMenu(contextMenu);
        
        // Double-clic pour gérer les participants
        projetsTable.setRowFactory(tv -> {
            TableRow<Projet> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    gererParticipants(row.getItem());
                }
            });
            return row;
        });

        projetsTable.setItems(projetsList);
    }

    private void setupSearchFilter() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                projetsTable.setItems(projetsList);
            } else {
                ObservableList<Projet> filtered = projetsList.filtered(p ->
                        p.getIntitule().toLowerCase().contains(newValue.toLowerCase()) ||
                        p.getEtat().toLowerCase().contains(newValue.toLowerCase())
                );
                projetsTable.setItems(filtered);
            }
        });
    }

    @FXML
    private void loadProjets() {
        try {
            System.out.println("Chargement des projets...");
            projetsList.clear();
            List<Projet> projets = apiService.getAllProjets();
            projetsList.addAll(projets);
            System.out.println(projets.size() + " projet(s) chargé(s)");
        } catch (java.net.ConnectException e) {
            e.printStackTrace();
            showErrorAlert("Erreur de connexion", 
                "Impossible de se connecter à l'API.\nVérifiez que le backend est démarré sur http://localhost:8080");
        } catch (Exception e) {
            e.printStackTrace();
            showErrorAlert("Erreur", "Impossible de charger les projets: " + e.getMessage());
        }
    }

    @FXML
    private void handleAddProjet() {
        Dialog<Projet> dialog = createProjetDialog(null);
        dialog.showAndWait().ifPresent(projet -> {
            try {
                apiService.createProjet(projet);
                loadProjets();
            } catch (Exception e) {
                showErrorAlert("Erreur", "Impossible de créer le projet: " + e.getMessage());
            }
        });
    }

    @FXML
    private void handleEditProjet() {
        Projet selected = projetsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showErrorAlert("Erreur", "Veuillez sélectionner un projet");
            return;
        }
        editProjet(selected);
    }

    @FXML
    private void handleGererParticipants() {
        Projet selected = projetsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showErrorAlert("Erreur", "Veuillez sélectionner un projet");
            return;
        }
        gererParticipants(selected);
    }

    @FXML
    private void handleDeleteProjet() {
        Projet selected = projetsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showErrorAlert("Erreur", "Veuillez sélectionner un projet");
            return;
        }
        deleteProjet(selected);
    }

    private void gererParticipants(Projet projet) {
        if (projet == null) return;

        Dialog<Set<Programmeur>> dialog = new Dialog<>();
        dialog.setTitle("Gérer les participants - " + projet.getIntitule());

        ButtonType saveButtonType = new ButtonType("Enregistrer", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        content.setPrefWidth(400);

        Label infoLabel = new Label("Sélectionnez les programmeurs participants au projet:");
        
        // Liste avec checkboxes
        ListView<CheckBox> listView = new ListView<>();
        ObservableList<CheckBox> checkBoxes = FXCollections.observableArrayList();
        
        Set<Integer> participantIds = projet.getProgrammeurs() != null ? 
            projet.getProgrammeurs().stream().map(Programmeur::getId).collect(Collectors.toSet()) :
            new HashSet<>();

        for (Programmeur prog : tousLesProgrammeurs) {
            CheckBox cb = new CheckBox(prog.getPrenom() + " " + prog.getNom() + " (" + prog.getPseudo() + ")");
            cb.setUserData(prog);
            cb.setSelected(participantIds.contains(prog.getId()));
            checkBoxes.add(cb);
        }
        
        listView.setItems(checkBoxes);
        listView.setPrefHeight(300);

        content.getChildren().addAll(infoLabel, listView);
        dialog.getDialogPane().setContent(content);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                Set<Programmeur> selectedProgrammeurs = checkBoxes.stream()
                        .filter(CheckBox::isSelected)
                        .map(cb -> (Programmeur) cb.getUserData())
                        .collect(Collectors.toSet());
                return selectedProgrammeurs;
            }
            return null;
        });

        dialog.showAndWait().ifPresent(participants -> {
            projet.setProgrammeurs(participants);
            try {
                apiService.updateProjet(projet);
                loadProjets();
            } catch (Exception e) {
                showErrorAlert("Erreur", "Impossible de mettre à jour les participants: " + e.getMessage());
            }
        });
    }

    private void editProjet(Projet projet) {
        if (projet == null) return;
        
        Dialog<Projet> dialog = createProjetDialog(projet);
        dialog.showAndWait().ifPresent(updatedProjet -> {
            try {
                apiService.updateProjet(updatedProjet);
                loadProjets();
            } catch (Exception e) {
                showErrorAlert("Erreur", "Impossible de mettre à jour le projet: " + e.getMessage());
            }
        });
    }

    private void deleteProjet(Projet projet) {
        if (projet == null) return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Supprimer le projet");
        alert.setContentText("Êtes-vous sûr de vouloir supprimer le projet \"" + projet.getIntitule() + "\" ?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    apiService.deleteProjet(projet.getId());
                    loadProjets();
                } catch (Exception e) {
                    showErrorAlert("Erreur", "Impossible de supprimer le projet: " + e.getMessage());
                }
            }
        });
    }

    private Dialog<Projet> createProjetDialog(Projet projet) {
        Dialog<Projet> dialog = new Dialog<>();
        dialog.setTitle(projet == null ? "Nouveau Projet" : "Modifier Projet");

        ButtonType saveButtonType = new ButtonType("Enregistrer", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        VBox content = new VBox(10);
        content.setPrefWidth(400);

        TextField intituleField = new TextField(projet != null ? projet.getIntitule() : "");
        DatePicker dateDebutPicker = new DatePicker(projet != null ? projet.getDateDebut() : LocalDate.now());
        DatePicker dateFinPicker = new DatePicker(projet != null ? projet.getDateFinPrevue() : LocalDate.now().plusMonths(3));
        
        ComboBox<String> etatCombo = new ComboBox<>();
        etatCombo.getItems().addAll("Planifié", "En cours", "Terminé", "Annulé");
        etatCombo.setValue(projet != null ? projet.getEtat() : "Planifié");

        content.getChildren().addAll(
                new Label("Intitulé:"), intituleField,
                new Label("Date de début:"), dateDebutPicker,
                new Label("Date de fin prévue:"), dateFinPicker,
                new Label("État:"), etatCombo
        );

        dialog.getDialogPane().setContent(content);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                Projet p = new Projet();
                if (projet != null) {
                    p.setId(projet.getId());
                }
                p.setIntitule(intituleField.getText());
                p.setDateDebut(dateDebutPicker.getValue());
                p.setDateFinPrevue(dateFinPicker.getValue());
                p.setEtat(etatCombo.getValue());
                return p;
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
}
