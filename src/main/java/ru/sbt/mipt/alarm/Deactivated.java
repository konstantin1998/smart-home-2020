package ru.sbt.mipt.alarm;

import ru.sbt.mipt.homeAndComponents.Action;
import ru.sbt.mipt.sensor.SensorEvent;

public class Deactivated implements AlarmState {
    private final Alarm alarm;
    Deactivated(Alarm alarm){
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        alarm.switchState(new Activated(alarm));
        alarm.setCode(code);
    }

    @Override
    public void deactivate(String code) {
    }

    @Override
    public void switchToAnxietyMode() {
        alarm.switchState(new Anxiety(alarm));
    }

    @Override
    public boolean isDeactivated() {
        return true;
    }

    @Override
    public void handle(SensorEvent event) {
    }
}
