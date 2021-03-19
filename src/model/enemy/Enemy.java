package model.enemy;

import javafx.scene.layout.Pane;
import model.MovingObjects;

public abstract class Enemy extends MovingObjects {

    private int remainingHealth;

    public Enemy(double x, double y, String pathStatic, String pathMoving, String pathStaticShot, String pathMovingShot, Pane mainLayer) {
        super(x, y, pathStatic, pathMoving, pathStaticShot, pathMovingShot, mainLayer);
        this.removeFromLayer();
    }


    public int getRemainingHealth() {
        return remainingHealth;
    }
    public void setRemainingHealth(int remainingHealth) {
        this.remainingHealth = remainingHealth;
    }

}
