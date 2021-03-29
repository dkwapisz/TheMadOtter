package model.item.guns;

import javafx.scene.layout.Pane;

public class Mp5 extends Gun {

    public Mp5(double x, double y, Pane layer) {
        super(x, y, "graphics/items/guns/mp5.png", "graphics/items/bullets/mp5Bullet.png", layer);
        setBulletVel(16);
        setCooldownShot(125);
        setDmg(5);
        setGunName("MP5");
    }

}