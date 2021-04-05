package model.item;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.hero.Hero;

public class Sign extends Item {

    private final Pane layer;
    private final Label information = new Label();

    public Sign(double x, double y, Pane layer, String text) {
        super(x, y, "graphics/items/sign.png", layer);
        this.layer = layer;
        information.setText(text);
        information.setStyle("-fx-font: 14 verdana;");
        information.relocate(x-25, y-20);
    }

    public void showText() {
        try {
            this.layer.getChildren().add(information);
        } catch (IllegalArgumentException ignored) {}
    }

    public void hideText() {
        try {
            this.layer.getChildren().remove(information);
        } catch (IllegalArgumentException ignored) {}
    }

}
