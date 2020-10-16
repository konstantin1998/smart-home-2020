package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorHandler implements Handler {

    private final SmartHome home;

    public DoorHandler(SmartHome home) {
        this.home = home;
    }

    public void handle(SensorEvent event) {

        if (event.getType() == SensorEventType.DOOR_OPEN) {
            Action action = (Object obj) -> {
                if (obj instanceof Door) {
                    Door door = (Door)obj;
                    if (door.getId().equals(event.getObjectId())) {
                        door.setOpen(true);
                    }
                }
            };
            home.execute(action);
        }

        if (event.getType() == SensorEventType.DOOR_CLOSED) {
            Action action = (Object obj) -> {
                if (obj instanceof Door) {
                    Door door = (Door)obj;
                    if (door.getId().equals(event.getObjectId())) {
                        door.setOpen(false);
                    }
                }
            };
            home.execute(action);
        }
    }
}
