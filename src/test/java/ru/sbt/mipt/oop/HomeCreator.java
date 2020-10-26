package ru.sbt.mipt.oop;

import ru.sbt.mipt.homeAndComponents.Door;
import ru.sbt.mipt.homeAndComponents.Light;
import ru.sbt.mipt.homeAndComponents.Room;
import ru.sbt.mipt.homeAndComponents.SmartHome;

import java.util.ArrayList;

public class HomeCreator {
    SmartHome createHome() {
        SmartHome home = new SmartHome();

        Light light1 = new Light("1", true);
        Light light2 = new Light("2", false);
        Light light3 = new Light("3", false);
        Light light4 = new Light("4", false);

        Door door1 = new Door(true, "1");
        door1.setLocked(true);
        Door door2 = new Door(false, "2");
        Door door3 = new Door(true, "3");
        Door door4 = new Door(false, "4");

        ArrayList<Light> lights1 = new ArrayList<>();
        lights1.add(light1);
        lights1.add(light2);
        ArrayList<Light> lights2 = new ArrayList<>();
        lights2.add(light3);
        lights2.add(light4);

        ArrayList<Door> kitchenDoors = new ArrayList<>();
        kitchenDoors.add(door1);
        kitchenDoors.add(door2);
        ArrayList<Door> hallDoors = new ArrayList<>();
        hallDoors.add(door3);
        hallDoors.add(door4);

        Room kitchen = new Room(lights1, kitchenDoors, "kitchen");
        Room hall = new Room(lights2, hallDoors, "hall");

        home.addRoom(kitchen);
        home.addRoom(hall);
        return home;
    }
}
