package map;

import javafx.scene.shape.Rectangle;

import model.*;
import model.block.Barrel;
import model.block.Block;
import model.block.Box;
import model.block.SoftBlock;
import model.enemy.*;
import model.hero.Hero;
import model.item.Sign;
import model.item.Item;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


public class Room {

    private ArrayList<Door> door;
    private boolean clean;
    private boolean shop;
    private int roomId;
    private FloorGenerator actualFloor;
    private ArrayList<Enemy> enemies;
    private ArrayList<Item> items;
    private ArrayList<Bullet> heroBullets;
    private ArrayList<Bullet> enemyBullets;
    private ArrayList<Block> blocks;
    private ArrayList<Explosion> explosions = new ArrayList<>();
    private ArrayList<BombFired> puttedBombs = new ArrayList<>();
    private final Random random = new Random();

    public Room(ArrayList<Door> door, int roomId, ArrayList<Enemy> enemies, ArrayList<Item> items, ArrayList<Block> blocks, FloorGenerator actualFloor) {
        this.enemies = Objects.requireNonNullElseGet(enemies, ArrayList::new);
        this.blocks = Objects.requireNonNullElseGet(blocks, ArrayList::new);
        this.items = Objects.requireNonNullElseGet(items, ArrayList::new);
        this.door = door;
        this.roomId = roomId;
        this.actualFloor = actualFloor;
    }

    public void checkCollision(Hero hero){
        blockCollision(hero);
        enemyCollision(hero);
        itemCollision(hero);
        heroBulletsCollision();
        enemyBulletCollision(hero);
        explosionCollision(hero);
        enemyFollow(hero);
        updateEnemy(hero);
        openTrapDoor();
    }

