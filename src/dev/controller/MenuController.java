package dev.controller;

import dev.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuController {

    @FXML
    private TextField nickField;

    @FXML
    private Button newGameButton;

    @FXML
    private Button highScoreButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button helpButton;

    @FXML
    private Button exitButton;

    public static ArrayList<String> highScores = new ArrayList<>();

    public MenuController() {
        setHighScores();
    }
    
    public void initialize() {
        newGameButton.setOnAction(event -> {
            Main main = new Main();
            Stage stage = (Stage) newGameButton.getScene().getWindow();
            try {
                Main.nick = nickField.getText();
                if (Main.nick.length() > 15) {
                    nickField.clear();
                    nickField.setPromptText("Too long nickname");
                } else if (Main.nick.length() == 0)  {
                    nickField.clear();
                    nickField.setPromptText("Please write your nickname");
                } else {
                    main.start(stage);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        exitButton.setOnAction(event -> {
            try {
                Platform.exit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        helpButton.setOnAction(event -> {
            Stage stage = (Stage) helpButton.getScene().getWindow();
            try {
                Pane root = FXMLLoader.load(getClass().getResource("/fxml/help.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        settingsButton.setOnAction(event -> {
            Stage stage = (Stage) settingsButton.getScene().getWindow();
            try {
                Pane root = FXMLLoader.load(getClass().getResource("/fxml/settings.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        highScoreButton.setOnAction(event -> {
            Stage stage = (Stage) highScoreButton.getScene().getWindow();
            setHighScores();
            try {
                setHighScores();
                Pane root = FXMLLoader.load(getClass().getResource("/fxml/highScore.fxml"));
                int y = 60;
                for (String score : highScores) {
                    Label zmienna = new Label(score);
                    zmienna.setStyle("-fx-font: 20 Forte");
                    zmienna.setLayoutX(240);
                    y = y + 30;
                    zmienna.setLayoutY(y);
                    root.getChildren().add(zmienna);
                }
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void setHighScores(){
        highScores = getHighestScores();
    }

    public ArrayList<String> getHighestScores(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("src\\dev\\HighScore.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> list = new ArrayList<>();
        while (true){
            assert scanner != null;
            if (!scanner.hasNextLine()) break;
            list.add(scanner.nextLine());
        }
        scanner.close();
        return list;
    }
}
