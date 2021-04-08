package model.enemy;

import javafx.scene.layout.Pane;

public class Enemy2 extends Enemy{

    public Enemy2(double x, double y, Pane layer) {
        super(x, y, "/graphics/enemies/enemy2.png", "/graphics/enemies/enemy2.png", "/graphics/enemies/enemy2.png", "/graphics/enemies/enemy2.png", layer);
        setFollowing(true);
        setFlying(false);
        setShooting(true);
        setExplosive(true);
        setFollowingVel(1);
        setBulletVelFactor(10);
        setCooldownShot(2000);
        setRemainingHealth(15);
        setBulletPath("graphics/items/bullets/ak47Bullet.png");
        setDmg(1);
    }

}