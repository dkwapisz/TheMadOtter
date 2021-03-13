package map;

import java.util.ArrayList;

public class Room {

    private ArrayList<Door> door;
    private boolean clean;
    private int roomId;


    public Room(ArrayList<Door> door, boolean clean, int roomId) {
        this.door = door;
        this.clean = clean;
        this.roomId = roomId;
    }


    public ArrayList<Door> getDoor() {
        return door;
    }
    public void setDoor(ArrayList<Door> door) {
        this.door = door;
    }

    public boolean isClean() {
        return clean;
    }
    public void setClean(boolean clean) {
        this.clean = clean;
    }

    public int getRoomId() {
        return roomId;
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
