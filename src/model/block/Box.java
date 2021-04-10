package model.block;

import javafx.scene.layout.Pane;

public class Box extends Block {
    private int hp = 1;

    public Box (double x, double y, Pane layer) {
        super(x, y, "graphics/blocks/box.png", layer);
        setBreakable(true);
        setPrickly(false);
        setToPass(false);

    }

    @Override
    public int getHp() {
        return hp;
    }
    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }
}