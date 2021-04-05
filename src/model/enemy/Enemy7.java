package model.enemy;


import javafx.scene.layout.Pane;
import model.Bullet;
import model.hero.Hero;

import java.util.ArrayList;
import java.util.Random;

public class Enemy7 extends Enemy{

    private long lastEnemyShot = 0;
    private long lastChange = 0;
    private final Random random = new Random();
    private boolean up = false;
    private final ArrayList<int[]> spotLoc;

    public Enemy7(double x, double y, Pane layer, ArrayList<int[]> spotLoc) {
        super(x, y, "/graphics/enemies/enemy7.png", "/graphics/enemies/enemy7.png", "/graphics/enemies/enemy7.png", "/graphics/enemies/enemy7.png", layer);
        this.spotLoc = spotLoc;
        setFollowing(false);
        setFlying(false);
        setShooting(true);
        setExplosive(false);
        setBulletVelFactor(8);
        setCooldownShot(100);
        setRemainingHealth(20);
        setBulletPath("graphics/items/bullets/ak47Bullet.png");
        setDmg(1);
    }

    @Override
    public void specificMovement() {
        long time = System.currentTimeMillis();
        if (time > lastChange + random.nextInt(3000)+2000) {
            lastChange = time;
            setLocation(spotLoc.get(random.nextInt(spotLoc.size()))[0], spotLoc.get(random.nextInt(spotLoc.size()))[1]);
            up = true;
        }
    }

    @Override
    public void shot(Hero hero, int velFactor) {
        long time = System.currentTimeMillis();
        if(up) {
            if (time > lastEnemyShot + getCooldownShot()) {
                lastEnemyShot = time;
                double vecLength;
                vecLength = Math.hypot(hero.getX() - getX(), hero.getY() - getY());
                setBulletVelX(velFactor * (hero.getX() - getX()) / vecLength);
                setBulletVelY(velFactor * (hero.getY() - getY()) / vecLength);
                hero.getActualRoom().getEnemyBullets().add(new Bullet(getX() + getDimension().getWidth() / 2, getY() + getDimension().getHeight() / 2, getBulletVelX(), getBulletVelY(), getDmg(), getBulletPath(), getBulletPath(), getLayer()));
            }
            up = false;
        }
    }

}