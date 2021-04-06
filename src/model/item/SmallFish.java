package model.item;

import javafx.scene.layout.Pane;
import model.hero.Hero;

public class SmallFish extends Item{


    public SmallFish(double x, double y, Pane layer) {
        super(x, y, "graphics/items/smallFish.gif", layer);
    }

    @Override
    public boolean onTouch(Hero hero) {
        boolean picked = false;
        if (hero.getRemainingLives() < 20) {
            hero.setRemainingLives(hero.getRemainingLives() + 1);
            picked = true;
        }
        return picked;
    }

}
