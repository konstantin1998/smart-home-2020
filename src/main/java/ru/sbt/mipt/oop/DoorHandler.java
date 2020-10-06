package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorHandler implements Handler {

    private final SmartHome home;

    DoorHandler(SmartHome home) {
        this.home = home;
    }

    private void close(Door door, Room room) {
        door.setOpen(false);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
    }

    private void open(Door door, Room room) {
        door.setOpen(true);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
    }

    public void handle(SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_OPEN || event.getType() == SensorEventType.DOOR_CLOSED) {
            for (Room room : home.getRooms()) {
                for (Door door : room.getDoors()) {
                    if (door.getId().equals(event.getObjectId())) {
                        if (event.getType() == SensorEventType.DOOR_OPEN) {
                            open(door, room);
                        }
                        if (event.getType() == SensorEventType.DOOR_CLOSED) {
                            close(door, room);
                        }
                    }
                }
            }
        }
    }
}
