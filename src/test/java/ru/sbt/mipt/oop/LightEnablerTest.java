package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.handlers.LightHandler;
import ru.sbt.mipt.handlers.LightsDisabler;
import ru.sbt.mipt.handlers.LightsEnabler;
import ru.sbt.mipt.homeAndComponents.Action;
import ru.sbt.mipt.homeAndComponents.Light;
import ru.sbt.mipt.homeAndComponents.SmartHome;
import ru.sbt.mipt.sensor.SensorEvent;
import ru.sbt.mipt.sensor.SensorEventType;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;

public class LightEnablerTest {
    private ArrayList<Light> lights;

    @Test
    void testDisablesAllLightsWhenEventReceived(){
        //given
        lights = new ArrayList<>();

        HomeCreator homeCreator = new HomeCreator();
        SmartHome home = homeCreator.createHome();

        LightHandler lightHandler = new LightHandler(home);
        lightHandler.handle(new SensorEvent(SensorEventType.LIGHT_OFF, "1"));
        lightHandler.handle(new SensorEvent(SensorEventType.LIGHT_OFF, "2"));
        lightHandler.handle(new SensorEvent(SensorEventType.LIGHT_OFF, "3"));
        lightHandler.handle(new SensorEvent(SensorEventType.LIGHT_OFF, "4"));

        Action action = (Object obj) -> {
            if (obj instanceof Light) {
                Light light = (Light)obj;
                lights.add(light);
            }
        };
        home.execute(action);
        //when
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ENABLE_ALL);
        LightsEnabler handler = new LightsEnabler(home);
        handler.handle(event);

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
