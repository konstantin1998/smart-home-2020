package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.handlers.DoorLockHandler;
import ru.sbt.mipt.homeAndComponents.Action;
import ru.sbt.mipt.homeAndComponents.Door;
import ru.sbt.mipt.homeAndComponents.SmartHome;
import ru.sbt.mipt.sensor.SensorEvent;
import ru.sbt.mipt.sensor.SensorEventType;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DoorLockHandlerTest {
    private Door testDoor;

    @Test
    public void testLocksDoorWhenLockEventReceived() {
        //given
        HomeCreator homeCreator = new HomeCreator();
        SmartHome home = homeCreator.createHome();
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_LOCKED, "2");
        DoorLockHandler doorLockHandler = new DoorLockHandler(home);
        //when
        Action action = (Object obj) -> {
            if (obj instanceof Door) {
                Door door = (Door)obj;
                if (door.getId().equals(event.getObjectId())) {
                    testDoor = door;
                }
            }
        };
        home.execute(action);
        assertFalse(testDoor.isLocked());
        doorLockHandler.handle(event);
        //then
        assertTrue(testDoor.isLocked());
    }

    @Test
    public void testUnlocksDoorWhenUnlockEventReceived() {
        //given
        HomeCreator homeCreator = new HomeCreator();
        SmartHome home = homeCreator.createHome();
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_UNLOCKED, "1");
        DoorLockHandler doorLockHandler = new DoorLockHandler(home);
        //when
        Action action = (Object obj) -> {
            if (obj instanceof Door) {
                Door door = (Door)obj;
                if (door.getId().equals(event.getObjectId())) {
                    testDoor = door;
                }
            }
        };
        home.execute(action);
        assertTrue(testDoor.isLocked());
        doorLockHandler.handle(event);
        //then
        assertFalse(testDoor.isLocked());
    }
}
