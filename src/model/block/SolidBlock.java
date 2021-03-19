package model.block;

import javafx.scene.layout.Pane;

public class SolidBlock extends Block{

    public SolidBlock(double x, double y, String pathStatic, Pane layer) {
        super(x, y, pathStatic, layer);
        setBreakable(false);
        setPrickly(false);
        setToPass(false);
    }
}
