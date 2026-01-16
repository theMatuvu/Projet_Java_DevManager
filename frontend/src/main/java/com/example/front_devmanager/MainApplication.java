package com.example.front_devmanager;

import com.example.front_devmanager.config.AppConfig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    private BorderPane mainLayout;
    private VBox sidebar;
    private StackPane contentArea;
    private Button selectedButton;

    @Override
    public void start(Stage stage) throws IOException {
        // Configuration de la fenêtre principale
        stage.setTitle(AppConfig.APP_TITLE + " v" + AppConfig.APP_VERSION);
        stage.setWidth(AppConfig.DEFAULT_WINDOW_WIDTH);
        stage.setHeight(AppConfig.DEFAULT_WINDOW_HEIGHT);

        // Création du layout principal
        mainLayout = new BorderPane();
        mainLayout.getStyleClass().add("main-layout");

        // Création de la sidebar
        createSidebar();
        
        // Création de la zone de contenu
        contentArea = new StackPane();
        contentArea.getStyleClass().add("content-container");
        
        // Affichage de la vue programmeurs par défaut
        loadProgrammeursView();

        // Assemblage du layout
        mainLayout.setLeft(sidebar);
        mainLayout.setCenter(contentArea);

        // Création de la scène
        Scene scene = new Scene(mainLayout);
        scene.getStylesheets().add(getClass().getResource("/com/example/front_devmanager/styles.css").toExternalForm());
        
        stage.setScene(scene);
        stage.show();
    }

    private void createSidebar() {
        sidebar = new VBox(10);
        sidebar.getStyleClass().add("sidebar");
        sidebar.setPrefWidth(200);
        sidebar.setPadding(new Insets(20));

        // Titre
        Label titleLabel = new Label("DevManager");
        titleLabel.getStyleClass().add("sidebar-title");

        // Spacer
        Region spacer1 = new Region();
        spacer1.setPrefHeight(20);

        // Boutons de navigation
        Button programmeursBtn = createNavButton("Programmeurs", () -> loadProgrammeursView());
        Button projetsBtn = createNavButton("Projets", () -> loadProjetsView());

        sidebar.getChildren().addAll(
                titleLabel,
                spacer1,
                programmeursBtn,
                projetsBtn
        );

        // Sélectionner le premier bouton par défaut
        selectedButton = programmeursBtn;
        programmeursBtn.getStyleClass().add("active");
    }

    private Button createNavButton(String text, Runnable action) {
        Button button = new Button(text);
        button.getStyleClass().add("nav-button");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER_LEFT);

        button.setOnAction(e -> {
            if (selectedButton != null) {
                selectedButton.getStyleClass().remove("active");
            }
            button.getStyleClass().add("active");
            selectedButton = button;
            action.run();
        });

        return button;
    }

    private void loadProgrammeursView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/front_devmanager/programmeurs-view.fxml"));
            VBox view = loader.load();
            loadContentWithAnimation(view);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorView("Erreur lors du chargement de la vue Programmeurs");
        }
    }

    private void loadProjetsView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/front_devmanager/projets-view.fxml"));
            VBox view = loader.load();
            loadContentWithAnimation(view);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorView("Erreur lors du chargement de la vue Projets");
        }
    }

    private void loadContentWithAnimation(Region content) {
        contentArea.getChildren().clear();
        contentArea.getChildren().add(content);
    }

    private void showErrorView(String message) {
        VBox errorView = new VBox(20);
        errorView.setAlignment(Pos.CENTER);
        errorView.setPadding(new Insets(40));

        Label errorLabel = new Label(message);
        errorLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #e74c3c;");

        errorView.getChildren().add(errorLabel);
        loadContentWithAnimation(errorView);
    }

    public static void main(String[] args) {
        launch();
    }
}
