package ru.sbt.mipt.handlers;

import ru.sbt.mipt.alarm.Alarm;
import ru.sbt.mipt.sensor.SensorEvent;

public class Decorator implements Handler {
    private final Handler handler;
    private final Alarm alarm;

    public Decorator(Alarm alarm, Handler handler) {
        this.alarm = alarm;
        this.handler = handler;
    }

    @Override
    public void handle(SensorEvent event) {
        alarm.reactToEvent(event);
        if (alarm.isDeactivated()) {
            handler.handle(event);
        }
    }
}
