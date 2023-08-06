package mad.otter.game.model.item;

import javafx.scene.layout.Pane;
import mad.otter.game.model.StaticObjects;
import mad.otter.game.model.hero.Hero;

public abstract class Item extends StaticObjects {

    private int points = 50;

    public Item(double x, double y, String pathStatic, Pane layer) {
        super(x, y, pathStatic, layer);
        getImageView().toBack();
    }

    public boolean onTouch(Hero hero) {return false;}

    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
}
