package model.item.guns;

import javafx.scene.layout.Pane;

public class M16 extends Gun {

    public M16(double x, double y, Pane layer) {
        super(x, y, "graphics/items/guns/m16.png", "graphics/items/bullets/m16Bullet.png", layer);
        setAmmo(30);
        setBulletVel(16);
        setCooldownShot(700);
        setDmg(6);
        setGunName("M16");
    }

}