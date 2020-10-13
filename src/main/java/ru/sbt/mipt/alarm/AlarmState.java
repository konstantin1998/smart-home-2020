package ru.sbt.mipt.alarm;
import ru.sbt.mipt.sensor.SensorEvent;

public interface AlarmState {
    void activate();
    void deactivate();
    void switchToAnxietyMode();
    boolean isDeactivated();
    void handle(SensorEvent event);
}
