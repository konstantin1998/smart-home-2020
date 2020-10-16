package ru.sbt.mipt.alarm;

import ru.sbt.mipt.sensor.SensorEvent;

public class DeactivatedState implements AlarmState {
    private final Alarm alarm;
    DeactivatedState(Alarm alarm){
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        alarm.switchState(new ActivatedState(alarm));
        alarm.setCode(code);
    }

    @Override
    public void deactivate(String code) {
    }

    @Override
    public void switchToAnxietyMode() {
        alarm.switchState(new AnxietyState(alarm));
    }

    @Override
    public void reactToEvent(SensorEvent event) {
    }
}
