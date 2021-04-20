package model.item;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import model.hero.Hero;

public class RedSphere extends Item {

    public RedSphere(double x, double y, Pane layer) {
        super(x, y, "graphics/items/redSphere.png", layer);
        setPoints(500);
    }

    @Override
    public boolean onTouch(Hero hero) {
        if (hero.getDmgFactor() == 1) {
            AnimationTimer animationTimer = new AnimationTimer() {
                long time = System.nanoTime();
                @Override
                public void stop() {
                    if (!hero.isPaused()) {
                        hero.setDmgFactor(1);
                        hero.getPowerUpTimers().remove(this);
                        super.stop();
                    }
                }

                @Override
                public void start() {
                    time = System.nanoTime();
                    super.start();
                }

                @Override
                public void handle(long l) {
                    if (hero.getDmgFactor() == 1) {
                        hero.setDmgFactor(2);
                        hero.getPowerUpTimers().add(this);
                    }
                    if (l/1_000_000 - 15_000 > time/1_000_000) { // czas trwania: 15s
                        this.stop();
                    }
                }

            };
            animationTimer.start();
            return true;
        } else {
            return false;
        }
    }
}
