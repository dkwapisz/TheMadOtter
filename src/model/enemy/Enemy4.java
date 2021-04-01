package model.enemy;

import javafx.scene.layout.Pane;

import java.util.Random;

public class Enemy4 extends Enemy{

    private Random random = new Random();
    private long lastChange = 0;
    private int[] randomVel = new int[] {-5, 0, 5};

    public Enemy4(double x, double y, Pane mainLayer) {
        super(x, y, "/graphics/enemies/enemy4.png", "/graphics/enemies/enemy4.png", null, null, mainLayer);
        setFollowing(false);
        setFlying(true);
        setShooting(false);
        setExplosive(false);
        setVelX(randomVel[random.nextInt(3)]);
        setVelY(randomVel[random.nextInt(3)]);
        setRemainingHealth(30);
        setDmg(1);
    }

    @Override
    public void specificMovement() {
        long time = System.currentTimeMillis();
        if (time > lastChange + random.nextInt(750)+500) {
            lastChange = time;
            setVelX(randomVel[random.nextInt(3)]);
            setVelY(randomVel[random.nextInt(3)]);
        }
    }
}
