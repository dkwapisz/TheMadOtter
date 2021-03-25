package model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Explosion extends StaticObjects{

    public Explosion(double x, double y, Pane layer) {
        super(x, y,"graphics/explosion.gif", layer);
        generateAndRemove();
    }

    public void generateAndRemove(){
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(800), e -> {removeFromLayer();})
        );
        timeline.play();
    }
}
