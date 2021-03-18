package model;

import javafx.scene.layout.Pane;

public class Bullet extends MovingObjects {
    public Bullet(double x, double y, int velX, int velY, String pathStatic, String pathMoving, Pane layer) {
        super(x, y, pathStatic, pathMoving, layer);
        setVelX(velX);
        setVelY(velY);
    }

    public boolean removeBullets() {
        if((getX() + getVelX() < 30 || getX() + getVelX() > 770 - getImageStatic().getHeight()/4 || getY() + getVelY() < 30 || getY() + getVelY() > 770 - getImageStatic().getHeight()/4)) {
            return true;
        }
        return false;
    }
}
