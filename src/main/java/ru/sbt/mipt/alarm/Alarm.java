package ru.sbt.mipt.alarm;

import ru.sbt.mipt.homeAndComponents.Action;
import ru.sbt.mipt.homeAndComponents.Actionable;
import ru.sbt.mipt.sensor.SensorEvent;

public class Alarm implements AlarmState, Actionable {
    private AlarmState state;

    public Alarm(AlarmState state){
        this.state = state;
    }

    public void switchState(AlarmState state) {
        this.state = state;
    }

    @Override
    public void activate() {
        state.activate();
    }

    @Override
    public void deactivate() {
        state.deactivate();
    }

    @Override
    public void switchToAnxietyMode() {
        state.switchToAnxietyMode();
    }

    @Override
    public boolean isDeactivated() {
        return state.isDeactivated();
    }

    @Override
    public void handle(SensorEvent event) {
        state.handle(event);
    }

    @Override
    public void execute(Action action) {
        action.perform(this);
    }
}
