package model.enemy;


import javafx.scene.layout.Pane;
import model.Bullet;
import model.hero.Hero;

import java.util.Random;

public class Enemy6 extends Enemy{

    private long lastEnemyShot = 0;
    private long lastChange = 0;
    private Random random = new Random();
    private boolean up = false;
    private double[] bulletVelTabX = new double[] {5, 2.5, 0, -2.5, -5, -2.5, 0, 2.5};
    private double[] bulletVelTabY = new double[] {0, -2.5, -5, -2.5, 0, 2.5, 5, 2.5};

    public Enemy6(double x, double y, Pane layer) {
        super(x, y, "graphics/enemies/diglet_test.gif", "graphics/enemies/diglet_test.gif", "/graphics/enemies/diglet.png", "/graphics/enemies/diglet.png", layer);
        setFollowing(false);
        setFlying(false);
        setShooting(true);
        setExplosive(false);
        setBulletVelFactor(6);
        setCooldownShot(300);
        setRemainingHealth(20);
        setBulletPath("graphics/items/bullets/EnemyBullets/digletBall.png");
        setDmg(1);
    }

    @Override
    public void specificMovement() {
        long time = System.currentTimeMillis();
        if (time > lastChange + random.nextInt(3000)+2000) {
            lastChange = time;
            setLocation(random.nextInt(600)+100, random.nextInt(600)+100);
            up = true;
        }
    }

    @Override
    public void shot(Hero hero, int velFactor) {
        long time = System.currentTimeMillis();
        if(up) {
            if (time > lastEnemyShot + getCooldownShot()) {
                lastEnemyShot = time;
                for (int j = 0; j < bulletVelTabX.length; j++) {
                    setBulletVelX(bulletVelTabX[j]);
                    setBulletVelY(bulletVelTabY[j]);
                    hero.getActualRoom().getEnemyBullets().add(new Bullet(getX() + getDimension().getWidth() / 2, getY() + getDimension().getHeight() / 2, getBulletVelX(), getBulletVelY(), getDmg(), getBulletPath(), getBulletPath(), getLayer()));
                }
                up = false;
            }
        }
    }
}