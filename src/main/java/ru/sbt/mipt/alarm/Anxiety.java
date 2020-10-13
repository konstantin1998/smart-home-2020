package ru.sbt.mipt.alarm;

import ru.sbt.mipt.homeAndComponents.Action;
import ru.sbt.mipt.sensor.SensorEvent;

public class Anxiety implements AlarmState {
    private final Alarm alarm;

    Anxiety(Alarm alarm) {
        this.alarm = alarm;
    }
    @Override
    public void activate() {
        alarm.switchState(new Activated(alarm));
    }

    @Override
    public void deactivate() {
        alarm.switchState(new Deactivated(alarm));
    }

    @Override
    public void switchToAnxietyMode() {
    }

    @Override
    public boolean isDeactivated() {
        return false;
    }

    @Override
    public void handle(SensorEvent event) {
        System.out.println("Sending sms");
    }
}
