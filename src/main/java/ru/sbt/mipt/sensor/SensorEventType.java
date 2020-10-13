package ru.sbt.mipt.sensor;

public enum SensorEventType {
    LIGHT_ON, LIGHT_OFF, DOOR_OPEN, DOOR_CLOSED, ALARM_ACTIVATE("123"), ALARM_DEACTIVATE("123");
    private final String code;

    SensorEventType(String code) {
        this.code = code;
    }

    SensorEventType() {
        this.code = "";
    }

    public String getCode(){ return code;}
}
