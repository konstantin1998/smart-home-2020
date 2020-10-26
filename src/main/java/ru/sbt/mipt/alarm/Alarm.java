package ru.sbt.mipt.alarm;

import ru.sbt.mipt.homeAndComponents.Action;
import ru.sbt.mipt.homeAndComponents.Actionable;
import ru.sbt.mipt.sensor.SensorEvent;

public class Alarm implements Actionable {
    private AlarmState state;
    private String code;

    public Alarm() {
        state = new DeactivatedState(this);
        code = "";
    }

    void setCode(String code) {
        this.code = code;
    }

    String getCode() {
        return code;
    }

    void switchState(AlarmState state) {
        this.state = state;
    }

    public void activate(String code) {
        sendSMS();
        state.activate(code);
    }

    public void deactivate(String code) {
        state.deactivate(code);
    }

    public void switchToAnxietyMode() {
        state.switchToAnxietyMode();
    }

    public boolean isDeactivated() {
        return state instanceof DeactivatedState;
    }

    private void sendSMS() {
        System.out.println("Sending sms");
    }

    @Override
    public void execute(Action action) {
        action.perform(this);
    }
}
