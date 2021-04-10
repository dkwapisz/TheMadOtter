package model.block;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import map.Room;

public class SolidBlock extends Block{

    private final Pane layer;

    public SolidBlock(double x, double y, Pane layer) {
        super(x, y, "graphics/blocks/solidBlock.png", layer);
        setBreakable(false);
        setPrickly(false);
        setToPass(false);
        this.layer = layer;
    }

    public void destroyAnimation(Room room) {
        ImageView destroyed = new ImageView(new Image("graphics/blocks/solidBlockAnimation.gif"));
        destroyed.relocate(this.getX(), this.getY());
        layer.getChildren().add(destroyed);
        room.getAnimations().add(destroyed);
        destroyed.toBack();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(800), e -> layer.getChildren().remove(destroyed)));
        timeline.play();
    }
}
