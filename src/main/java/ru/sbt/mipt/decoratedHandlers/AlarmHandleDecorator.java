package ru.sbt.mipt.decoratedHandlers;

import ru.sbt.mipt.alarm.Alarm;
import ru.sbt.mipt.handlers.AlarmHandler;
import ru.sbt.mipt.handlers.Handler;
import ru.sbt.mipt.sensor.SensorEvent;

public class AlarmHandleDecorator implements Handler {
    private final Alarm alarm;
    private final AlarmHandler alarmHandler;

    AlarmHandleDecorator(Alarm alarm) {
        this.alarm = alarm;
        this.alarmHandler = new AlarmHandler(alarm);
    }
    @Override
    public void handle(SensorEvent event) {
        alarm.handle(event);
        if (alarm.isDeactivated()) {
            alarmHandler.handle(event);
        }
    }
}
