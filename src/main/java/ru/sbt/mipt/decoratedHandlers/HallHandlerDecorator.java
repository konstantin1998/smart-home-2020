package ru.sbt.mipt.decoratedHandlers;

import ru.sbt.mipt.alarm.Alarm;
import ru.sbt.mipt.handlers.HallHandler;
import ru.sbt.mipt.handlers.Handler;
import ru.sbt.mipt.sensor.SensorEvent;

public class HallHandlerDecorator implements Handler {
    private final Alarm alarm;
    private final HallHandler hallHandler;

    public HallHandlerDecorator(Alarm alarm, HallHandler hallHandler) {
        this.alarm = alarm;
        this.hallHandler = hallHandler;
    }

    @Override
    public void handle(SensorEvent event) {
        alarm.handle(event);
        if (alarm.isDeactivated()) {
            hallHandler.handle(event);
        }
    }
}
