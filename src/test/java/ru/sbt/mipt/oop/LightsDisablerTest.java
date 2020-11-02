package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.handlers.LightHandler;
import ru.sbt.mipt.handlers.LightsDisabler;
import ru.sbt.mipt.homeAndComponents.Action;
import ru.sbt.mipt.homeAndComponents.Light;
import ru.sbt.mipt.homeAndComponents.SmartHome;
import ru.sbt.mipt.sensor.SensorEvent;
import ru.sbt.mipt.sensor.SensorEventType;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class LightsDisablerTest {
    private final ArrayList<Light> lights = new ArrayList<>();;

    @Test
    void testDisablesAllLightsWhenEventReceived(){
        //given
        HomeCreator homeCreator = new HomeCreator();
        SmartHome home = homeCreator.createHome();

        LightHandler lightHandler = new LightHandler(home);
        lightHandler.handle(new SensorEvent(SensorEventType.LIGHT_ON, "1"));
        lightHandler.handle(new SensorEvent(SensorEventType.LIGHT_ON, "2"));
        lightHandler.handle(new SensorEvent(SensorEventType.LIGHT_ON, "3"));
        lightHandler.handle(new SensorEvent(SensorEventType.LIGHT_ON, "4"));

        Action action = (Object obj) -> {
            if (obj instanceof Light) {
                Light light = (Light)obj;
                lights.add(light);
            }
        };
        home.execute(action);
        //when
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_DISABLE_ALL);
        LightsDisabler handler = new LightsDisabler(home);
        handler.handle(event);

        ArrayList<Boolean> actualLightStates = new ArrayList<>();
        ArrayList<Boolean> expectedLightStates = new ArrayList<>();

        for (Light light : lights) {
            actualLightStates.add(light.isOn());
            expectedLightStates.add(false);
        }
        //then
        assertArrayEquals(expectedLightStates.toArray(new Boolean[0]), actualLightStates.toArray(new Boolean[0]));
    }
}
