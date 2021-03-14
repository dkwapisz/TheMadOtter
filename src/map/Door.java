package map;

import javafx.scene.layout.Pane;
import model.ObjectsBehaviour;

public class Door extends ObjectsBehaviour {

    private int doorId;
    private boolean closedDoors;

    public Door(double x, double y, String pathStatic, String pathMoving, Pane mainLayer, int doorId) {
        super(x, y, pathStatic, pathMoving, mainLayer);
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
