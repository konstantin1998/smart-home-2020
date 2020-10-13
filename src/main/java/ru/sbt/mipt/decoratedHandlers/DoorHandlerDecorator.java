package ru.sbt.mipt.decoratedHandlers;

import ru.sbt.mipt.alarm.Alarm;
import ru.sbt.mipt.handlers.DoorHandler;
import ru.sbt.mipt.handlers.Handler;
import ru.sbt.mipt.homeAndComponents.SmartHome;
import ru.sbt.mipt.sensor.SensorEvent;

public class DoorHandlerDecorator implements Handler {
    private final DoorHandler doorHandler;
    private final Alarm alarm;

    public DoorHandlerDecorator(SmartHome home, Alarm alarm) {
        this.alarm = alarm;
        this.doorHandler = new DoorHandler(home);
    }

    @Override
    public void handle(SensorEvent event) {
        alarm.handle(event);
        if (alarm.isDeactivated()) {
            doorHandler.handle(event);
        }
    }
}
