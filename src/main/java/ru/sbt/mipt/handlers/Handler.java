package ru.sbt.mipt.handlers;

import ru.sbt.mipt.sensor.SensorEvent;

public interface Handler {
    void handle(SensorEvent event);
}
