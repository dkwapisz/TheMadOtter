package dev;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.hero.Hero;


public class Main extends Application {
    private Hero hero;
    private InputManager inputManager;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("123");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        hero = new Hero(100,100,root);
        inputManager = new InputManager(hero);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e->run()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        EventHandling.addEventHandlers(primaryStage.getScene());
    }

    private void run() {
        inputManager.handlePlayerActions();
        inputManager.hero.updateLocation();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
