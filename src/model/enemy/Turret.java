package model.enemy;

import javafx.scene.layout.Pane;

public class Turret extends Enemy{

    public Turret(double x, double y, Pane mainLayer) {
        super(x, y, "/graphics/enemies/turret.png", "/graphics/enemies/turret.png", "/graphics/enemies/turret.png", "/graphics/enemies/turret.png", mainLayer);
        setFollowing(false);
        setFlying(false);
        setShooting(true);
        setVelX(0);
        setVelY(0);
        setBulletVelFactor(8);
        setRemainingHealth(100);
        setBulletPath("graphics/items/bullets/ak47Bullet.png");
        setDmg(1);
    }

}
