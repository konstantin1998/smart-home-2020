package ru.sbt.mipt.handlers;

import ru.sbt.mipt.alarm.Alarm;
import ru.sbt.mipt.sensor.SensorEvent;

public class AlarmDecorator implements Handler {
    private final Handler handler;
    private final Alarm alarm;
    private final SMSSender sender;

    public AlarmDecorator(Alarm alarm, Handler handler, SMSSender sender) {
        this.alarm = alarm;
        this.handler = handler;
        this.sender = sender;
    }


    @Override
    public void handle(SensorEvent event) {

        if (alarm.isDeactivated()) {
            handler.handle(event);
        } else {
            alarm.switchToAnxietyMode();
            sender.sendSMS();
        }
    }
}
