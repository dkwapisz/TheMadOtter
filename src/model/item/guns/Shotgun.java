package model.item.guns;

import javafx.scene.layout.Pane;

public class Shotgun extends Gun {

    public Shotgun(double x, double y, Pane layer) {
        super(x, y, "graphics/items/guns/shotgun.png", "graphics/items/bullets/shotgunBullet.png", layer);
        setAmmo(12);
        setBulletVel(14);
        setCooldownShot(1250);
        setDmg(2);
        setGunName("Shotgun");
    }

}