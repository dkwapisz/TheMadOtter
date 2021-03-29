package map;

import javafx.scene.layout.Pane;
import model.block.*;
import model.enemy.*;
import model.item.Fish;
import model.item.Item;
import model.item.guns.*;

import java.util.ArrayList;
import java.util.Random;

public class FloorGenerator {

    private ArrayList<Room> roomList = new ArrayList<>();
    private int nrOfRooms;  //pierwiastek z liczby pokoi
    private int nrOfEnemies = 0;
    private int floorId;
    private Door door1;
    private Door door2;
    private Door door3;
    private Door door4;
    private Trapdoor trapdoor;
    private Pane layer;


    public FloorGenerator(int nrOfRooms, Pane layer, int floorId) {
        this.nrOfRooms = nrOfRooms;
        this.layer = layer;
        this.floorId = floorId;

        door1 = new Door(360, 0, "/graphics/doorH.png",  layer, 1);         // góra
        door2 = new Door(0, 360, "/graphics/doorV.png",  layer, 2);        // lewo
        door3 = new Door(360, 768, "/graphics/doorH.png",   layer, 3);        // dół
        door4 = new Door(768, 360, "/graphics/doorV.png",   layer, 4);     // prawo
        door3.getImageView().setRotate(180);
        door4.getImageView().setRotate(180);
        trapdoor = new Trapdoor(368, 368, layer);
        generateMap();
    }

    private void generateMap() {
        int k = 0;
        for(int i=0; i < nrOfRooms; i++) {
            for(int j=0; j < nrOfRooms; j++) {
                ArrayList<Door> doors = new ArrayList<>();
                if((i == 0 || i == nrOfRooms-1) || (j == 0 || j == nrOfRooms-1)) {
                    if((i == 0 || i == nrOfRooms-1) && (j == 0 || j == nrOfRooms-1)) {
                        if(i == 0 && j == 0) {
                            doors.add(door3);
                            doors.add(door4);
                            roomList.add(new Room(doors, false, k, enemiesGenerator(k), new ArrayList<>(k), BlockGenerator(k), this));
                        }
                        else if(i == nrOfRooms-1 && j == 0) {
                            doors.add(door2);
                            doors.add(door3);
                            roomList.add(new Room(doors, false, k, enemiesGenerator(k), new ArrayList<>(k), BlockGenerator(k), this));
                        }
                        else if(i == 0 && j == nrOfRooms-1) {
                            doors.add(door1);
                            doors.add(door4);
                            roomList.add(new Room(doors, false, k, enemiesGenerator(k), new ArrayList<>(k), BlockGenerator(k), this));
                        }
                        else if(i == nrOfRooms-1 && j == nrOfRooms-1) {
                            doors.add(door1);
                            doors.add(door2);
                            roomList.add(new Room(doors, false, k, enemiesGenerator(k), new ArrayList<>(k), BlockGenerator(k), this));
                        }
                    } else {
                        if(i == 0) {
                            doors.add(door1);
                            doors.add(door3);
                            doors.add(door4);
                            roomList.add(new Room(doors, false, k, enemiesGenerator(k), new ArrayList<>(), BlockGenerator(k), this));
                        }
                        else if(i == nrOfRooms - 1) {
                            doors.add(door1);
                            doors.add(door2);
                            doors.add(door3);
                            roomList.add(new Room(doors, false, k, enemiesGenerator(k), new ArrayList<>(), BlockGenerator(k), this));
                        }
                        else if(j == 0) {
                            doors.add(door2);
                            doors.add(door3);
                            doors.add(door4);
                            roomList.add(new Room(doors, false, k, enemiesGenerator(k), new ArrayList<>(), BlockGenerator(k), this));
                        }
                        else if(j == nrOfRooms-1) {
                            doors.add(door1);
                            doors.add(door2);
                            doors.add(door4);
                            roomList.add(new Room(doors, false, k, enemiesGenerator(k), new ArrayList<>(), BlockGenerator(k), this));
                        }
                    }
                } else {
                    doors.add(door1);
                    doors.add(door2);
                    doors.add(door3);
                    doors.add(door4);
                    if(k == (nrOfRooms*nrOfRooms-1)/2) {
                        roomList.add(new Room(doors, true, k, new ArrayList<>(), itemsGenerator(k), BlockGenerator(k), this));

                    } else {
                        roomList.add(new Room(doors, false, k, enemiesGenerator(k), new ArrayList<>(), BlockGenerator(k), this));
                    }
                }
                k++;
            }
        }
    }

