package map;

import javafx.scene.layout.Pane;
import model.StaticObjects;

public class Door extends StaticObjects {

    private int doorId;
    private boolean closedDoors;

    public Door(double x, double y, String pathStatic, Pane layer, int doorId) {
        super(x, y, pathStatic, layer);
        this.closedDoors = false;
        this.doorId = doorId;
    }


    public int getDoorId() {
        return doorId;
    }
    public void setDoorId(int doorId) {
        this.doorId = doorId;
    }

    public boolean isClosedDoors() {
        return closedDoors;
    }
    public void setClosedDoors(boolean closedDoors) {
        this.closedDoors = closedDoors;
    }

}
