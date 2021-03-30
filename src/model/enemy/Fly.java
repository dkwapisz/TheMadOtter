package model.enemy;

import javafx.scene.layout.Pane;

public class Fly extends Enemy{

    public Fly(double x, double y, Pane mainLayer) {
        super(x, y, "/graphics/enemies/fly.gif", "/graphics/enemies/fly.gif", null, null, mainLayer);
        setFollowing(false);
        setFlying(true);
        setShooting(false);
        setExplosive(false);
        setVelX(5);
        setVelY(-5);
        setRemainingHealth(30);
        setDmg(1);
    }

}
