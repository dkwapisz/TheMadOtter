package dev.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class LoseController {

    @FXML
    private Button restartButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button menuButton;

    @FXML
    private Label pointsLabel;


    public void initialize() {
        exitButton.setOnAction(event -> {
            try {
                Platform.exit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        menuButton.setOnAction(event -> {
            Stage stage = (Stage) menuButton.getScene().getWindow();
            try {
                Pane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/menu.fxml")));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        restartButton.setOnAction(event -> {
            // restart game
        });
    }
}
