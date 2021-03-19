package model.item.guns;

import javafx.scene.layout.Pane;

public class Pistol extends Gun {

    public Pistol(Pane layer) {
        super(1000, 1000, "graphics/items/uzi.png", layer);
        setAmmo(0);
        setBulletVel(8);
        setCooldownShot(500);
        setDmg(2);
        setGunName("Pistol");
    }




}
