package model.item;

import javafx.scene.layout.Pane;
import model.hero.Hero;

public class Dolar extends Item {

    public Dolar(double x, double y, Pane layer) {
        super(x, y, "graphics/items/dolar.gif", layer);
    }

    @Override
    public boolean onTouch(Hero hero) {
        hero.setMoney(hero.getMoney()+5);
        return true;
    }
}
