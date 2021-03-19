package map;

import javafx.scene.shape.Rectangle;
import model.Bullet;
import model.enemy.Enemy;
import model.hero.Hero;
import model.item.Gun;
import model.item.Item;

import java.util.ArrayList;

public class Room {

    private ArrayList<Door> door;
    private boolean clean;
    private int roomId;
    private ArrayList<Enemy> enemies;
    private ArrayList<Item> items;
    private ArrayList<Bullet> heroBullets;


    public Room(ArrayList<Door> door, boolean clean, int roomId, ArrayList<Enemy> enemies, ArrayList<Item> items) {
        this.door = door;
        this.clean = clean;
        this.roomId = roomId;
        this.enemies = enemies;
        this.items = items;
    }

    public void drawEnemies(){
        for (Enemy enemy : enemies) {
            enemy.addToLayer();
        }
    }

    public void eraseEnemies(){
        for (Enemy enemy : enemies) {
            enemy.removeFromLayer();
        }
    }

    public void drawItems(){
        for (Item item : items) {
            item.addToLayer();
        }
    }

    public void eraseItems(){
        for (Item item : items) {
            item.removeFromLayer();
        }
    }

    public void removeBullets(ArrayList<Bullet> list) {
        if (list == null) {
            return;
        }
        for(Bullet bullet : list) {
            bullet.removeFromLayer();
            heroBullets.remove(bullet);
        }
    }

    public void removeItems(ArrayList<Item> itemList) {
        if (itemList == null) {
            return;
        }
        for(Item item : itemList) {
            item.removeFromLayer();
            items.remove(item);
        }
    }

    public void eraseBullets() {
        for (Bullet bullet : heroBullets) {
            bullet.removeFromLayer();
        }
        heroBullets.clear();
    }

    public void openDoor(){
        for (Door door : door) {
            if (enemies.isEmpty() && door.isClosedDoors()) {
                door.setClosedDoors(false);
                door.addToLayer();
            }
        }
    }

    public void itemCollision(Hero hero) {
        ArrayList<Item> toBeRemoved = new ArrayList<>();
        Rectangle heroBounds = hero.getBounds();
        for(Item item : items) {
            Rectangle itemBounds = item.getBounds();
            if (heroBounds.intersects(itemBounds.getBoundsInParent())) {
                toBeRemoved.add(item);
                if(item instanceof Gun) {
                    hero.addNewGun((Gun) item);
                }
            }
        }
        removeItems(toBeRemoved);
    }

    public void makeHeroBulletList() {
        heroBullets = new ArrayList<>();
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

    public ArrayList<Bullet> getHeroBullets() {
        return heroBullets;
    }

    public void setHeroBullets(ArrayList<Bullet> heroBullets) {
        this.heroBullets = heroBullets;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
