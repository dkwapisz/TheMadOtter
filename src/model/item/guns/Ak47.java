package model.item.guns;

import javafx.scene.layout.Pane;

public class Ak47 extends Gun {

    public Ak47(double x, double y, Pane layer) {
        super(x, y, "graphics/items/guns/ak47.png", "graphics/items/bullets/ak47Bullet.png", layer);
        setAmmo(30);
        setBulletVel(15);
        setCooldownShot(150);
        setDmg(8);
        setGunName("AK-47");
    }

}