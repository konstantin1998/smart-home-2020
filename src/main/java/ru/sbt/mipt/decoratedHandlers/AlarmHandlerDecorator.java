package ru.sbt.mipt.decoratedHandlers;

import ru.sbt.mipt.alarm.Alarm;
import ru.sbt.mipt.handlers.AlarmHandler;
import ru.sbt.mipt.handlers.Handler;
import ru.sbt.mipt.sensor.SensorEvent;

public class AlarmHandlerDecorator implements Handler {
    private final Alarm alarm;
    private final AlarmHandler alarmHandler;

    AlarmHandlerDecorator(Alarm alarm, AlarmHandler alarmHandler) {
        this.alarm = alarm;
        this.alarmHandler = alarmHandler;
    }

    @Override
    public void handle(SensorEvent event) {
        alarm.handle(event);
        if (alarm.isDeactivated()) {
            alarmHandler.handle(event);
        }
    }
}
