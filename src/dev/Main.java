package dev;

import dev.statsPanel.HealthBar;
import dev.statsPanel.MiniMap;
import dev.statsPanel.Stats;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.hero.Hero;

public class Main extends Application {

    private static Hero hero;
    private InputManager inputManager;
    private ImageView gunReview;
    private MiniMap miniMap;
    private HealthBar healthBar;
    private Stats stats;
    private Label pauseLabel;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane root = FXMLLoader.load(getClass().getResource("mainStage.fxml"));
        primaryStage.setTitle("TheGame");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.getScene().getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
        primaryStage.setResizable(false);

        hero = new Hero(368, 568, root);
        inputManager = new InputManager(hero);

        gunReview = new ImageView(hero.getActualGun().getImageView().getImage());
        gunReview.relocate(900 - gunReview.getImage().getWidth()/2,700 + (62 - gunReview.getImage().getHeight())/2);
        root.getChildren().add(gunReview);

        pauseLabel = new Label();
        pauseLabel.setText("Game Paused");
        pauseLabel.setStyle("-fx-font: 25 forte");
        pauseLabel.relocate(35, 35);
        pauseLabel.setVisible(false);
        root.getChildren().add(pauseLabel);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(25), e -> gameLoop()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        EventHandling.addEventHandlers(primaryStage.getScene());

        this.miniMap = new MiniMap(hero);
        this.healthBar = new HealthBar(hero);
        this.stats = new Stats(hero);
    }

    private void gameLoop() {
        inputManager.handlePause(pauseLabel);
        if (!hero.isPaused()) {
            inputManager.handlePlayerActions();
            inputManager.hero.updateHero();
            updateGun();
            miniMap.updateMiniMap();
            healthBar.updateHealthBar();
            stats.updateBasicStats();
            stats.updateAdditionalStats();
        }
    }

    private void updateGun() {
        if (hero.getActualGun().getImageView().getImage() != gunReview.getImage()) {
            gunReview.setImage(hero.getActualGun().getImageView().getImage());
            gunReview.relocate(900 - gunReview.getImage().getWidth()/2,700 + (62 - gunReview.getImage().getHeight())/2);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
