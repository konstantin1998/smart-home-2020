package ru.sbt.mipt.handlers;

import ru.sbt.mipt.alarm.Alarm;
import ru.sbt.mipt.sensor.SensorEvent;
import ru.sbt.mipt.sensor.SensorEventType;

public class AlarmHandler implements Handler{
    private final Alarm alarm;

    public AlarmHandler(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void handle(SensorEvent event) {
        if (event.getType() == SensorEventType.ALARM_ACTIVATE) {
            alarm.execute((Object obj) -> {
                if (obj instanceof Alarm) {
                    Alarm currentAlarm = (Alarm)obj;
                    currentAlarm.activate(SensorEventType.ALARM_ACTIVATE.getCode());
                }
            });
        }

        if (event.getType() == SensorEventType.ALARM_DEACTIVATE) {
            alarm.execute((Object obj) -> {
                if (obj instanceof Alarm) {
                    Alarm currentAlarm = (Alarm)obj;
                    currentAlarm.deactivate(SensorEventType.ALARM_DEACTIVATE.getCode());
                }
            });
        }
    }
}
