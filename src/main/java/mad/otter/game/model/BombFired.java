package mad.otter.game.model;

import javafx.scene.layout.Pane;
import mad.otter.game.map.Room;

public class BombFired extends StaticObjects{

    public BombFired(double x, double y, Pane layer) {
        super(x, y, "graphics/items/bombFired.gif", layer);
    }
}
