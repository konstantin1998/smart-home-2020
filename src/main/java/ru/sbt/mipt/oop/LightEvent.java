package ru.sbt.mipt.oop;

public class LightEvent extends Event implements GetStateToChange {
    private LightTypeEvent type;

    public LightEvent(String id, LightTypeEvent type) {
        super(id);
        this.type = type;
    }


    @Override
    public States getState() {
        return type.getState();
    }

    @Override
    public String getType() {
        return type.toString();
    }
}
