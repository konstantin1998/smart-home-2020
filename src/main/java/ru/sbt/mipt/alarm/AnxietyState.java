package ru.sbt.mipt.alarm;

import ru.sbt.mipt.sensor.SensorEvent;

public class AnxietyState implements AlarmState {
    private final Alarm alarm;

    AnxietyState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        alarm.switchState(new AnxietyState(alarm));
    }

    @Override
    public void deactivate(String code) {
        if (code.equals(alarm.getCode())) {
            alarm.switchState(new DeactivatedState(alarm));
        } else {
            alarm.switchState(new AnxietyState(alarm));
        }
    }

    @Override
    public void switchToAnxietyMode() {
    }

}
