package ru.sbt.mipt.oop;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HallHandlerDoorTest {
    private Door testDoor;
    @Test
    public void closeChecksIfDoorIsClosed() {
        //given
        HomeCreator homeCreator = new HomeCreator();
        SmartHome home = homeCreator.createHome();
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "3");
        HallHandler hallHandler = new HallHandler(home);
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
        hallHandler.handle(event);
        //then
        assertFalse(testDoor.isOpen());
    }
}