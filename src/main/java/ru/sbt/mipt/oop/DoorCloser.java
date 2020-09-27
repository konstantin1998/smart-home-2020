package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorCloser {
    private final String id;
    private final SmartHome home;
    DoorCloser(String id, SmartHome home) {
        this.id = id;
        this.home = home;

    }
    public void closeDoor() {
        for (Room room : home.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(id)) {
                    door.setOpen(false);
                    System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                    if (room.getName().equals("hall")) {
                        LightDisabler lightDisabler = new LightDisabler(id, home);
                        lightDisabler.disableAll();
                    }
                }
            }
        }
    }
}
