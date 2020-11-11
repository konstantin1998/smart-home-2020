package ru.sbt.mipt.handlers;

import ru.sbt.mipt.alarm.Alarm;
import ru.sbt.mipt.sensor.SensorEvent;

public class AlarmDecorator implements Handler {
    private final Handler handler;
    private final Alarm alarm;
    private final SMSSender smsSender;

    public AlarmDecorator(Alarm alarm, Handler handler, SMSSender smsSender) {
        this.alarm = alarm;
        this.handler = handler;
        this.smsSender = smsSender;
    }

    @Override
    public void handle(SensorEvent event) {
        if (alarm.isDeactivated()) {
            handler.handle(event);
        } else {
            smsSender.sendSMS();
            alarm.switchToAnxietyMode();
        }
    }
}
