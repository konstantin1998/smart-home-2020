package ru.sbt.mipt.alarm;

import ru.sbt.mipt.homeAndComponents.Action;
import ru.sbt.mipt.homeAndComponents.Actionable;
import ru.sbt.mipt.sensor.SensorEvent;

public class Alarm implements AlarmState, Actionable {
    private AlarmState state;
    private String code;

//    public Alarm(AlarmState state){
//        this.state = state;
//        code = "";
//    }

    public Alarm() {
        state = new Deactivated(this);
        code = "";
    }

    void setCode(String code) {
        this.code = code;
    }

    String getCode() {
        return code;
    }

    public void switchState(AlarmState state) {
        this.state = state;
    }

    @Override
    public void activate(String code) {
        state.activate(code);
    }

    @Override
    public void deactivate(String code) {
        state.deactivate(code);
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
