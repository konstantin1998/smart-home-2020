package ru.sbt.mipt.handlers;

import ru.sbt.mipt.homeAndComponents.Light;
import ru.sbt.mipt.homeAndComponents.SmartHome;
import ru.sbt.mipt.sensor.SensorEvent;
import ru.sbt.mipt.sensor.SensorEventType;

public class LightHandler implements Handler {
    private final SmartHome home;

    public LightHandler(SmartHome home) {
        this.home = home;
    }

    public void handle(SensorEvent event) {
        if (event.getType() == SensorEventType.LIGHT_ON) {
            Action action = (Object obj) -> {
                if (obj instanceof Light) {
                    Light light = (Light)obj;
                    if (light.getId().equals(event.getObjectId())) {
                        light.setOn(true);
                    }
                }
            };
            home.execute(action);
        }

        if (event.getType() == SensorEventType.LIGHT_OFF) {
            Action action = (Object obj) -> {
                if (obj instanceof Light) {
                    Light light = (Light)obj;
                    if (light.getId().equals(event.getObjectId())) {
                        light.setOn(false);
                    }
                }
            };
            home.execute(action);
        }
    }
}
