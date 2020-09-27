package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorOpener {
    private final String id;
    private final SmartHome home;
    DoorOpener(String id, SmartHome home) {
        this.id = id;
        this.home = home;

    }
    public void openDoor() {
        for (Room room : home.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(id)) {
                    door.setOpen(true);
                    System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                }
            }
        }
    }
}
