package model.block;

import javafx.scene.layout.Pane;
import model.StaticObjects;

public abstract class Block extends StaticObjects {

    private boolean breakable; // blok zniszczalny
    private boolean toPass;    // blok, przez który można przejść
    private boolean prickly;   // blok zadający obrażenia

    public Block(double x, double y, String pathStatic, Pane layer) {
        super(x, y, pathStatic, layer);
    }


    public boolean isBreakable() {
        return breakable;
    }
    public void setBreakable(boolean breakable) {
        this.breakable = breakable;
    }

    public boolean isToPass() {
        return toPass;
    }
    public void setToPass(boolean toPass) {
        this.toPass = toPass;
    }

    public boolean isPrickly() {
        return prickly;
    }
    public void setPrickly(boolean prickly) {
        this.prickly = prickly;
    }
}
