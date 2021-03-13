package map;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class MapGenerator {

    private ArrayList<Room> roomList = new ArrayList<>();
    private int nrOfRooms;              //pierwiastek z liczby pokoi
    private Door door1;
    private Door door2;
    private Door door3;
    private Door door4;

    public MapGenerator(int nrOfRooms, Pane layer) {
        this.nrOfRooms = nrOfRooms;
        door1 = new Door(360, 0, "/graphics/door.png", "/graphics/door.png", layer, 1);     // góra
        door2 = new Door(0, 360, "/graphics/door.png", "/graphics/door.png", layer, 2);     // prawo
        door3 = new Door(360, 720, "/graphics/door.png", "/graphics/door.png",  layer, 3);  // dół
        door4 = new Door(720, 360, "/graphics/door.png", "/graphics/door.png",  layer, 4);  // lewo
        generateMap();
    }


    private void generateMap() {
        int k = 0;
        for(int i=0; i < nrOfRooms; i++) {
            for(int j=0; j < nrOfRooms; j++) {
                ArrayList<Door> doors = new ArrayList<>();
                if((i == 0 || i == nrOfRooms-1) || (j == 0 || j == nrOfRooms-1)) {
                    if((i == 0 || i == nrOfRooms-1) && (j == 0 || j == nrOfRooms-1)) {
                        doors.add(door1);
                        doors.add(door2);
                        roomList.add(new Room(doors, false, k));
                    } else {
                        if((i == 0 || i == nrOfRooms-1)) {
                            doors.add(door2);
                            doors.add(door3);
                            doors.add(door4);
                            roomList.add(new Room(doors, false, k));
                        }
                        else if((j == 0 || j == nrOfRooms-1)) {
                            doors.add(door1);
                            doors.add(door2);
                            doors.add(door3);
                            roomList.add(new Room(doors, false, k));
                        }
                    }
                } else {
                    doors.add(door1);
                    doors.add(door2);
                    doors.add(door3);
                    doors.add(door4);
                    if(k == (nrOfRooms*nrOfRooms-1)/2) {
                        roomList.add(new Room(doors, true, k));
                    } else {
                        roomList.add(new Room(doors, false, k));
                    }
                }
                k++;
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
}
