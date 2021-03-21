package model.item.guns;

import javafx.scene.layout.Pane;

public class PlasmaGun extends Gun {

    public PlasmaGun(double x, double y, Pane layer) {
        super(x, y, "graphics/items/guns/plasmaGun.gif", "graphics/items/bullets/plasmaBullet.gif", layer);
        setAmmo(40);
        setBulletVel(15);
        setCooldownShot(100);
        setDmg(25);
        setGunName("Plasma Gun");
    }

}