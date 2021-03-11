package ru.sbt.mipt.oop;


public class RoomObject implements RoomObjectInterface {
    final String id;

    public RoomObject(String id) {
        this.id = id;
    }

    public void setState(States mean){};
    public void setHome(SmartHome home){};
    public String getString(){return "";};
}
