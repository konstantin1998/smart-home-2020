package ru.sbt.mipt.oop;

import java.util.Collection;

public class Room implements Actionable {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    private Collection<Light> getLights() {
        return lights;
    }

    private Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    public void execute(Action action) {
        for (Door door : getDoors()) {
            door.execute(action);
        }
        for (Light light : getLights()) {
            light.execute(action);
        }
        action.perform(this);
    }
}
