package dev;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.hero.Hero;


public class Main extends Application {
    private Hero hero;
    private InputManager inputManager;
    private Label label;
    private Pane layer;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane root = FXMLLoader.load(getClass().getResource("mainStage.fxml"));
        primaryStage.setTitle("TheGame");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.getScene().getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
        primaryStage.setResizable(false);

        hero = new Hero(360, 360, root);
        inputManager = new InputManager(hero);

        label = new Label();
        label.setStyle("-fx-font: 20 arial;");
        label.setTextFill(Color.WHITE);
        root.getChildren().add(label);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(25), e->run()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        EventHandling.addEventHandlers(primaryStage.getScene());
    }

    private void run() {
        inputManager.handlePlayerActions();
        inputManager.hero.updateLocation();
        inputManager.hero.getMap().updateEnemy(hero.getActualRoom());
        label.setText(Integer.toString(hero.getActualRoom().getRoomId()));


//        System.out.println("Room: " + checkRoom);
//        System.out.println("Door: " + inputManager.hero.getActualRoom().getDoor());

    }


    public static void main(String[] args) {
        launch(args);
    }

}
