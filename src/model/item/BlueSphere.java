package model.item;

import javafx.animation.AnimationTimer;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import model.hero.Hero;

public class BlueSphere extends Item {

    public BlueSphere(double x, double y, Pane layer) {
        super(x, y, "graphics/items/blueSphere.png", layer);
        setPoints(500);
    }

    @Override
    public boolean onTouch(Hero hero) {
        if (!hero.isGodmode()) {
            AnimationTimer animationTimer = new AnimationTimer() {
                long time = System.nanoTime();
                @Override
                public void stop() {
                    if (!hero.isPaused()) {
                        hero.setGodmode(false);
                        hero.getImageView().setEffect(new Glow(0));
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
                    if (!hero.isGodmode()) {
                        time = System.nanoTime();
                        hero.setGodmode(true);
                        hero.getImageView().setEffect(new Glow(0.5));
                        hero.getPowerUpTimers().add(this);
                    }
                    if (l/1_000_000 - 10_000 > time/1_000_000) { // czas trwania: 10s
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
