package ru.sbt.mipt.handlers;

import ru.sbt.mipt.homeAndComponents.*;
import ru.sbt.mipt.sensor.SensorEvent;
import ru.sbt.mipt.sensor.SensorEventType;

public class HallLightEnabler implements Handler{
    private final SmartHome home;

    public HallLightEnabler(SmartHome home) {
        this.home = home;
    }
    @Override
    public void handle(SensorEvent event) {
        if (event.getType() == SensorEventType.LIGHT_ENABLE_HALL) {
            home.execute((Object obj) -> {
                if (obj instanceof Room) {
                    Room room = (Room)obj;
                    if (room.getName().equals("hall")) {
                        Action action = (Object item) -> {
                            if (item instanceof Light) {
                                Light light = (Light) item;
                                light.setOn(true);
                            }
                        };
                        room.execute(action);
                    }
                }
            });
        }
    }
}