    private void openTrapDoor() {
        boolean isAllClear = true;
        for (Room room : actualFloor.getRoomList()) {
            if (!room.isClean()) {
                isAllClear = false;
                break;
            }
        }
        if (isAllClear) {
            actualFloor.getTrapdoor().open();
        }
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
            item.getImageView().toBack();
        }
    }

    public void eraseItems(){
        for (Item item : items) {
            item.removeFromLayer();
            if (item instanceof Sign) {
                ((Sign) item).hideText();
            }
        }
    }

    public void eraseExplosions() {
        if (explosions.size() != 0) {
            for (Explosion explosion : explosions) {
                explosion.removeFromLayer();
            }
            explosions.clear();
        }
    }

    public void erasePuttedBombs() {
        if (puttedBombs.size() != 0) {
            for (BombFired bombFired : puttedBombs) {
                bombFired.removeFromLayer();
            }
            puttedBombs.clear();
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
                    if(((Bullet) object).getDmg() == 60) {
                        explosions.add(new Explosion(object.getX() - object.getDimension().getWidth()/2, object.getY() - object.getDimension().getHeight()/2, object.getLayer(), this));
                    }
                    heroBullets.remove(object);
                } else if (enemyBullets != null){
                    enemyBullets.remove(object);
                }
            }if (object instanceof Enemy){
                if (((Enemy) object).isExplosive()) {
                    explosions.add(new Explosion(object.getX(), object.getY(), object.getLayer(), this));
                }
                Item randomItem = RNG.getRandomItem(object.getX(), object.getY(), 0.05, object.getLayer());
                if (randomItem != null) {
                    items.add(randomItem);
                }
                enemies.remove(object);
                if(enemies.size() == 0) {
                    clean = true;
                }
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
                if (object instanceof Barrel) {
                    explosions.add(new Explosion(object.getX() - object.getBounds().getWidth()/2, object.getY() - object.getBounds().getHeight()/2, object.getLayer(), this));
                }
                else if (object instanceof Box) {
                    Item randomItem = RNG.getRandomItem(object.getX(), object.getY(), 1, object.getLayer());
                    if (randomItem != null) {
                        items.add(randomItem);
                    }
                }
                else if (object instanceof SoftBlock) {
                    Item randomItem = RNG.getRandomItem(object.getX(), object.getY(), 0.1, object.getLayer());
                    if (randomItem != null) {
                        items.add(randomItem);
                    }
                }
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
        Rectangle heroBounds = hero.getSmallerBounds();
        for(Item item : items) {
            Rectangle itemBounds = item.getBounds();
            if (item instanceof Sign) {
                if (hero.getBounds().intersects(itemBounds.getBoundsInParent())) {
                    ((Sign) item).showText();
                } else {
                    ((Sign) item).hideText();
                }
            }
            else if (heroBounds.intersects(itemBounds.getBoundsInParent())) {
                if (item.onTouch(hero)) {
                    toBeRemoved.add(item);
                }
            }
        }
        removeStaticObjects(toBeRemoved);
    }

    public void explosionCollision(Hero hero) {
        if (explosions.size() != 0) {
            for(Explosion explosion : explosions) {
                if(hero.getSmallerBounds().intersects(explosion.getBounds().getBoundsInParent())) {
                    hero.healthDown(2);
                }
                for(Enemy enemy : enemies) {
                    if(enemy.getBounds().intersects(explosion.getBounds().getBoundsInParent())) {
                        enemy.setRemainingHealth(Math.max(enemy.getRemainingHealth() - 30, 0));
                    }
                }
            }
        }
    }

    public void blockCollision(Hero hero) {
        ArrayList<StaticObjects> toBeRemoved = new ArrayList<>();
        Rectangle heroBounds = hero.getSmallerBounds();
        for (Block block : blocks) {
            Rectangle blockBounds = block.getBounds();
            if (heroBounds.intersects(blockBounds.getBoundsInParent())) {
                if(!block.isToPass()) {
                    hero.setVelX(0);
                    hero.setVelY(0);
                }
                if (block.isPrickly()){
                    hero.healthDown(2);
                }
            }
            for (Explosion explosion : explosions) {
                if (blockBounds.intersects(explosion.getBounds().getBoundsInParent()) && !block.isToPass()) {
                    toBeRemoved.add(block);
                }
            }
        }
        removeStaticObjects(toBeRemoved);
    }

    public void enemyCollision(Hero hero){
        ArrayList<MovingObjects> toBeRemoved = new ArrayList<>();
        Rectangle heroBounds = hero.getSmallerBounds();
        for (Enemy enemy : enemies) {
            if (heroBounds.intersects(enemy.getBounds().getBoundsInParent())) {
                hero.healthDown(enemy.getDmg());
                if(enemy instanceof Boomer) {
                    enemy.setRemainingHealth(0);
                    toBeRemoved.add(enemy);
                }
            }
            if (!enemy.isFlying() && !enemy.isFollowing()) {
                for (Block block : blocks) {
                    if(enemy.getBounds().intersects(block.getBounds().getBoundsInParent()) && !block.isToPass()) {
                        enemy.setVelX(-enemy.getVelX());
                        enemy.setVelY(-enemy.getVelY());
                    }
                }
            }
        }
        removeMovingObjects(toBeRemoved);
    }

    public void enemyBulletCollision(Hero hero) {
        if(enemyBullets == null) {
            return;
        }
        ArrayList<MovingObjects> toBeRemoved = new ArrayList<>();
        for(Bullet bullet : enemyBullets) {
            Rectangle bulletBounds = bullet.getBounds();
            Rectangle heroBounds = hero.getSmallerBounds();
            if(bulletBounds.intersects(heroBounds.getBoundsInParent())) {
                toBeRemoved.add(bullet);
                hero.healthDown(bullet.getDmg());
            }
            for(Block block : blocks) {
                Rectangle blockBounds = block.getBounds();
                if(bulletBounds.intersects(blockBounds.getBoundsInParent()) && !block.isToPass()) {
                    toBeRemoved.add(bullet);
                }
            }
        }
        removeMovingObjects(toBeRemoved);
    }

    public void heroBulletsCollision(){
        ArrayList<MovingObjects> toBeRemoved = new ArrayList<>();
        ArrayList<StaticObjects> toRemoveBlocks = new ArrayList<>();
        ArrayList<Slime> slimes = new ArrayList<>();
        for(Bullet bullet : heroBullets){
            bullet.changeLayer();
            Rectangle bulletBounds = bullet.getBounds();
            for(Block block : blocks) {
                Rectangle blockBounds = block.getBounds();
                if (bulletBounds.intersects(blockBounds.getBoundsInParent()) && !block.isToPass()){
                    toBeRemoved.add(bullet);
                    if (block.isBreakable()){
                        block.setHp(block.getHp() - 1);
                        block.changeImage();
                        if (block.getHp() <= 0 || bullet.getDmg() > 50){
                            toRemoveBlocks.add(block);
                        }
                    }
                }
            }
            for (Enemy enemy : enemies){
                Rectangle enemyBounds = enemy.getBounds();
                if (bulletBounds.intersects(enemyBounds.getBoundsInParent())){
                    toBeRemoved.add(bullet);
                    enemy.setRemainingHealth(enemy.getRemainingHealth() - bullet.getDmg());
                    if (enemy.getRemainingHealth() <= 0){
                        toBeRemoved.add(enemy);
                        if(enemy instanceof Slime){
                            slimes.add(((Slime) enemy));
                        }
                    }
                }
            }
        }
        if(!slimes.isEmpty()){
            for (Slime slime: slimes) {
                if(slime.isSlimeKing()){
                    enemies.addAll(slime.createMedium(slime.getX(), slime.getY()));
                }else if(slime.isMedium()){
                    enemies.addAll(slime.createSmall(slime.getX(), slime.getY()));
                }
            }
        }
        removeMovingObjects(toBeRemoved);
        removeStaticObjects(toRemoveBlocks);
    }

    public void enemyFollow(Hero hero) {
        double vecLength;
        double randomize;
        double newVelX;
        double newVelY;
        for (Enemy enemy : enemies) {
            vecLength = Math.hypot(hero.getX() - enemy.getX(), hero.getY() - enemy.getY());
            newVelX = enemy.getFollowingVel()*(hero.getX() - enemy.getX())/vecLength;
            newVelY = enemy.getFollowingVel()*(hero.getY() - enemy.getY())/vecLength;
            if (enemy.isFollowing()) {
                for (Block block : blocks) {
                    enemy.setVelX(enemy.getFollowingVel()*(hero.getX() - enemy.getX())/vecLength);
                    enemy.setVelY(enemy.getFollowingVel()*(hero.getY() - enemy.getY())/vecLength);
                    Rectangle enemyBounds = enemy.getBounds();
                    Rectangle blockBounds = block.getBounds();
                    if(enemyBounds.intersects(blockBounds.getBoundsInParent()) && !enemy.isFlying() && !block.isToPass()) {
                        if(enemy.getUpBounds().intersects(block.getDownBounds().getBoundsInParent()) || enemy.getDownBounds().intersects(block.getUpBounds().getBoundsInParent())) {
                            newVelY = 0;
                        }
                        else if(enemy.getLeftBounds().intersects(block.getRightBounds().getBoundsInParent()) || enemy.getRightBounds().intersects(block.getLeftBounds().getBoundsInParent())) {
                            newVelX = 0;
                        }
                    } else {
                        if((Math.abs(hero.getX() - enemy.getX()) < 2 && Math.abs(hero.getY() - enemy.getY()) < 2)) {
                            newVelX = 0;
                            newVelY = 0;
                        }
                    }
                    randomize = (0.75+random.nextDouble());
                    enemy.setVelX(randomize*newVelX);
                    enemy.setVelY(randomize*newVelY);
                }
            }
        }
    }

    public void updateEnemy(Hero hero){
        if (!enemies.isEmpty()) {
            for (Enemy enemy : enemies) {
                enemy.specificMovement();
                if (enemy.getX() + enemy.getVelX() < 30 || enemy.getX() + enemy.getVelX() > 770 - enemy.getImageStatic().getHeight()/4){
                    enemy.setVelX(-enemy.getVelX());
                }
                if (enemy.getY() + enemy.getVelY() < 30 || enemy.getY() + enemy.getVelY() > 770 - enemy.getImageStatic().getHeight()/4){
                    enemy.setVelY(-enemy.getVelY());
                }
                enemy.updateLocation();
                if(enemy.isShooting()) {
                    enemy.shot(hero, enemy.getBulletVelFactor());
                    if (enemy instanceof Turret) {
                        if (enemy.getBulletVelX() < 0) {
                            enemy.getImageView().setRotate(Math.toDegrees(-(Math.PI - (Math.atan(enemy.getBulletVelY() / enemy.getBulletVelX()) + Math.PI / 2))));
                        } else {
                            enemy.getImageView().setRotate(Math.toDegrees(Math.atan(enemy.getBulletVelY() / enemy.getBulletVelX()) + Math.PI / 2));
                        }
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

    public ArrayList<Explosion> getExplosions() {
        return explosions;
    }
    public void setExplosions(ArrayList<Explosion> explosions) {
        this.explosions = explosions;
    }

    public FloorGenerator getActualFloor() {
        return actualFloor;
    }
    public void setActualFloor(FloorGenerator actualFloor) {
        this.actualFloor = actualFloor;
    }

    public boolean isShop() {
        return shop;
    }
    public void setShop(boolean shop) {
        this.shop = shop;
    }

    public ArrayList<BombFired> getPuttedBombs() {
        return puttedBombs;
    }
    public void setPuttedBombs(ArrayList<BombFired> puttedBombs) {
        this.puttedBombs = puttedBombs;
    }
}
