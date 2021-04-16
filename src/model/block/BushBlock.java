package model.block;



import javafx.scene.layout.Pane;
import model.hero.Hero;

public class BushBlock extends Block{
    private boolean ifInBush;
    public BushBlock(double x, double y, Pane layer) {
        super(x, y, "graphics/blocks/bush.png", layer);
        setBreakable(false);
        setPrickly(false);
        setToPass(true);
        setIfInBush(false);
    }


    public boolean isIfInBush() {
        return ifInBush;
    }

    public void setIfInBush(boolean ifInBush) {
        this.ifInBush = ifInBush;
    }
}