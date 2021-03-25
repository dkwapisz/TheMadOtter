package model.hero;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import map.Door;
import map.MapGenerator;
import map.Room;
import model.Bullet;
import model.MovingObjects;
import model.item.guns.*;

import java.util.ArrayList;
import java.util.Random;

public class Hero extends MovingObjects {

    private boolean shooting;
    private int remainingLives;
    private HeroActions currentAction;
    private Room actualRoom;
    private MapGenerator map;
    private long lastShot = 0;
    private long cooldownShot;
    private long lastChange = 0;
    private long lastEnemyTouch = 0;
    private ArrayList<Gun> equipment = new ArrayList<>();
    private Gun actualGun;
    private final Random random = new Random();

    public Hero(double x, double y, Pane layer) {
        super(x, y, "/graphics/hero/otterStatic.gif", "/graphics/hero/otterMoving.gif", "graphics/hero/otterStaticShoting.gif", "graphics/hero/otterMovingShoting.gif",  layer);
        map = new MapGenerator(5, layer);  //nrOfRooms musi być nieparzyste (!!!)
        actualRoom = map.getRoomList().get((map.getNrOfRooms()*map.getNrOfRooms()-1)/2);
        currentAction = HeroActions.UP;
        actualRoom.makeHeroBulletList();
        equipment.add(new Pistol(layer));
        actualGun = equipment.get(0);
        cooldownShot = actualGun.getCooldownShot();
        remainingLives = 20;
        getImageView().toFront();
    }

    public void updateHero() {
        setShootingStatus(shooting);
        actualRoom.checkCollision(this);
        updateBullets();
        updateLocation();
    }

    public void shot(double velX, double velY) {
        double x = 0;
        double y = 0;

        cooldownShot = actualGun.getCooldownShot();

        if(actualGun.getAmmo() == 0) {
            setNoAmmo(true);
        } else {
            setNoAmmo(false);
        }

        if(currentAction == HeroActions.SHOTUP) {
            getImageView().setViewport(getFrame().get(1));
            x = getX() + 32;
            y = getY() + 25;
        }
        else if (currentAction == HeroActions.SHOTDOWN) {
            getImageView().setViewport(getFrame().get(0));
            x = getX() + 27;
            y = getY() + 28;
        }
        else if (currentAction == HeroActions.SHOTLEFT) {
            getImageView().setViewport(getFrame().get(3));
            x = getX() + 2;
            y = getY() + 20;
        }
        else if (currentAction == HeroActions.SHOTRIGHT) {
            getImageView().setViewport(getFrame().get(2));
            x = getX() + 61;
            y = getY() + 20;
        }

        long time = System.currentTimeMillis();

        if (time > lastShot + cooldownShot && (actualGun.getAmmo() != 0 || actualGun instanceof Pistol)) {
            lastShot = time;
            if(actualGun instanceof Shotgun) {
                double newVelX = 0;
                double newVelY = 0;
                for(double i = -0.1; i<0.1;) {
                    if(velX != 0) {
                        newVelY = i + (random.nextDouble()-0.5)/5;
                        newVelX = velX + (random.nextDouble()-0.5)/10;
                        i = i + 0.02;
                    }
                    if(velY != 0) {
                        newVelY = velY + (random.nextDouble()-0.5)/10;
                        newVelX = i + (random.nextDouble()-0.5)/5;
                        i = i + 0.02;
                    }
                    actualRoom.getHeroBullets().add(new Bullet(x, y, newVelX*actualGun.getBulletVel(), newVelY*actualGun.getBulletVel(), actualGun.getDmg(), actualGun.getPathBullet(), actualGun.getPathBullet(), getLayer()));
                }
            }
            else if (actualGun instanceof M16){
                for(double i=1; i<1.3; i = i+0.1) {
                    actualRoom.getHeroBullets().add(new Bullet(x, y, i*velX*actualGun.getBulletVel(), i*velY*actualGun.getBulletVel(), actualGun.getDmg(), actualGun.getPathBullet(), actualGun.getPathBullet(), getLayer()));
                }
                actualGun.setAmmo(actualGun.getAmmo()-2);
            }
            else {
                actualRoom.getHeroBullets().add(new Bullet(x, y, velX*actualGun.getBulletVel(), velY*actualGun.getBulletVel(), actualGun.getDmg(), actualGun.getPathBullet(), actualGun.getPathBullet(), getLayer()));
            }
            actualGun.setAmmo(actualGun.getAmmo()-1);
        }
    }

