package model.enemy;

import javafx.scene.layout.Pane;

public class Enemy1 extends Enemy{

    public Enemy1(double x, double y, Pane layer) {
        super(x, y, "/graphics/enemies/enemy1.png", "/graphics/enemies/enemy1.png", "/graphics/enemies/enemy1.png", "/graphics/enemies/enemy1.png", layer);
        setFollowing(true);
        setFlying(false);
        setShooting(true);
        setExplosive(false);
        setFollowingVel(2);
        setBulletVelFactor(8);
        setCooldownShot(1500);
        setRemainingHealth(20);
        setBulletPath("graphics/items/bullets/ak47Bullet.png");
        setDmg(1);
    }

}