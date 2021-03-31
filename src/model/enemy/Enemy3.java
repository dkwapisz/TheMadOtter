package model.enemy;

// follow + explosive (kamikaze)

import javafx.scene.layout.Pane;

public class Enemy3 extends Enemy{

    public Enemy3(double x, double y, Pane mainLayer) {
        super(x, y, "/graphics/enemies/enemy3.png", "/graphics/enemies/enemy3.png", "/graphics/enemies/enemy3.png", "/graphics/enemies/enemy3.png", mainLayer);
        setFollowing(true);
        setFlying(false);
        setShooting(false);
        setExplosive(true);
        setFollowingVel(3);
        setRemainingHealth(15);
        setDmg(0);
    }

}