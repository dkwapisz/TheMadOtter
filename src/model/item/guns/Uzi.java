package model.item.guns;

import javafx.scene.layout.Pane;

public class Uzi extends Gun {

    public Uzi(double x, double y, Pane layer) {
        super(x, y, "graphics/items/uzi.png", layer);
        setAmmo(30);
        setBulletVel(10);
        setCooldownShot(150);
        setDmg(3);
        setGunName("Uzi");
    }

}