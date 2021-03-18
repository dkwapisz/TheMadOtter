package model.enemy;

import javafx.scene.layout.Pane;
import model.MovingObjects;

public abstract class Enemy extends MovingObjects {

    private int remainingLives;

    public Enemy(double x, double y, String pathStatic, String pathMoving, Pane mainLayer) {
        super(x, y, pathStatic, pathMoving, mainLayer);
        this.removeFromLayer();
    }


    public int getRemainingLives() {
        return remainingLives;
    }
    public void setRemainingLives(int remainingLives) {
        this.remainingLives = remainingLives;
    }

}
