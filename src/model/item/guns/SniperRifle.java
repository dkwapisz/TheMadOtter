package model.item.guns;

import javafx.scene.layout.Pane;

public class SniperRifle extends Gun {

    public SniperRifle(double x, double y, Pane layer) {
        super(x, y, "graphics/items/guns/sniperRifle.png", "graphics/items/bullets/sniperBullet.png", layer);
        setBulletVel(25);
        setCooldownShot(1500);
        setDmg(50);
        setGunName("Sniper Rifle");
    }

}
