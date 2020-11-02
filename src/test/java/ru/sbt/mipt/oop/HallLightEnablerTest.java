package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.handlers.DoorHandler;
import ru.sbt.mipt.handlers.HallLightEnabler;
import ru.sbt.mipt.homeAndComponents.*;
import ru.sbt.mipt.sensor.SensorEvent;
import ru.sbt.mipt.sensor.SensorEventType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import static org.junit.Assert.*;

public class HallLightEnablerTest {
    private Room hall;
    @Test
    void testEnablesLightsInHallWhenEventReceived() {
        //given
        HomeCreator homeCreator = new HomeCreator();
        SmartHome home = homeCreator.createHome();
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ENABLE_HALL, "1");
        HallLightEnabler lightEnabler = new HallLightEnabler(home);

        Action action = (Object obj) -> {
            if (obj instanceof Room) {
                Room room = (Room)obj;
                if (room.getName().equals("hall")) {
                    hall = room;
                }
            }
        };
        home.execute(action);
        lightEnabler.handle(event);

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
}
