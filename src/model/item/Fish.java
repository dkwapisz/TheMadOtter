package model.item;

import javafx.scene.layout.Pane;
import model.hero.Hero;

public class Fish extends Item{


    public Fish(double x, double y, Pane layer) {
        super(x, y, "graphics/items/fish.gif", layer);
    }

    @Override
    public boolean onTouch(Hero hero) {
        boolean picked = false;
        if (hero.getRemainingLives() <= 19) {
            if (hero.getRemainingLives() == 19){
                hero.setRemainingLives(hero.getRemainingLives() + 1);
            } else {
                hero.setRemainingLives(hero.getRemainingLives() + 2);
            }
            picked = true;
        }
        return picked;
    }

}
