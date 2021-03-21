package model.enemy;

import javafx.scene.layout.Pane;
import model.MovingObjects;

public abstract class Enemy extends MovingObjects {

    private int remainingHealth;
    private boolean following;
    private int followingVel; // tylko dla przeciwników podążających za graczem
    private boolean flying;

    public Enemy(double x, double y, String pathStatic, String pathMoving, String pathStaticShot, String pathMovingShot, Pane mainLayer) {
        super(x, y, pathStatic, pathMoving, pathStaticShot, pathMovingShot, mainLayer);
        this.removeFromLayer();
    }


    public int getRemainingHealth() {
        return remainingHealth;
    }
    public void setRemainingHealth(int remainingHealth) {
        this.remainingHealth = remainingHealth;
    }

    public boolean isFollowing() {
        return following;
    }
    public void setFollowing(boolean following) {
        this.following = following;
    }

    public boolean isFlying() {
        return flying;
    }
    public void setFlying(boolean flying) {
        this.flying = flying;
    }

    public int getFollowingVel() {
        return followingVel;
    }
    public void setFollowingVel(int followingVel) {
        this.followingVel = followingVel;
    }
}
