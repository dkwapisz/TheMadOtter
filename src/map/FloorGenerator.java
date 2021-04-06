package map;

import javafx.scene.layout.Pane;
import model.block.*;

import java.util.ArrayList;

public class FloorGenerator {

    private ArrayList<Room> roomList = new ArrayList<>();
    private int nrOfRooms;  //pierwiastek z liczby pokoi
    private int floorId;
    private Door door1;
    private Door door2;
    private Door door3;
    private Door door4;
    private Trapdoor trapdoor;
    private Pane layer;
    private RNG rng;

    public FloorGenerator(int nrOfRooms, Pane layer, int floorId) {
        this.nrOfRooms = nrOfRooms;
        this.layer = layer;
        this.floorId = floorId;
        trapdoor = new Trapdoor(368, 368, layer);
        door1 = new Door(360, 0, "/graphics/doorH.png",  layer, 1);         // góra
        door2 = new Door(0, 360, "/graphics/doorV.png",  layer, 2);        // lewo
        door3 = new Door(360, 768, "/graphics/doorH.png",   layer, 3);        // dół
        door4 = new Door(768, 360, "/graphics/doorV.png",   layer, 4);     // prawo
        door3.getImageView().setRotate(180);
        door4.getImageView().setRotate(180);
        generateMap();

    }

    private void generateMap() {
        int roomId = 0;
        for(int i=0; i < nrOfRooms; i++) {
            for(int j=0; j < nrOfRooms; j++) {
                this.rng = new RNG((nrOfRooms*nrOfRooms-1)/2, floorId, roomId, layer);
                ArrayList<Door> doors = new ArrayList<>();
                if((i == 0 || i == nrOfRooms-1) || (j == 0 || j == nrOfRooms-1)) {
                    if((i == 0 || i == nrOfRooms-1) && (j == 0 || j == nrOfRooms-1)) {
                        if(i == 0 && j == 0) {
                            doors.add(door3);
                            doors.add(door4);
                            roomList.add(new Room(doors, roomId, rng.enemiesGenerator(roomId), null, rng.blockGenerator(roomId), this));
                        }
                        else if(i == nrOfRooms-1 && j == 0) {
                            doors.add(door2);
                            doors.add(door3);
                            roomList.add(new Room(doors, roomId, rng.enemiesGenerator(roomId), null, rng.blockGenerator(roomId), this));
                        }
                        else if(i == 0 && j == nrOfRooms-1) {
                            doors.add(door1);
                            doors.add(door4);
                            roomList.add(new Room(doors, roomId, rng.enemiesGenerator(roomId), null, rng.blockGenerator(roomId), this));
                        }
                        else if(i == nrOfRooms-1 && j == nrOfRooms-1) {
                            doors.add(door1);
                            doors.add(door2);
                            roomList.add(new Room(doors, roomId, rng.enemiesGenerator(roomId), null, rng.blockGenerator(roomId), this));
                        }
                    } else {
                        if(i == 0) {
                            doors.add(door1);
                            doors.add(door3);
                            doors.add(door4);
                            roomList.add(new Room(doors, roomId, rng.enemiesGenerator(roomId), null, rng.blockGenerator(roomId), this));
                        }
                        else if(i == nrOfRooms - 1) {
                            doors.add(door1);
                            doors.add(door2);
                            doors.add(door3);
                            roomList.add(new Room(doors, roomId, rng.enemiesGenerator(roomId), null, rng.blockGenerator(roomId), this));
                        }
                        else if(j == 0) {
                            doors.add(door2);
                            doors.add(door3);
                            doors.add(door4);
                            roomList.add(new Room(doors, roomId, rng.enemiesGenerator(roomId), null, rng.blockGenerator(roomId), this));
                        }
                        else if(j == nrOfRooms-1) {
                            doors.add(door1);
                            doors.add(door2);
                            doors.add(door4);
                            roomList.add(new Room(doors, roomId, rng.enemiesGenerator(roomId), null, rng.blockGenerator(roomId), this));
                        }
                    }
                } else {
                    doors.add(door1);
                    doors.add(door2);
                    doors.add(door3);
                    doors.add(door4);
                    if(roomId == (nrOfRooms*nrOfRooms-1)/2) {
                        roomList.add(new Room(doors, roomId, null, rng.itemsGenerator(roomId), null, this));

                    } else {
                        roomList.add(new Room(doors, roomId, rng.enemiesGenerator(roomId), null, rng.blockGenerator(roomId), this));
                    }
                }
                if(roomList.get(roomId).getEnemies().size() != 0) {
                    roomList.get(roomId).setClean(false);
                } else {
                    roomList.get(roomId).setClean(true);
                }
                if(roomId == (nrOfRooms*nrOfRooms-1)/2) {
                    roomList.get(roomId).getBlocks().add(trapdoor);
                }
                roomId++;
            }
        }
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