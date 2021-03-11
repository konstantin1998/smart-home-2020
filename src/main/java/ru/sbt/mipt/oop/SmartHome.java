package ru.sbt.mipt.oop;


import java.util.ArrayList;
import java.util.Collection;

public class SmartHome {
    private Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
        for (Room r:rooms) {
            r.setHome(this);
//            r.setHomeForAll();
        }
    }

    public void addRoom(Room room) {
        rooms.add(room);
        room.setHome(this);
//        room.setHomeForAll();
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    RoomObject findDoor(String id){
        for (Room room : this.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(id)) {
                    return door;
                }
            }
        }
        return null;
    }

    RoomObject findLight(String id){
        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(id)) {
//                    light.setRoom(room);
                    return light;
                }
            }
        }
        return null;
    }

    Room findRoomForLight(String id){
        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(id)) {
                    return room;
                }
            }
        }
        return null;
    }

    Room findRoomForDoor(String id){
        for (Room room : rooms) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(id)) {
                    return room;
                }
            }
        }
        return null;
    }

    void lightOff(){
        for (Room r:rooms){
            for (Light l : r.getLights()) {
                l.setState(States.LIGHT_OFF);
                System.out.println("Pretent we're sending command " + new SensorCommand(CommandType.LIGHT_OFF, l.getId()));
            }
        }
    }

    public RoomObject findObject(String id){
        for (Room room:rooms){
            for (Light light:room.getLights()){
                if (light.getId().equals(id)){
                    return light;
                }
            }
            for (Door door:room.getDoors()){
                if (door.getId().equals(id)){
                    return door;
                }
            }
        }
        return null;
    }

    public void setHome(Collection<RoomObject> o){
        for (RoomObject ro: o) {
            ro.setHome(this);
        }
    }

}
