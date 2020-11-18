package ru.sbt.mipt.handlers;

import ru.sbt.mipt.homeAndComponents.Action;
import ru.sbt.mipt.homeAndComponents.Door;
import ru.sbt.mipt.homeAndComponents.SmartHome;
import ru.sbt.mipt.sensor.SensorEvent;
import ru.sbt.mipt.sensor.SensorEventType;

public class DoorLockHandler implements Handler{
    private final SmartHome home;

    public DoorLockHandler(SmartHome home) {
        this.home = home;
    }

    @Override
    public void handle(SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_LOCKED) {
            Action action = (Object obj) -> {
                if (obj instanceof Door) {
                    Door door = (Door)obj;
                    if (door.getId().equals(event.getObjectId())) {
                        door.setLocked(true);
                    }
                }
            };
            home.execute(action);
        }

        if (event.getType() == SensorEventType.DOOR_UNLOCKED) {
            Action action = (Object obj) -> {
                if (obj instanceof Door) {
                    Door door = (Door)obj;
                    if (door.getId().equals(event.getObjectId())) {
                        door.setLocked(false);
                    }
                }
            };
            home.execute(action);
        }
    }
}
