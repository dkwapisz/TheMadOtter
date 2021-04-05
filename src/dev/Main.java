package dev;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import map.Door;
import model.hero.Hero;


public class Main extends Application {
    static Hero hero;
    private InputManager inputManager;
    private Label infoLabel;
    private ImageView gunReview;
    private MiniMap miniMap;

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

        infoLabel = new Label();
        infoLabel.setStyle("-fx-font: 16 verdana;");
        infoLabel.setTextFill(Color.WHITE);
        infoLabel.relocate(800, 200);
        root.getChildren().add(infoLabel);

        gunReview = new ImageView(hero.getActualGun().getImageView().getImage());
        gunReview.relocate(900 - gunReview.getImage().getWidth()/2,700 + (62 - gunReview.getImage().getHeight())/2);
        root.getChildren().add(gunReview);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(25), e->run()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        EventHandling.addEventHandlers(primaryStage.getScene());
        this.miniMap = new MiniMap(hero);

    }

    private void run() {
        inputManager.handlePlayerActions();
        inputManager.hero.updateHero();
        infoLabel.setText("Gun: " + hero.getActualGun().getGunName() +
                        "\nGun Dmg: " + hero.getActualGun().getDmg() +
                        "\nGun coold.: " + hero.getActualGun().getCooldownShot() + " [ms]" +
                        "\nBullet Vel: " + hero.getActualGun().getBulletVel() +
                        "\nHP: " + hero.getRemainingLives() +
                        "\nMoney: " + hero.getMoney() + " $" +
                        "\nBombs: " + hero.getBombs() +
                        "\nClean Room: " + hero.getActualRoom().isClean() +
                        "\nFloorID: " + hero.getFloor().getFloorId() +
                        "\nRoomID: " + hero.getActualRoom().getRoomId() +
                        "\nShooting: " + hero.isShooting() +
                        "\nTrapdoor Open: " + hero.getFloor().getTrapdoor().isOpen());

        updateGun();
        miniMap.updateMiniMap();
    }

    private void updateGun() {
        if(hero.getActualGun().getImageView().getImage() != gunReview.getImage()) {
            gunReview.setImage(hero.getActualGun().getImageView().getImage()); // wywołuje się tylko, gdy aktualna wybrana broń różni się od wyświetlonej :)
            gunReview.relocate(900 - gunReview.getImage().getWidth()/2,700 + (62 - gunReview.getImage().getHeight())/2);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
