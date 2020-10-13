package ru.sbt.mipt.alarm;

import ru.sbt.mipt.sensor.SensorEvent;

public class Activated implements AlarmState{
    private final Alarm alarm;

    Activated(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
    }

    @Override
    public void deactivate(String code) {
        if (code.equals(alarm.getCode())) {
            alarm.switchState(new Deactivated(alarm));
        } else {
            alarm.switchState(new Anxiety(alarm));
        }

    }

    @Override
    public void switchToAnxietyMode() {
        alarm.switchState(new Anxiety(alarm));
    }

    @Override
    public boolean isDeactivated() {
        return false;
    }

    @Override
    public void handle(SensorEvent event) {
        System.out.println("Sending sms");
        alarm.switchState(new Anxiety(alarm));
    }
}
