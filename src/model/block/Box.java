package model.block;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import map.Room;

public class Box extends Block {

    private int hp = 1;
    private final Pane layer;

    public Box (double x, double y, Pane layer) {
        super(x, y, "graphics/blocks/box.png", layer);
        setBreakable(true);
        setPrickly(false);
        setToPass(false);
        this.layer = layer;
    }

    public void destroyAnimation(Room room) {
        ImageView destroyed = new ImageView(new Image("graphics/blocks/boxAnimation.gif"));
        destroyed.relocate(this.getX(), this.getY());
        layer.getChildren().add(destroyed);
        room.getAnimations().add(destroyed);
        destroyed.toBack();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(720), e -> layer.getChildren().remove(destroyed)));
        timeline.play();
    }

    @Override
    public int getHp() {
        return hp;
    }
    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }
}