package model.block;

import javafx.scene.layout.Pane;

public class SolidBlock extends Block{

    public SolidBlock(double x, double y, Pane layer) {
        super(x, y, "graphics/blocks/solidBlock.png", layer);
        setBreakable(false);
        setPrickly(false);
        setToPass(false);
    }
}
