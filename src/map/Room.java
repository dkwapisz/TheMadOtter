package map;

import javafx.scene.shape.Rectangle;
import model.Bullet;
import model.MovingObjects;
import model.StaticObjects;
import model.block.Block;
import model.block.SoftBlock;
import model.enemy.Enemy;
import model.enemy.Turret;
import model.hero.Hero;
import model.item.Fish;
import model.item.guns.Gun;
import model.item.Item;
import java.lang.Math;
import java.util.ArrayList;


public class Room {

    private ArrayList<Door> door;
    private boolean clean;
    private int roomId;
    private ArrayList<Enemy> enemies;
    private ArrayList<Item> items;
    private ArrayList<Bullet> heroBullets;
    private ArrayList<Bullet> enemyBullets;
    private ArrayList<Block> blocks;



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
        heroBulletsCollision();
        enemyBulletCollision(hero);
        enemyFollow(hero);
        updateEnemy(hero);
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
                if(heroBullets.contains(object)) {
                    heroBullets.remove(object);
                } else {
                    enemyBullets.remove(object);
                }
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
        if(enemyBullets == null) {
            return;
        }
        for (Bullet bullet : enemyBullets) {
            bullet.removeFromLayer();
        }
        enemyBullets.clear();
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
        for(Enemy enemy : enemies){
            if(heroBounds.intersects(enemy.getBounds().getBoundsInParent())){
                hero.healthDown(enemy.getDmg());
            }
        }
    }

    public void enemyBulletCollision(Hero hero) {
        if(enemyBullets == null) {
            return;
        }
        ArrayList<MovingObjects> toBeRemoved = new ArrayList<>();
        for(Bullet bullet : enemyBullets) {
            Rectangle bulletBounds = bullet.getBounds();
            Rectangle heroBounds = hero.getBounds();
            if(bulletBounds.intersects(heroBounds.getBoundsInParent())) {
                toBeRemoved.add(bullet);
                hero.healthDown(bullet.getDmg());
            }
            for(Block block : blocks) {
                Rectangle blockBounds = block.getBounds();
                if(bulletBounds.intersects(blockBounds.getBoundsInParent())) {
                    toBeRemoved.add(bullet);
                }
            }
        }
        removeMovingObjects(toBeRemoved);
    }


    public void heroBulletsCollision(){
        ArrayList<MovingObjects> toBeRemoved = new ArrayList<>();
        ArrayList<StaticObjects> toRemoveBlocks = new ArrayList<>();
        for(Bullet bullet : heroBullets){
            bullet.changeLayer();
            Rectangle bulletBounds = bullet.getBounds();
            for(Block block : blocks) {
                Rectangle blockBounds = block.getBounds();
                if (bulletBounds.intersects(blockBounds.getBoundsInParent())){
                    toBeRemoved.add(bullet);
                    if (block.isBreakable()){
                        ((SoftBlock) block).setHp(((SoftBlock)block).getHp()-1);
                        ((SoftBlock) block).changeImage();
                        if (((SoftBlock) block).getHp() <= 0 || bullet.getDmg() > 50){
                            toRemoveBlocks.add(block);
                        }
                    }
                }
            }
            for (Enemy enemy : enemies){
                Rectangle enemyBounds = enemy.getBounds();
                if (bulletBounds.intersects(enemyBounds.getBoundsInParent())){
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

    public void enemyFollow(Hero hero) {
        double vecLength;
        double newVelX;
        double newVelY;
        for (Enemy enemy : enemies) {
            vecLength = Math.hypot(hero.getX() - enemy.getX(), hero.getY() - enemy.getY());
            newVelX = enemy.getFollowingVel()*(hero.getX() - enemy.getX())/vecLength;
            newVelY = enemy.getFollowingVel()*(hero.getY() - enemy.getY())/vecLength;
            if (enemy.isFollowing() && !enemy.isFlying()) {
                for (Block block : blocks) {
                    enemy.setVelX(enemy.getFollowingVel()*(hero.getX() - enemy.getX())/vecLength);
                    enemy.setVelY(enemy.getFollowingVel()*(hero.getY() - enemy.getY())/vecLength);
                    Rectangle enemyBounds = enemy.getBounds();
                    Rectangle blockBounds = block.getBounds();
                    if(enemyBounds.intersects(blockBounds.getBoundsInParent())) {
                        if(enemy.getUpBounds().intersects(block.getDownBounds().getBoundsInParent()) || enemy.getDownBounds().intersects(block.getUpBounds().getBoundsInParent())) {
                            newVelY = 0;
                        }
                        else if(enemy.getLeftBounds().intersects(block.getRightBounds().getBoundsInParent()) || enemy.getRightBounds().intersects(block.getLeftBounds().getBoundsInParent())) {
                            newVelX = 0;
                        }
                    } else {
                        if((Math.abs(hero.getX() - enemy.getX()) < 2 && Math.abs(hero.getY() - enemy.getY()) < 2)) {
                            enemy.setVelX(0);
                            enemy.setVelY(0);
                        }
                    }
                    enemy.setVelX(newVelX);
                    enemy.setVelY(newVelY);
                }
            }
        }
    }

    public void updateEnemy(Hero hero){
        if (!enemies.isEmpty()) {
            for (Enemy enemy : enemies) {
                if(enemy.getX() + enemy.getVelX() < 30 || enemy.getX() + enemy.getVelX() > 770 - enemy.getImageStatic().getHeight()/4){
                    enemy.setVelX(-enemy.getVelX());
                }
                enemy.updateLocation();
                if(enemy instanceof Turret) {
                    enemy.shot(hero, 8);
                    if (enemy.getBulletVelX() < 0) {
                        enemy.getImageView().setRotate(Math.toDegrees(-(Math.PI - (Math.atan(enemy.getBulletVelY() / enemy.getBulletVelX()) + Math.PI / 2))));
                    } else {
                        enemy.getImageView().setRotate(Math.toDegrees(Math.atan(enemy.getBulletVelY() / enemy.getBulletVelX()) + Math.PI / 2));
                    }
                }
            }
        }
        openDoor();
    }

    public void makeHeroBulletList() {
        heroBullets = new ArrayList<>();
    }
    public void makeEnemyBulletList() {
        enemyBullets = new ArrayList<>();
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

    public ArrayList<Bullet> getEnemyBullets() {
        return enemyBullets;
    }
    public void setEnemyBullets(ArrayList<Bullet> enemyBullets) {
        this.enemyBullets = enemyBullets;
    }
}
