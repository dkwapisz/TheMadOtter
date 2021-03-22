package model.item;

import javafx.scene.layout.Pane;

public class Fish extends Item{

    private int hpBonus;

    public Fish(double x, double y, Pane layer) {
        super(x, y, "graphics/items/fish.gif", layer);
        hpBonus = 2;
    }

    public int getHpBonus() {
        return hpBonus;
    }
    public void setHpBonus(int hpBonus) {
        this.hpBonus = hpBonus;
    }
}