    public void move() {
        int vel = 5;
        doorCollision();
        if(currentAction == HeroActions.UP) {
            setVelY(-vel);
        }
        if(currentAction == HeroActions.DOWN) {
            setVelY(vel);
        }
        if(currentAction == HeroActions.RIGHT) {
            setVelX(vel);
        }
        if(currentAction == HeroActions.LEFT) {
            setVelX(-vel);
        }
    }

    private void updateBullets() {
        ArrayList<MovingObjects> toBeRemoved = new ArrayList<>();
        if(actualRoom.getHeroBullets() != null) {
            for(Bullet bullet : actualRoom.getHeroBullets()) {
                bullet.updateLocation();
                if (bullet.removeBullets()) {
                    toBeRemoved.add(bullet);
                }
            }
            actualRoom.removeMovingObjects(toBeRemoved);
        }
        if(actualRoom.getEnemyBullets() != null) {
            for(Bullet bullet : actualRoom.getEnemyBullets()) {
                bullet.updateLocation();
                if (bullet.removeBullets()) {
                    toBeRemoved.add(bullet);
                }
            }
            actualRoom.removeMovingObjects(toBeRemoved);
        }
    }

    public void doorCollision() {
        Rectangle heroBounds = getBounds();
        ArrayList<Door> doors = actualRoom.getDoor();
        for (Door door : doors) {
            Rectangle doorBounds = door.getBounds();
            if (heroBounds.intersects(doorBounds.getBoundsInParent())) {
                if (door.getDoorId() == 1 && !door.isClosedDoors()) {
                    goToNextRoom(map.getRoomList().get(actualRoom.getRoomId() - 1), actualRoom);
                    actualRoom = map.getRoomList().get(actualRoom.getRoomId() - 1);
                    setX(360);
                    setY(700);
                }
                if (door.getDoorId() == 2 && !door.isClosedDoors()) {
                    goToNextRoom(map.getRoomList().get(actualRoom.getRoomId() - map.getNrOfRooms()), actualRoom);
                    actualRoom = map.getRoomList().get(actualRoom.getRoomId() - map.getNrOfRooms());
                    setX(700);
                    setY(360);
                }
                if (door.getDoorId() == 3 && !door.isClosedDoors()) {
                    goToNextRoom(map.getRoomList().get(actualRoom.getRoomId() + 1), actualRoom);
                    actualRoom = map.getRoomList().get(actualRoom.getRoomId() + 1);
                    setX(360);
                    setY(40);
                }
                if (door.getDoorId() == 4 && !door.isClosedDoors()) {
                    goToNextRoom(map.getRoomList().get(actualRoom.getRoomId() + map.getNrOfRooms()), actualRoom);
                    actualRoom = map.getRoomList().get(actualRoom.getRoomId() + map.getNrOfRooms());
                    setX(40);
                    setY(360);
                }
            }
        }
    }


