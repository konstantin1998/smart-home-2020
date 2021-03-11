package ru.sbt.mipt.oop;



import java.util.Collection;

public class Room {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;
    private SmartHome home;

    public void setHome(SmartHome home) {
        this.home = home;
    }

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }


    public void setHome(Collection<RoomObject> o){
        for (RoomObject ro: o) {
            ro.setHome(this.home);
        }
    }

}
