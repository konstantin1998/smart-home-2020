package ru.sbt.mipt.oop;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LightHandlerTurnOnTest {
    private Light testLight;
    @Test
    public void turnOnChecksIfLightIsTurnedOn() {
        //given
        HomeCreator homeCreator = new HomeCreator();
        SmartHome home = homeCreator.createHome();
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, "2");
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
        assertFalse(testLight.isOn());
        lightHandler.handle(event);
        //then
        assertTrue(testLight.isOn());
    }
}