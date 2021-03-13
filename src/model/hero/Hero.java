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
        int vel = 7;
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
//                    checkNumberOfDoors(map.getRoomList().get(actualRoom.getRoomId() - 1));
                    actualRoom = map.getRoomList().get(actualRoom.getRoomId() - 1);
                    setX(350);
                    setY(350);
                }
                if (door.getDoorId() == 2 && !door.isClosedDoors()) {
//                    checkNumberOfDoors(map.getRoomList().get(actualRoom.getRoomId() - 5));
                    actualRoom = map.getRoomList().get(actualRoom.getRoomId() - 5);
                    setX(350);
                    setY(350);
                }
                if (door.getDoorId() == 3 && !door.isClosedDoors()) {
                    actualRoom = map.getRoomList().get(actualRoom.getRoomId() + 1);
                    setX(350);
                    setY(350);
                }
                if (door.getDoorId() == 4 && !door.isClosedDoors()) {
                    actualRoom = map.getRoomList().get(actualRoom.getRoomId() + 5);
                    setX(350);
                    setY(350);
                }
            }
        }
    }


//    private void checkNumberOfDoors(Room nextRoom) {
//
//        if (nextRoom.getDoor().size() != 4) {
//            if (nextRoom.getDoor().size() == 3) {
//                for(int i=0; i<3; i++) {
//                    //System.out.println(nextRoom.getDoor().get(i).getDoorId());
//                    if (nextRoom.getDoor().get(i).getDoorId() == 1) {
//                        map.getDoor1().removeFromLayer();
//                        map.getDoor1().setClosedDoors(true);
//                    }
//                    else if (nextRoom.getDoor().get(i).getDoorId() == 2) {
//                        map.getDoor2().removeFromLayer();
//                        map.getDoor2().setClosedDoors(true);
//                    }
//                }
//
//
//            } else if (nextRoom.getDoor().size() == 2) {
//
//
//            }
//        }
//    }



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
