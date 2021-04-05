package model.item.guns;

import javafx.scene.layout.Pane;

public class Pistol extends Gun {

    public Pistol(Pane layer) {
        super(1000, 1000, "graphics/items/guns/pistol.png", "graphics/items/bullets/pistolBullet.png", layer);
        setBulletVel(9);
        setCooldownShot(400);
        setDmg(4);
        setGunName("Pistol");
    }

}
