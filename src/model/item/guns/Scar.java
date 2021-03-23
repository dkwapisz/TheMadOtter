package model.item.guns;

import javafx.scene.layout.Pane;

public class Scar extends Gun {

    public Scar(double x, double y, Pane layer) {
        super(x, y, "graphics/items/guns/scar.png", "graphics/items/bullets/scarBullet.png", layer);
        setAmmo(30);
        setBulletVel(15);
        setCooldownShot(125);
        setDmg(7);
        setGunName("Scar");
    }

}