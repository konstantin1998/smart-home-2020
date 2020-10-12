package ru.sbt.mipt.homeAndComponents;

import ru.sbt.mipt.handlers.Action;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    private Collection<Room> getRooms() {
        return rooms;
    }

    @Override
    public void execute(Action action) {
        for (Room room : getRooms()) {
            room.execute(action);
        }
        action.perform(this);
    }
}
