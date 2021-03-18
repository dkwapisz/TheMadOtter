package model.hero;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import map.Door;
import map.MapGenerator;
import map.Room;
import model.Bullet;
import model.MovingObjects;
import model.item.Item;

import java.util.ArrayList;

public class Hero extends MovingObjects {

    private boolean shooting;
    private int remainingLives;
    private HeroActions currentAction;
    private Room actualRoom;
    private MapGenerator map;
    private long lastShot = 0;
    private long cooldownShot = 500;
    private ArrayList<Item> equipment = new ArrayList<>();
    private Item actualGun;

    public Hero(double x, double y, Pane layer) {
        super(x, y, "/graphics/hero/otterStaticGIF.gif","/graphics/hero/otterMovingGIF.gif", layer);
        map = new MapGenerator(5, layer);  //nrOfRooms musi być nieparzyste (!!!)
        actualRoom = map.getRoomList().get((map.getNrOfRooms()*map.getNrOfRooms()-1)/2);
        currentAction = HeroActions.UP;
        actualRoom.makeHeroBulletList();
    }

    public void updateHero() {
        updateLocation();
        setShootingStatus(shooting);
        actualRoom.itemCollision(this);
        System.out.println(currentAction);
        ArrayList<Bullet> toBeRemoved = new ArrayList<>();
        if(actualRoom.getHeroBullets() != null) {
            for(Bullet bullet : actualRoom.getHeroBullets()) {
                bullet.updateLocation();
                if (bullet.removeBullets()) {
                    toBeRemoved.add(bullet);
                }
            }
            actualRoom.removeBullets(toBeRemoved);
        }
    }

    public void shot(int velX, int velY) {
        if(currentAction == HeroActions.SHOTUP) {
            getImageView().setViewport(getFrame().get(1));
        }
        else if (currentAction == HeroActions.SHOTDOWN) {
            getImageView().setViewport(getFrame().get(0));
        }
        else if (currentAction == HeroActions.SHOTLEFT) {
            getImageView().setViewport(getFrame().get(3));
        }
        else if (currentAction == HeroActions.SHOTRIGHT) {
            getImageView().setViewport(getFrame().get(2));
        }
        long time = System.currentTimeMillis();
        if (time > lastShot + cooldownShot) {
            lastShot = time;
            actualRoom.getHeroBullets().add(new Bullet(getX()+32, getY()+32, velX, velY, "graphics/bullet.png", "graphics/bullet.png", getLayer()));
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


    public void doorCollision() {
        Rectangle heroBounds = getBounds();
        ArrayList<Door> doors = actualRoom.getDoor();
        for (Door door : doors) {
            Rectangle doorBounds = door.getBounds();
            if (heroBounds.intersects(doorBounds.getBoundsInParent())) {
                actualRoom.eraseBullets();
                if (door.getDoorId() == 1 && !door.isClosedDoors()) {
                    checkNumberOfDoors(map.getRoomList().get(actualRoom.getRoomId() - 1), actualRoom);
                    actualRoom = map.getRoomList().get(actualRoom.getRoomId() - 1);
                    setX(360);
                    setY(700);
                }
                if (door.getDoorId() == 2 && !door.isClosedDoors()) {
                    checkNumberOfDoors(map.getRoomList().get(actualRoom.getRoomId() - map.getNrOfRooms()), actualRoom);
                    actualRoom = map.getRoomList().get(actualRoom.getRoomId() - map.getNrOfRooms());
                    setX(700);
                    setY(360);
                }
                if (door.getDoorId() == 3 && !door.isClosedDoors()) {
                    checkNumberOfDoors(map.getRoomList().get(actualRoom.getRoomId() + 1), actualRoom);
                    actualRoom = map.getRoomList().get(actualRoom.getRoomId() + 1);
                    setX(360);
                    setY(40);
                }
                if (door.getDoorId() == 4 && !door.isClosedDoors()) {
                    checkNumberOfDoors(map.getRoomList().get(actualRoom.getRoomId() + map.getNrOfRooms()), actualRoom);
                    actualRoom = map.getRoomList().get(actualRoom.getRoomId() + map.getNrOfRooms());
                    setX(40);
                    setY(360);
                }
            }
        }
    }


    private void checkNumberOfDoors(Room nextRoom, Room actualRoom) {
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

        nextRoom.drawItems();
        nextRoom.drawEnemies();
        nextRoom.makeHeroBulletList();
        if (!nextRoom.getEnemies().isEmpty()){
            for (Door door : nextRoom.getDoor()) {
                door.removeFromLayer();
                door.setClosedDoors(true);
            }
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
}
