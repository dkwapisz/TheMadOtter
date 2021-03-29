package model.block;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Trapdoor extends Block{

    private boolean open = false;

    public Trapdoor(double x, double y, Pane layer) {
        super(x, y, "graphics/trapDoorClose.png", layer);
        setBreakable(false);
        setPrickly(false);
        setToPass(true);
        getImageView().toBack();
    }


    public void open() {
        open = true;
        getImageView().setImage(new Image("graphics/trapDoorOpen.png"));
    }

    public boolean isOpen() {
        return open;
    }
    public void setOpen(boolean open) {
        this.open = open;
    }
}
