package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.handlers.DoorHandler;
import ru.sbt.mipt.handlers.EntranceDoorCloser;
import ru.sbt.mipt.homeAndComponents.Action;
import ru.sbt.mipt.homeAndComponents.Door;
import ru.sbt.mipt.homeAndComponents.SmartHome;
import ru.sbt.mipt.sensor.SensorEvent;
import ru.sbt.mipt.sensor.SensorEventType;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntranceDoorCloserTest {
    private Door testDoor;
    @Test
    void testClosesEntranceDoorWhenEventReceived(){
        //given
        HomeCreator homeCreator = new HomeCreator();
        SmartHome home = homeCreator.createHome();
        SensorEvent event = new SensorEvent(SensorEventType.ENTRANCE_DOOR_CLOSED, "1");
        EntranceDoorCloser entranceHandler = new EntranceDoorCloser(home);
        //when
        Action action = (Object obj) -> {
            if (obj instanceof Door) {
                Door door = (Door)obj;
                if (door.isEntrance()) {
                    testDoor = door;
                }
            }
        };
        home.execute(action);
        assertTrue(testDoor.isOpen());
        entranceHandler.handle(event);
        //then
        assertFalse(testDoor.isOpen());
    }
}
