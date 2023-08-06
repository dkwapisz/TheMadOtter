package mad.otter.game.dev.controller;

import mad.otter.game.dev.Main;
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
import java.util.Objects;
import java.util.Scanner;

public class MenuController {

    @FXML
    private Label title;

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
        settingsButton.setDisable(true);
        newGameButton.setOnAction(event -> {
            Main main = new Main();
            Stage stage = (Stage) newGameButton.getScene().getWindow();
            try {
                Main.nick = nickField.getText();
                if (Main.nick.length() > 10) {
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
                Pane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/help.fxml")));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                stage.setResizable(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        settingsButton.setOnAction(event -> {
            Stage stage = (Stage) settingsButton.getScene().getWindow();
            try {
                Pane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/settings.fxml")));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                stage.setResizable(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        highScoreButton.setOnAction(event -> {
            Stage stage = (Stage) highScoreButton.getScene().getWindow();
            setHighScores();
            try {
                Pane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/highScore.fxml")));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                stage.setResizable(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void setHighScores() {
        highScores = getHighestScores();
    }

    public static ArrayList<String> getHighestScores() {
        Scanner scanner = new Scanner(Objects.requireNonNull(MenuController.class.getResourceAsStream("/score/HighScore.txt")));
        ArrayList<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
        scanner.close();
        return list;
    }


}
