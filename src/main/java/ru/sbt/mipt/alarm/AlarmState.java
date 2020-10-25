package ru.sbt.mipt.alarm;
import ru.sbt.mipt.sensor.SensorEvent;

public interface AlarmState {
    void activate(String code);
    void deactivate(String code);
    void switchToAnxietyMode();
}
