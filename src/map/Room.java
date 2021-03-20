package map;

import javafx.scene.shape.Rectangle;
import model.Bullet;
import model.MovingObjects;
import model.StaticObjects;
import model.block.Block;
import model.block.SoftBlock;
import model.enemy.Enemy;
import model.hero.Hero;
import model.item.Fish;
import model.item.guns.Gun;
import model.item.Item;
import model.item.guns.RocketLauncher;

import java.util.ArrayList;

public class Room {

    private ArrayList<Door> door;
    private boolean clean;
    private int roomId;
    private ArrayList<Enemy> enemies;
    private ArrayList<Item> items;
    private ArrayList<Bullet> heroBullets;
    private ArrayList<Block> blocks;
    private long lastTouch;


    public Room(ArrayList<Door> door, boolean clean, int roomId, ArrayList<Enemy> enemies, ArrayList<Item> items, ArrayList<Block> blocks) {
        this.door = door;
        this.clean = clean;
        this.roomId = roomId;
        this.enemies = enemies;
        this.items = items;
        this.blocks = blocks;
    }

    public void checkCollision(Hero hero){
        blockCollision(hero);
        enemyCollision(hero);
        itemCollision(hero);
        heroBulletsCollision(hero);
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

    public void drawBlocks(){
        for (Block block: blocks) {
            block.addToLayer();
            block.getImageView().toBack();
        }
    }

    public void eraseBlocks(){
        for (Block block : blocks) {
            block.removeFromLayer();
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

    public void removeMovingObjects(ArrayList<MovingObjects> list) {
        if (list == null) {
            return;
        }
        for(MovingObjects object : list) {
            object.removeFromLayer();
            if(object instanceof Bullet){
                heroBullets.remove(object);
            }if (object instanceof Enemy){
                enemies.remove(object);
            }

        }
    }

    public void removeStaticObjects(ArrayList<StaticObjects> list) {
        if (list == null) {
            return;
        }
        for(StaticObjects object : list) {
            object.removeFromLayer();
            if(object instanceof Item){
                items.remove(object);
            }
            if(object instanceof Block){
                blocks.remove(object);
            }
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
        ArrayList<StaticObjects> toBeRemoved = new ArrayList<>();
        Rectangle heroBounds = hero.getBounds();
        for(Item item : items) {
            Rectangle itemBounds = item.getBounds();
            if (heroBounds.intersects(itemBounds.getBoundsInParent())) {
                if(item instanceof Gun) {
                    hero.addNewGun((Gun) item);
                    toBeRemoved.add(item);
                }
                if(item instanceof Fish && hero.getRemainingLives() <= 19) {
                    if(hero.getRemainingLives() == 19){
                        hero.setRemainingLives(hero.getRemainingLives() + ((Fish) item).getHpBonus() - 1);
                    }else {
                        hero.setRemainingLives(hero.getRemainingLives() + ((Fish) item).getHpBonus());
                    }
                    toBeRemoved.add(item);
                }
            }
        }
        removeStaticObjects(toBeRemoved);
    }

    public void blockCollision(Hero hero) {
        Rectangle heroBounds = hero.getSmallerBounds();
        for(Block block : blocks) {
            Rectangle blockBounds = block.getBounds();
            if (heroBounds.intersects(blockBounds.getBoundsInParent())) {
                hero.setVelX(0);
                hero.setVelY(0);
            }
        }
    }

    public void enemyCollision(Hero hero){
        Rectangle heroBounds = hero.getBounds();
        for(Enemy enemy:enemies){
            if(heroBounds.intersects(enemy.getBounds().getBoundsInParent())){
                Long time = System.currentTimeMillis();
                if(time > lastTouch + 1000) {
                    hero.setRemainingLives(hero.getRemainingLives() - 1);
                    lastTouch = time;
                }
            }
        }
    }

    public void heroBulletsCollision(Hero hero){
        ArrayList<MovingObjects> toBeRemoved = new ArrayList<>();
        ArrayList<StaticObjects> toRemoveBlocks = new ArrayList<>();
        for(Bullet bullet : heroBullets){
            Rectangle bulletBounds = bullet.getBounds();
            for(Block block : blocks) {
                Rectangle blockBounds = block.getBounds();
                if (bulletBounds.intersects(blockBounds.getBoundsInParent())){
                    toBeRemoved.add(bullet);
                    if(block.isBreakable()){
                        ((SoftBlock) block).setHp(((SoftBlock)block).getHp()-1);
                        ((SoftBlock) block).changeImage();
                        if(((SoftBlock) block).getHp() <= 0 || bullet.getDmg() > 50){
                            toRemoveBlocks.add(block);
                        }
                    }
                }
            }
            for (Enemy enemy : enemies){
                Rectangle enemyBounds = enemy.getBounds();
                if(bulletBounds.intersects(enemyBounds.getBoundsInParent())){
                    toBeRemoved.add(bullet);
                    enemy.setRemainingHealth(enemy.getRemainingHealth()-bullet.getDmg());
                    if (enemy.getRemainingHealth() <= 0){
                        toBeRemoved.add(enemy);
                    }
                }
            }
        }
        removeMovingObjects(toBeRemoved);
        removeStaticObjects(toRemoveBlocks);
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

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }
}
