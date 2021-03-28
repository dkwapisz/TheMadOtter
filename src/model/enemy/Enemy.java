package model.enemy;

import javafx.scene.layout.Pane;
import model.Bullet;
import model.MovingObjects;
import model.hero.Hero;

public abstract class Enemy extends MovingObjects {

    private int remainingHealth; // MUST HAVE
    private int dmg; // MUST HAVE
    private boolean following; // MUST HAVE
    private boolean flying; // MUST HAVE
    private boolean shooting; // MUST HAVE
    private boolean explosive; // MUST HAVE


    private int followingVel = 0; // tylko dla przeciwników podążających za graczem - nie więcej niż 4!
    private String bulletPath = null; // tylko dla przeciwników strzelających, ścieżka do grafiki pocisku
    private int bulletVelFactor = 0; // prędkość pocisków przeciwników strzelających

    private double bulletVelX = 0; // atrybut pomocniczy, nigdzie nie ustawiać
    private double bulletVelY = 0; // atrybut pomocniczy, nigdzie nie ustawiać
    private long lastEnemyShot = 0; // atrybut pomocniczy, nigdzie nie ustawiać


    public Enemy(double x, double y, String pathStatic, String pathMoving, String pathStaticShot, String pathMovingShot, Pane mainLayer) {
        super(x, y, pathStatic, pathMoving, pathStaticShot, pathMovingShot, mainLayer);
        this.removeFromLayer();
    }

    public void shot(Hero hero, int velFactor) {
        long time = System.currentTimeMillis();
        if(this.shooting) {
            if (time > lastEnemyShot + 2000) {
                lastEnemyShot = time;
                double vecLength;
                vecLength = Math.hypot(hero.getX() - getX(), hero.getY() - getY());
                bulletVelX = velFactor * (hero.getX() - getX()) / vecLength;
                bulletVelY = velFactor * (hero.getY() - getY()) / vecLength;
                hero.getActualRoom().getEnemyBullets().add(new Bullet(getX() + getDimension().getWidth() / 2, getY() + getDimension().getHeight() / 2, bulletVelX, bulletVelY, dmg, bulletPath, bulletPath, getLayer()));
            }
        }
    }

//    public Rectangle getCenterBounds() {
//        Rectangle centerBounds = new Rectangle((int) getX() + 2*getVelX() +  getDimension().getWidth()/4, (int) getY() + 2*getVelY() + getDimension().getHeight()/4, getDimension().getWidth()/2, getDimension().getHeight()/2);
//        centerBounds.setArcHeight(getDimension().getHeight()/8);
//        centerBounds.setArcWidth(getDimension().getWidth()/8);
//        return centerBounds;
//    }

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

    public double getBulletVelX() {
        return bulletVelX;
    }
    public void setBulletVelX(double bulletVelX) {
        this.bulletVelX = bulletVelX;
    }

    public double getBulletVelY() {
        return bulletVelY;
    }
    public void setBulletVelY(double bulletVelY) {
        this.bulletVelY = bulletVelY;
    }

    public int getDmg() {
        return dmg;
    }
    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public boolean isShooting() {
        return shooting;
    }
    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    public int getBulletVelFactor() {
        return bulletVelFactor;
    }
    public void setBulletVelFactor(int bulletVelFactor) {
        this.bulletVelFactor = bulletVelFactor;
    }

    public boolean isExplosive() {
        return explosive;
    }
    public void setExplosive(boolean explosive) {
        this.explosive = explosive;
    }

}
