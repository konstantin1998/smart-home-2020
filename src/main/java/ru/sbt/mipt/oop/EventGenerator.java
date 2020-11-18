package ru.sbt.mipt.oop;

import ru.sbt.mipt.sensor.SensorEvent;

public interface EventGenerator {
    SensorEvent getNextSensorEvent();
}

