package model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import map.Room;

public class Explosion extends StaticObjects{

    Room actualRoom;

    public Explosion(double x, double y, Pane layer, Room room) {
        super(x, y, "graphics/explosion64x64.gif", layer);
        actualRoom = room;
        generateAndRemove();
    }

    public void generateAndRemove(){
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(800), e -> {
                    actualRoom.getExplosions().remove(this);
                    removeFromLayer();
                })
        );
        timeline.play();
    }

}
