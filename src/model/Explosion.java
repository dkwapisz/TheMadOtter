package model;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import map.Room;

public class Explosion extends StaticObjects {

    Room actualRoom;

    public Explosion(double x, double y, Pane layer, Room room) {
        super(x, y, "graphics/explosion64x64.gif", layer);
        actualRoom = room;
        generateAndRemove();
    }

    public void generateAndRemove() {
        Explosion thisExplosion = this;
        long time = System.nanoTime();
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (l - 800_000_000 > time) {
                    this.stop();
                }
            }
            @Override
            public void stop() {
                actualRoom.getExplosions().remove(thisExplosion);
                removeFromLayer();
                super.stop();
            }
        };
        animationTimer.start();
    }

//    public void generateAndRemove() {
//        Timeline timeline = new Timeline(
//                new KeyFrame(Duration.millis(800), e -> {
//                    actualRoom.getExplosions().remove(this);
//                    removeFromLayer();
//                })
//        );
//        timeline.play();
//    }

}
