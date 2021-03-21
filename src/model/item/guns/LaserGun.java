package model.item.guns;

import javafx.scene.layout.Pane;

public class LaserGun extends Gun {

    public LaserGun(double x, double y, Pane layer) {
        super(x, y, "graphics/items/guns/laserGun.gif", "graphics/items/bullets/laserBullet.gif", layer);
        setAmmo(30);
        setBulletVel(18);
        setCooldownShot(300);
        setDmg(30);
        setGunName("Laser Gun");
    }

}