package model.block;



import javafx.scene.layout.Pane;
import model.hero.Hero;

public class BushBlock extends Block{
    public BushBlock(double x, double y, Pane layer) {
        super(x, y, "graphics/blocks/bush.png", layer);
        setBreakable(false);
        setPrickly(false);
        setToPass(true);
    }

    @Override
    public void onTouch(Hero hero) {
        if(!hero.isDamaged()) {
            hero.getImageView().setOpacity(0.7);
        }
    }
    @Override
    public void stoppedTouching(Hero hero) {
        if(!hero.isDamaged()) {
            hero.getImageView().setOpacity(1);
        }
    }

}