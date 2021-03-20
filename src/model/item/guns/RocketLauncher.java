package model.item.guns;

import javafx.scene.layout.Pane;

public class RocketLauncher extends Gun {

    public RocketLauncher(double x, double y, Pane layer) {
        super(x, y, "graphics/items/guns/RocketLauncher.png", "graphics/items/bullets/rocketBullet.gif", layer);
        setAmmo(3);
        setBulletVel(12);
        setCooldownShot(2500);
        setDmg(200);
        setGunName("Rocket Launcher");
    }


}