package model;

import javafx.scene.layout.Pane;

public class Bullet extends MovingObjects {

    private int dmg;

    public Bullet(double x, double y, int bulletVelX, int bulletVelY, int dmg, String pathStatic, String pathMoving, Pane layer) {
        super(x, y, pathStatic, pathMoving, null, null, layer);
        this.dmg = dmg;

        setVelX(bulletVelX);
        setVelY(bulletVelY);

        getImageView().toBack();
    }

    public boolean removeBullets() {
        if((getX() + getVelX() < 30 || getX() + getVelX() > 770 - getImageStatic().getHeight()/4 || getY() + getVelY() < 30 || getY() + getVelY() > 770 - getImageStatic().getHeight()/4)) {
            return true;
        }
        return false;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }
}
