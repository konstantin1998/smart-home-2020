package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.homeAndComponents.Action;
import ru.sbt.mipt.handlers.LightHandler;
import ru.sbt.mipt.homeAndComponents.Light;
import ru.sbt.mipt.homeAndComponents.SmartHome;
import ru.sbt.mipt.sensor.SensorEvent;
import ru.sbt.mipt.sensor.SensorEventType;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LightHandlerTurnOffTest {
    private Light testLight;
    @Test
    public void turnOffChecksIfLightIsTurnedOff() {
        //given
        HomeCreator homeCreator = new HomeCreator();
        SmartHome home = homeCreator.createHome();
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, "1");
        LightHandler lightHandler = new LightHandler(home);
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
        lightHandler.handle(event);
        //then
        assertFalse(testLight.isOn());
    }
}