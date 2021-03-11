package ru.sbt.mipt.oop;


public class Door extends RoomObject implements RoomObjectInterface {
    private static final String[] st = {" was opened."," was closed."};
    private States isOpen;
    private SmartHome home;

    public Door(States isOpen, String id) {
        super(id);
        this.isOpen = isOpen;
    }

    public String getId() {
        return id;
    }

    @Override
    public void setState(States open) {
        isOpen = open;
    }

    @Override
    public String getString(){
        return "Door " + this.getId() + " in room " + home.findRoomForDoor(this.getId()).getName() + (isOpen.getString());
    };

    @Override
    public void setHome(SmartHome home) {
        this.home = home;
    }
}

