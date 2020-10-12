package ru.sbt.mipt.homeAndComponents;

import ru.sbt.mipt.handlers.Action;

public class Light implements Actionable {
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public String getId() {
        return id;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public void execute(Action action) {
        action.perform(this);
    }
}
