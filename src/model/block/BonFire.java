package model.block;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class BonFire extends Block {

    public BonFire(double x, double y, Pane layer) {
        super(x, y,"graphics/blocks/bonfire1.gif" , layer);
        setBreakable(false);
        setPrickly(true);
        setToPass(true);
        setDmg(4);
    }

    public void putOutFire() {
        setDmg(1);
        getImageView().setImage(new Image("graphics/blocks/bonfire2.gif"));
    }

}
