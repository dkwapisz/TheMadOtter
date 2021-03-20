package model.item.guns;

import javafx.scene.layout.Pane;

public class SniperRifle extends Gun {

    public SniperRifle(double x, double y, Pane layer) {
        super(x, y, "graphics/items/guns/sniperRifle.png", "graphics/items/bullets/sniperBullet.png", layer);
        setAmmo(5);
        setBulletVel(20);
        setCooldownShot(1500);
        setDmg(80);
        setGunName("Sniper Rifle");
    }

}
