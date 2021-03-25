package model.enemy;

import javafx.scene.layout.Pane;

public class Wasp extends Enemy{

    public Wasp(double x, double y, Pane mainLayer) {
        super(x, y, "/graphics/enemies/wasp.gif", "/graphics/enemies/wasp.gif", null, null, mainLayer);
        setFollowing(true);
        setFlying(true);
        setShooting(false);
        setFollowingVel(3);
        setRemainingHealth(30);
        setDmg(2);
    }

}
