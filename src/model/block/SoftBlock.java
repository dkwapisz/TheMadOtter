package model.block;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class SoftBlock extends Block{
    private int hp;

    public SoftBlock(double x, double y, Pane layer) {
        super(x, y,"graphics/blocks/softBlock1.png" , layer);
        hp = 3;
        setBreakable(true);
        setPrickly(false);
        setToPass(false);
    }

    public void changeImage(){
        if(hp == 2){
            getImageView().setImage(new Image("graphics/blocks/softBlock2.png"));
        }
        if(hp == 1){
            getImageView().setImage(new Image("graphics/blocks/softBlock3.png"));
        }
    }


    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
