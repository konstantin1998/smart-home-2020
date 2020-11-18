package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;
import org.junit.Test;
import ru.sbt.mipt.adapter.HandlerAdapter;
import ru.sbt.mipt.handlers.DoorHandler;
import ru.sbt.mipt.handlers.DoorLockHandler;
import ru.sbt.mipt.handlers.LightHandler;
import ru.sbt.mipt.homeAndComponents.Action;
import ru.sbt.mipt.homeAndComponents.Door;
import ru.sbt.mipt.homeAndComponents.Light;
import ru.sbt.mipt.homeAndComponents.SmartHome;
import ru.sbt.mipt.sensor.SensorEventType;

import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdapterTest {
    private Door testDoor;
    private Light testLight;

    @Test
    public void testOpensDoorWhenOpenEventReceived() {
        //given
        HashMap<String, SensorEventType> factory = new Factory().getFactory();

        HomeCreator homeCreator = new HomeCreator();
        SmartHome home = homeCreator.createHome();
        CCSensorEvent event = new CCSensorEvent("DoorIsOpen", "2");
        DoorHandler doorHandler = new DoorHandler(home);
        HandlerAdapter adoptedHandler = new HandlerAdapter(doorHandler, factory);
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
        assertFalse(testDoor.isOpen());
        adoptedHandler.handleEvent(event);
        //then
        assertTrue(testDoor.isOpen());
    }

    @Test
    public void testClosesDoorWhenCloseEventReceived() {
        //given
        HashMap<String, SensorEventType> factory = new Factory().getFactory();
        HomeCreator homeCreator = new HomeCreator();
        SmartHome home = homeCreator.createHome();
        CCSensorEvent event = new CCSensorEvent("DoorIsClosed", "1");
        DoorHandler doorHandler = new DoorHandler(home);
        HandlerAdapter adoptedHandler = new HandlerAdapter(doorHandler, factory);
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
        adoptedHandler.handleEvent(event);
        //then
        assertFalse(testDoor.isOpen());
    }

    @Test
    public void testTurnsOnLightWhenTurnOnEventReceived() {
        //given
        HashMap<String, SensorEventType> factory = new Factory().getFactory();
        HomeCreator homeCreator = new HomeCreator();
        SmartHome home = homeCreator.createHome();
        CCSensorEvent event = new CCSensorEvent("LightIsOn", "2");
        LightHandler LightHandler = new LightHandler(home);
        HandlerAdapter adoptedHandler = new HandlerAdapter(LightHandler, factory);
        //when
        Action action = (Object obj) -> {
            if (obj instanceof Light) {
                Light light = (Light)obj;
                if (light.getId().equals(event.getObjectId())) {
                    testLight = light;
                }
            }
        };
        home.execute(action);
        assertFalse(testLight.isOn());
        adoptedHandler.handleEvent(event);
        //then
        assertTrue(testLight.isOn());
    }

    @Test
    public void testTurnsOffLightWhenTurnOffEventReceived() {
        //given
        HashMap<String, SensorEventType> factory = new Factory().getFactory();
        HomeCreator homeCreator = new HomeCreator();
        SmartHome home = homeCreator.createHome();
        CCSensorEvent event = new CCSensorEvent("LightIsOff", "1");
        LightHandler LightHandler = new LightHandler(home);
        HandlerAdapter adoptedHandler = new HandlerAdapter(LightHandler, factory);
        //when
        Action action = (Object obj) -> {
            if (obj instanceof Light) {
                Light light = (Light)obj;
                if (light.getId().equals(event.getObjectId())) {
                    testLight = light;
                }
            }
        };
        home.execute(action);
        assertTrue(testLight.isOn());
        adoptedHandler.handleEvent(event);
        //then
        assertFalse(testLight.isOn());
    }

    @Test
    public void testLocksDoorWhenLockEventReceived() {
        //given
        HashMap<String, SensorEventType> factory = new Factory().getFactory();
        HomeCreator homeCreator = new HomeCreator();
        SmartHome home = homeCreator.createHome();
        CCSensorEvent event = new CCSensorEvent("DoorIsLocked", "2");
        DoorLockHandler doorLockHandler = new DoorLockHandler(home);
        HandlerAdapter adoptedHandler = new HandlerAdapter(doorLockHandler, factory);
        //when
        Action action = (Object obj) -> {
            if (obj instanceof Door) {
                Door door = (Door) obj;
                if (door.getId().equals(event.getObjectId())) {
                    testDoor = door;
                }
            }
        };
        home.execute(action);
        assertFalse(testDoor.isLocked());
        adoptedHandler.handleEvent(event);
        //then
        assertTrue(testDoor.isLocked());
    }

    @Test
    public void testUnlocksDoorWhenUnlockEventReceived() {
        //given
        HashMap<String, SensorEventType> factory = new Factory().getFactory();
        HomeCreator homeCreator = new HomeCreator();
        SmartHome home = homeCreator.createHome();
        CCSensorEvent event = new CCSensorEvent("DoorIsUnlocked", "1");
        DoorLockHandler doorLockHandler = new DoorLockHandler(home);
        HandlerAdapter adoptedHandler = new HandlerAdapter(doorLockHandler, factory);
        //when
        Action action = (Object obj) -> {
            if (obj instanceof Door) {
                Door door = (Door) obj;
                if (door.getId().equals(event.getObjectId())) {
                    testDoor = door;
                }
            }
        };
        home.execute(action);
        assertTrue(testDoor.isLocked());
        adoptedHandler.handleEvent(event);
        //then
        assertFalse(testDoor.isLocked());
    }
}
