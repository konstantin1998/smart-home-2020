package ru.sbt.mipt.alarm;

import ru.sbt.mipt.homeAndComponents.Action;
import ru.sbt.mipt.sensor.SensorEvent;

public class Anxiety implements AlarmState {
    private final Alarm alarm;

    Anxiety(Alarm alarm) {
        this.alarm = alarm;
    }
    @Override
    public void activate(String code) {
        alarm.switchState(new Activated(alarm));
        alarm.setCode(code);
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