    private ArrayList<Item> itemsGenerator(int roomId) {
        ArrayList<Item> items = new ArrayList<>();
        if(floorId == 1) {
            items.add(new Uzi(100, 100, layer));
            items.add(new Ak47(100, 200, layer));
            items.add(new SniperRifle(100, 300, layer));
            items.add(new Deagle(100, 400, layer));
            items.add(new PlasmaGun(100, 500, layer));
            items.add(new Shotgun(100, 600, layer));
            items.add(new RocketLauncher(100, 700, layer));
        } else if (floorId == 2) {
            items.add(new M16(200, 100, layer));
            items.add(new Mp5(200, 200, layer));
            items.add(new Glock(200, 300, layer));
            items.add(new LaserGun(200, 400, layer));
            items.add(new Scar(200, 500, layer));
            items.add(new Stg44(200, 600, layer));
            items.add(new Fish(700,100, layer));
        }

        if(roomId != (nrOfRooms*nrOfRooms-1)/2) {
            for (Item item : items) {
                item.removeFromLayer();
            }
        }

        return items;
    }

    private ArrayList<Block> BlockGenerator(int roomId) {
        ArrayList<Block> blocks = new ArrayList<>();
        if(roomId == (nrOfRooms*nrOfRooms-1)/2) {
            blocks.add(trapdoor);
        }
        blocks.add(new SolidBlock(600,400, layer));
        blocks.add(new SolidBlock(600,500, layer));
        blocks.add(new SoftBlock(600,600, layer));
        blocks.add(new SoftBlock(600,700, layer));
        blocks.add(new SpikeBlock(500,700, layer));


        if(roomId != (nrOfRooms*nrOfRooms-1)/2) {
            for (Block block : blocks) {
                block.removeFromLayer();
            }
        }

        return blocks;
    }

    private ArrayList<Enemy> enemiesGenerator(int roomId){
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new Turret(500, 500, layer));
//        if(roomId == 11 || roomId == 13) {
//            enemies.add(new Turret(500, 500, layer));
//            enemies.add(new Snake(500, 300, layer));
//            enemies.add(new Wasp(100, 700, layer));
//            enemies.add(new Fly(360, 700, layer));
//            enemies.add(new Crab(360, 700, layer));
//        }

        nrOfEnemies = nrOfEnemies + enemies.size();
        return enemies;
    }


    public ArrayList<Room> getRoomList() {
        return roomList;
    }
    public void setRoomList(ArrayList<Room> roomList) {
        this.roomList = roomList;
    }

    public int getNrOfRooms() {
        return nrOfRooms;
    }
    public void setNrOfRooms(int nrOfRooms) {
        this.nrOfRooms = nrOfRooms;
    }

    public Door getDoor1() {
        return door1;
    }
    public void setDoor1(Door door1) {
        this.door1 = door1;
    }

    public Door getDoor2() {
        return door2;
    }
    public void setDoor2(Door door2) {
        this.door2 = door2;
    }

    public Door getDoor3() {
        return door3;
    }
    public void setDoor3(Door door3) {
        this.door3 = door3;
    }

    public Door getDoor4() {
        return door4;
    }
    public void setDoor4(Door door4) {
        this.door4 = door4;
    }

    public Pane getLayer() {
        return layer;
    }
    public void setLayer(Pane layer) {
        this.layer = layer;
    }

    public int getNrOfEnemies() {
        return nrOfEnemies;
    }
    public void setNrOfEnemies(int nrOfEnemies) {
        this.nrOfEnemies = nrOfEnemies;
    }

    public Trapdoor getTrapdoor() {
        return trapdoor;
    }
    public void setTrapdoor(Trapdoor trapdoor) {
        this.trapdoor = trapdoor;
    }

    public int getFloorId() {
        return floorId;
    }
    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }
}