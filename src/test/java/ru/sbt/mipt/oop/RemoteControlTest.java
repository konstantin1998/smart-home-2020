package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.alarm.Alarm;
import ru.sbt.mipt.handlers.*;
import ru.sbt.mipt.homeAndComponents.*;
import ru.sbt.mipt.remoteController.RemoteController;
import ru.sbt.mipt.remoteController.commands.*;
import ru.sbt.mipt.sensor.SensorEvent;
import ru.sbt.mipt.sensor.SensorEventType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RemoteControlTest {
    private RemoteController remoteController;
    private SmartHome home;
    private final ArrayList<Light> lights = new ArrayList<>();
    private String id;
    private Alarm alarm;
    private Door entrance;
    private Room hall;

    private void init() {
        HomeCreator homeCreator = new HomeCreator();
        home = homeCreator.createHome();
        alarm = new Alarm();

        ArrayList<Handler> handlers = new ArrayList<>();
        handlers.add(new AlarmHandler(alarm));
        handlers.add(new HallLightEnabler(home));
        handlers.add(new EntranceDoorCloser(home));
        handlers.add(new LightsDisabler(home));
        handlers.add(new LightsEnabler(home));

        HashMap<String, Command> buttonToCommand = new HashMap<>();
        buttonToCommand.put("1", new ActivateAlarmCommand(handlers));
        buttonToCommand.put("2", new TriggerAlarmCommand(handlers));
        buttonToCommand.put("3", new EnableLightsInHallCommand(handlers));
        buttonToCommand.put("4", new CloseEntranceDoorCommand(handlers));
        buttonToCommand.put("A", new AllLightsDisableCommand(handlers));
        buttonToCommand.put("B", new AllLightsEnableCommand(handlers));

        id = "1";
        remoteController = new RemoteController(buttonToCommand, id);
    }

    @Test
    void testActivatesAlarmWhenButtonPressed() {
        //given
        init();

        boolean isTrue = alarm.isDeactivated();
        //when
        assertTrue(isTrue);
        remoteController.onButtonPressed("1", id);
        //then
        boolean isFalse = alarm.isDeactivated();
        assertFalse(isFalse);
    }

    @Test
    void testTriggersAlarmWhenButtonPressed() {
        //given
        init();

        boolean isTrue = alarm.isDeactivated();
        //when
        assertTrue(isTrue);
        remoteController.onButtonPressed("2", id);
        //then
        boolean isFalse = alarm.isDeactivated();
        assertFalse(isFalse);
    }

    @Test
    void testEnablesLightsInHallWhenButtonPressed() {
        //given
        init();

        remoteController.onButtonPressed("3", id);
        Action action = (Object obj) -> {
            if (obj instanceof Room) {
                Room room = (Room)obj;
                if (room.getName().equals("hall")) {
                    hall = room;
                }
            }
        };
        home.execute(action);

        Collection<Light> lights = new ArrayList<Light>();
        try {
            Field lightsField = hall.getClass().getDeclaredField("lights");
            lightsField.setAccessible(true);
            lights = (Collection<Light>) lightsField.get(hall);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        //when
        ArrayList<Boolean> actualLightStates = new ArrayList<>();
        ArrayList<Boolean> expectedLightStates = new ArrayList<>();
        for (Light light : lights) {
            actualLightStates.add(light.isOn());
            expectedLightStates.add(true);
        }
        //then
        assertArrayEquals(expectedLightStates.toArray(new Boolean[0]), actualLightStates.toArray(new Boolean[0]));
    }

    @Test
    void testClosesEntranceDoorWhenButtonPressed() {
        //given
        init();

        Action action = (Object obj) -> {
            if (obj instanceof Door) {
                Door door = (Door)obj;
                if (door.isEntrance()) {
                    entrance = door;
                }
            }
        };
        //when
        home.execute(action);
        assertTrue(entrance.isOpen());
        remoteController.onButtonPressed("4", id);
        //then
        assertFalse(entrance.isOpen());
    }

    @Test
    void testDisablesAllLightsWhenButtonPressed(){
        //given
        init();

        LightsEnabler enabler = new LightsEnabler(home);
        enabler.handle(new SensorEvent(SensorEventType.LIGHT_ENABLE_ALL));

        Action action = (Object obj) -> {
            if (obj instanceof Light) {
                Light light = (Light)obj;
                lights.add(light);
            }
        };
        home.execute(action);
        //when
        remoteController.onButtonPressed("A", id);

        ArrayList<Boolean> actualLightStates = new ArrayList<>();
        ArrayList<Boolean> expectedLightStates = new ArrayList<>();
        for (Light light : lights) {
            actualLightStates.add(light.isOn());
            expectedLightStates.add(false);
        }
        //then
        assertArrayEquals(expectedLightStates.toArray(new Boolean[0]), actualLightStates.toArray(new Boolean[0]));
    }

    @Test
    void testEnablesAllLightsWhenButtonPressed(){
        //given
        init();

        LightsDisabler disabler = new LightsDisabler(home);
        disabler.handle(new SensorEvent(SensorEventType.LIGHT_DISABLE_ALL));

        Action action = (Object obj) -> {
            if (obj instanceof Light) {
                Light light = (Light)obj;
                lights.add(light);
            }
        };
        home.execute(action);
        //when
        remoteController.onButtonPressed("B", id);

        ArrayList<Boolean> actualLightStates = new ArrayList<>();
        ArrayList<Boolean> expectedLightStates = new ArrayList<>();
        for (Light light : lights) {
            actualLightStates.add(light.isOn());
            expectedLightStates.add(true);
        }
        //then
        assertArrayEquals(expectedLightStates.toArray(new Boolean[0]), actualLightStates.toArray(new Boolean[0]));
    }
}
