package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.handlers.Action;
import ru.sbt.mipt.handlers.DoorHandler;
import ru.sbt.mipt.homeAndComponents.Door;
import ru.sbt.mipt.homeAndComponents.SmartHome;
import ru.sbt.mipt.sensor.SensorEvent;
import ru.sbt.mipt.sensor.SensorEventType;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DoorHandlerCloseTest {
    private Door testDoor;
    @Test
    public void closeChecksIfDoorIsClosed() {
        //given
        HomeCreator homeCreator = new HomeCreator();
        SmartHome home = homeCreator.createHome();
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        DoorHandler doorHandler = new DoorHandler(home);
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
        assertTrue(testDoor.isOpen());
        doorHandler.handle(event);
        //then
        assertFalse(testDoor.isOpen());
    }
}