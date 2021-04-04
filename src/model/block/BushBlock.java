package model.block;



import javafx.scene.layout.Pane;

public class BushBlock extends Block{
    public BushBlock(double x, double y, Pane layer) {
        super(x, y, "graphics/blocks/bushBlock.png", layer);
        setBreakable(false);
        setPrickly(false);
        setToPass(true);
        getImageView().setOpacity(0.6);
    }
}