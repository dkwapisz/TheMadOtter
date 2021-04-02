package model.item;

import javafx.scene.layout.Pane;
import model.hero.Hero;

public class Fish extends Item{

    private int hpBonus;

    public Fish(double x, double y, Pane layer) {
        super(x, y, "graphics/items/fish.gif", layer);
        hpBonus = 2;
    }

    @Override
    public boolean onTouch(Hero hero) {
        boolean picked = false;
        if(hero.getRemainingLives() <= 19) {
            if(hero.getRemainingLives() == 19){
                hero.setRemainingLives(hero.getRemainingLives() + this.getHpBonus() - 1);
            }else {
                hero.setRemainingLives(hero.getRemainingLives() + this.getHpBonus());
            }
            picked = true;
        }
        return picked;
    }

    public int getHpBonus() {
        return hpBonus;
    }
    public void setHpBonus(int hpBonus) {
        this.hpBonus = hpBonus;
    }
}
