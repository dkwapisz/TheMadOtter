package model.item;

import javafx.scene.layout.Pane;
import model.StaticObjects;
import model.hero.Hero;

public abstract class Item extends StaticObjects {

    public Item(double x, double y, String pathStatic, Pane layer) {
        super(x, y, pathStatic, layer);
    }

}
