package ru.sbt.mipt.handlers;

import ru.sbt.mipt.homeAndComponents.Action;
import ru.sbt.mipt.homeAndComponents.Light;
import ru.sbt.mipt.homeAndComponents.SmartHome;
import ru.sbt.mipt.sensor.SensorEvent;
import ru.sbt.mipt.sensor.SensorEventType;

public class LightsDisabler implements Handler {
    private final SmartHome home;

    public LightsDisabler(SmartHome home) {
        this.home = home;
    }

    @Override
    public void handle(SensorEvent event) {
        if (event.getType() == SensorEventType.LIGHT_DISABLE_ALL) {
            Action action = (Object obj) -> {
                if (obj instanceof Light) {
                    Light light = (Light)obj;
                    light.setOn(false);
                }
            };
            home.execute(action);
        }
    }
}
