package ru.sbt.mipt.oop;

import ru.sbt.mipt.sensor.SensorEventType;

import java.util.HashMap;

public class Factory {
    private final HashMap<String, SensorEventType> factory;

    Factory() {
        factory = new HashMap<>();
        factory.put("LightIsOn", SensorEventType.LIGHT_ON);
        factory.put("LightIsOff", SensorEventType.LIGHT_OFF);
        factory.put("DoorIsOpen", SensorEventType.DOOR_OPEN);
        factory.put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        factory.put("DoorIsLocked", SensorEventType.DOOR_LOCKED);
        factory.put("DoorIsUnlocked", SensorEventType.DOOR_UNLOCKED);
    }

    public HashMap<String, SensorEventType> getFactory() {
        return factory;
    }
}
