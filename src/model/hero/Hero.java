package model.hero;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import map.Door;
import map.MapGenerator;
import map.Room;
import model.ObjectsBehaviour;

import java.awt.*;
import java.util.ArrayList;

public class Hero extends ObjectsBehaviour {

    private int remainingLives;
    private HeroDirections currentDirection;
    private Room actualRoom;
    private MapGenerator map;


    public Hero(double x, double y, Pane layer) {
        super(x, y, "/graphics/hero/heroStatic.png","/graphics/hero/heroMove.gif", layer);
        map = new MapGenerator(5, layer);
        actualRoom = map.getRoomList().get(12);
        currentDirection = HeroDirections.UP;
    }


    public void move() {
        int vel = 5;
        doorCollision();
        if(currentDirection == HeroDirections.UP) {
            setVelY(-vel);
        }
        if(currentDirection == HeroDirections.DOWN) {
            setVelY(vel);
        }
        if(currentDirection == HeroDirections.RIGHT) {
            setVelX(vel);
        }
        if(currentDirection == HeroDirections.LEFT) {
            setVelX(-vel);
        }


    }

    public void doorCollision() {
        Rectangle heroBounds = getBounds();
        ArrayList<Door> doors = actualRoom.getDoor();
        for (Door door : doors) {
            Rectangle doorBounds = door.getBounds();
            if (heroBounds.intersects(doorBounds.getBoundsInParent())) {
                if (door.getDoorId() == 1 && !door.isClosedDoors()) {
                    checkNumberOfDoors(map.getRoomList().get(actualRoom.getRoomId() - 1), actualRoom);
                    actualRoom = map.getRoomList().get(actualRoom.getRoomId() - 1);
                    setX(360);
                    setY(700);
                }
                if (door.getDoorId() == 2 && !door.isClosedDoors()) {
                    checkNumberOfDoors(map.getRoomList().get(actualRoom.getRoomId() - 5), actualRoom);
                    actualRoom = map.getRoomList().get(actualRoom.getRoomId() - 5);
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
                    checkNumberOfDoors(map.getRoomList().get(actualRoom.getRoomId() + 5), actualRoom);
                    actualRoom = map.getRoomList().get(actualRoom.getRoomId() + 5);
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
    }



//        Rectangle door1Bounds = map.getDoor1().getBounds();
//        Rectangle door2Bounds = map.getDoor2().getBounds();
//        Rectangle door3Bounds = map.getDoor3().getBounds();
//        Rectangle door4Bounds = map.getDoor4().getBounds();
//        if(heroBounds.intersects(door1Bounds.getBoundsInParent())) {
//            actualRoom = map.getRoomList().get(actualRoom.getRoomId()-1);
//            setX(360);
//            setY(700);
//        }
//    }

    public boolean checkWhichRoom(int room1) {
        return room1 == actualRoom.getRoomId();
    }


    public int getRemainingLives() {
        return remainingLives;
    }
    public void setRemainingLives(int remainingLives) {
        this.remainingLives = remainingLives;
    }

    public HeroDirections getCurrentDirection() {
        return currentDirection;
    }
    public void setCurrentDirection(HeroDirections currentDirection) {
        this.currentDirection = currentDirection;
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
}