    private void goToNextRoom(Room nextRoom, Room actualRoom) {
        ArrayList<Integer> tempNextRemove = new ArrayList<>();
        ArrayList<Integer> tempNextAdd = new ArrayList<>();
        for (int i = 0; i < nextRoom.getDoor().size(); i++) {
            tempNextRemove.add(nextRoom.getDoor().get(i).getDoorId());
        }
        if (nextRoom.getDoor().size() < actualRoom.getDoor().size()) {
            if (!tempNextRemove.contains(1)) {
                map.getDoor1().removeFromLayer();
                map.getDoor1().setClosedDoors(true);
            }
            if (!tempNextRemove.contains(2)) {
                map.getDoor2().removeFromLayer();
                map.getDoor2().setClosedDoors(true);
            }
            if (!tempNextRemove.contains(3)) {
                map.getDoor3().removeFromLayer();
                map.getDoor3().setClosedDoors(true);
            }
            if (!tempNextRemove.contains(4)) {
                map.getDoor4().removeFromLayer();
                map.getDoor4().setClosedDoors(true);
            }
        } else {
            for (int i = 0; i < actualRoom.getDoor().size(); i++) {
                tempNextAdd.add(actualRoom.getDoor().get(i).getDoorId());
            }
            if (tempNextRemove.contains(1) && !tempNextAdd.contains(1)) {
                map.getDoor1().addToLayer();
                map.getDoor1().setClosedDoors(false);
            }
            if (tempNextRemove.contains(2) && !tempNextAdd.contains(2)) {
                map.getDoor2().addToLayer();
                map.getDoor2().setClosedDoors(false);
            }
            if (tempNextRemove.contains(3) && !tempNextAdd.contains(3)) {
                map.getDoor3().addToLayer();
                map.getDoor3().setClosedDoors(false);
            }
            if (tempNextRemove.contains(4) && !tempNextAdd.contains(4)) {
                map.getDoor4().addToLayer();
                map.getDoor4().setClosedDoors(false);
            }
        }
        actualRoom.eraseEnemies();
        actualRoom.eraseItems();
        actualRoom.eraseBlocks();
        actualRoom.eraseBullets();

        nextRoom.drawItems();
        nextRoom.drawEnemies();
        nextRoom.drawBlocks();
        nextRoom.makeHeroBulletList();
        nextRoom.makeEnemyBulletList();
        if (!nextRoom.getEnemies().isEmpty()){
            for (Door door : nextRoom.getDoor()) {
                door.removeFromLayer();
                door.setClosedDoors(true);
            }
        }
    }

    public void addNewGun(Gun gun) {
        boolean isOwned = false;
        for(Gun ownedGun : equipment) {
            if(ownedGun.getClass().equals(gun.getClass())) {
                isOwned = true;
                ownedGun.addAmmo();
            }
        }
        if(!isOwned) {
            equipment.add(gun);
            actualGun = gun;
        }
    }

    public void changeWeapon(boolean forward) {
        int length = equipment.size();
        if(length == 1) {
            return;
        }
        int selectedGun = equipment.indexOf(actualGun);
        long time = System.currentTimeMillis();
        if (time > lastChange + 500) {
            lastChange = time;
            if(forward) {
                if (selectedGun + 1 == length) {
                    actualGun = equipment.get(0);
                } else {
                    actualGun = equipment.get(equipment.indexOf(actualGun) + 1);
                }
            } else {
                if (selectedGun - 1 == -1) {
                    actualGun = equipment.get(length-1);
                } else {
                    actualGun = equipment.get(equipment.indexOf(actualGun) - 1);
                }
            }
        }
    }

    public void healthDown(int minus) {
        long time = System.currentTimeMillis();
        if(time > lastEnemyTouch + 2000) {
            setRemainingLives(getRemainingLives() - minus);
            lastEnemyTouch = time;
        }
    }

    public int getRemainingLives() {
        return remainingLives;
    }
    public void setRemainingLives(int remainingLives) {
        this.remainingLives = remainingLives;
    }

    public HeroActions getCurrentAction() {
        return currentAction;
    }
    public void setCurrentAction(HeroActions currentAction) {
        this.currentAction = currentAction;
    }

    public Room getActualRoom() {
        return actualRoom;
    }
    public void setActualRoom(Room actualRoom) {
        this.actualRoom = actualRoom;
    }

    public MapGenerator getMap() {
        return map;
    }
    public void setMap(MapGenerator map) {
        this.map = map;
    }

    public boolean isShooting() {
        return shooting;
    }
    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    public Gun getActualGun() {
        return actualGun;
    }
    public void setActualGun(Gun actualGun) {
        this.actualGun = actualGun;
    }
}
