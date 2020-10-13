package ru.sbt.mipt.decoratedHandlers;

import ru.sbt.mipt.alarm.Alarm;
import ru.sbt.mipt.handlers.Handler;
import ru.sbt.mipt.handlers.LightHandler;
import ru.sbt.mipt.homeAndComponents.SmartHome;
import ru.sbt.mipt.sensor.SensorEvent;

public class LightHandlerDecorator implements Handler {
    private final Alarm alarm;
    private final LightHandler lightHandler;

    public LightHandlerDecorator(Alarm alarm, LightHandler lightHandler) {
        this.alarm = alarm;
        this.lightHandler = lightHandler;
    }

    @Override
    public void handle(SensorEvent event) {
        alarm.handle(event);
        if (alarm.isDeactivated()) {
            lightHandler.handle(event);
        }
    }
}
