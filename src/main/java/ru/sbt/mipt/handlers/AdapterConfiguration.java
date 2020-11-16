package ru.sbt.mipt.handlers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.sensor.SensorEventType;

import java.util.HashMap;

@Configuration
public class AdapterConfiguration {
    @Bean(name = "factory")
    public HashMap<String, SensorEventType> getFactory() {
        HashMap<String, SensorEventType> factory = new HashMap<>();
        factory.put("LightIsOn", SensorEventType.LIGHT_ON);
        factory.put("LightIsOff", SensorEventType.LIGHT_OFF);
        factory.put("DoorIsOpen", SensorEventType.DOOR_OPEN);
        factory.put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        factory.put("DoorIsLocked", SensorEventType.DOOR_LOCKED);
        factory.put("DoorIsUnlocked", SensorEventType.DOOR_UNLOCKED);
        return factory;
    }
}
