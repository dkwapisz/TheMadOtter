package model.item.guns;

import javafx.scene.layout.Pane;

public class Glock extends Gun {

    public Glock(double x, double y, Pane layer) {
        super(x, y, "graphics/items/guns/glock.png", "graphics/items/bullets/glockBullet.png", layer);
        setBulletVel(8);
        setCooldownShot(100);
        setDmg(2);
        setGunName("Glock");
    }

}