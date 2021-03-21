package model.enemy;

import javafx.scene.layout.Pane;
import model.Bullet;
import model.MovingObjects;
import model.hero.Hero;

public abstract class Enemy extends MovingObjects {

    private int remainingHealth;
    private boolean following;
    private int followingVel; // tylko dla przeciwników podążających za graczem
    private boolean flying;
    private String bulletPath;
    private double newVelX = 0;
    private double newVelY = 0;

    public Enemy(double x, double y, String pathStatic, String pathMoving, String pathStaticShot, String pathMovingShot, Pane mainLayer) {
        super(x, y, pathStatic, pathMoving, pathStaticShot, pathMovingShot, mainLayer);
        this.removeFromLayer();
    }

    public void shot(Hero hero, int dmg, int velFactor) {
        double vecLength;
        vecLength = Math.hypot(hero.getX() - getX(), hero.getY() - getY());
        newVelX = velFactor*(hero.getX() - getX())/vecLength;
        newVelY = velFactor*(hero.getY() - getY())/vecLength;
        hero.getActualRoom().getEnemyBullets().add(new Bullet(getX() + getDimension().getWidth()/2, getY() + getDimension().getHeight()/2, newVelX, newVelY, dmg, bulletPath, bulletPath, getLayer()));
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

    public String getBulletPath() {
        return bulletPath;
    }
    public void setBulletPath(String bulletPath) {
        this.bulletPath = bulletPath;
    }

    public double getNewVelX() {
        return newVelX;
    }
    public void setNewVelX(double newVelX) {
        this.newVelX = newVelX;
    }

    public double getNewVelY() {
        return newVelY;
    }
    public void setNewVelY(double newVelY) {
        this.newVelY = newVelY;
    }
}
