package map;

import model.enemy.Enemy;

import java.util.ArrayList;

public class Room {

    private ArrayList<Door> door;
    private boolean clean;
    private int roomId;
    private ArrayList<Enemy> enemies;


    public Room(ArrayList<Door> door, boolean clean, int roomId, ArrayList<Enemy> enemies) {
        this.door = door;
        this.clean = clean;
        this.roomId = roomId;
        this.enemies = enemies;
    }

    public void drawEnemies(){
        for (Enemy enemy : enemies) {
            enemy.addToLayer();
        }
    }

    public void removeEnemies(){
        for (Enemy enemy : enemies) {
            enemy.removeFromLayer();
        }
    }

    public void openDoor(){
        for (Door door : door) {
            if (enemies.isEmpty() && door.isClosedDoors()) {
                door.setClosedDoors(false);
                door.addToLayer();
            }
        }
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

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

}
