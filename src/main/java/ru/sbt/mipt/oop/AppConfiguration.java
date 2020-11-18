package ru.sbt.mipt.oop;
import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.adapter.HandlerAdapter;
import ru.sbt.mipt.alarm.Alarm;
import ru.sbt.mipt.handlers.*;
import ru.sbt.mipt.homeAndComponents.SmartHome;
import ru.sbt.mipt.sensor.SensorEventType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Configuration
public class AppConfiguration {

    @Bean
    public HashMap<String, SensorEventType> createFactory() {
        HashMap<String, SensorEventType> factory = new HashMap<>();
        factory.put("LightIsOn", SensorEventType.LIGHT_ON);
        factory.put("LightIsOff", SensorEventType.LIGHT_OFF);
        factory.put("DoorIsOpen", SensorEventType.DOOR_OPEN);
        factory.put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        factory.put("DoorIsLocked", SensorEventType.DOOR_LOCKED);
        factory.put("DoorIsUnlocked", SensorEventType.DOOR_UNLOCKED);
        return factory;
    }

    @Bean
    public Alarm createAlarm() {
        return new Alarm();
    }

    @Bean
    public SmartHome createHome () {
        return HomeReader.createHome("smart-home-1.js");
    }

    @Bean
    public HandlerAdapter lightHandler(SmartHome home, Alarm alarm, HashMap<String, SensorEventType> factory) {
        return new HandlerAdapter(new AlarmDecorator(alarm, new LightHandler(home)), factory);
    }

    @Bean
    public HandlerAdapter doorHandler(SmartHome home, Alarm alarm, HashMap<String, SensorEventType> factory) {
        return new HandlerAdapter(new AlarmDecorator(alarm, new DoorHandler(home)), factory);
    }

    @Bean
    public HandlerAdapter doorLockHandler(SmartHome home, Alarm alarm, HashMap<String, SensorEventType> factory) {
        return new HandlerAdapter(new AlarmDecorator(alarm, new DoorLockHandler(home)), factory);
    }

    @Bean
    public HandlerAdapter hallHandler(SmartHome home, Alarm alarm, HashMap<String, SensorEventType> factory) {
        return new HandlerAdapter(new AlarmDecorator(alarm, new HallHandler(home, new CommandSender())), factory);
    }

    @Bean
    public HandlerAdapter alarmHandler(Alarm alarm, HashMap<String, SensorEventType> factory) {
        return new HandlerAdapter(new AlarmHandler(alarm), factory);
    }

    @Bean
    public HandlerAdapter hallLightHandler(SmartHome home, Alarm alarm, HashMap<String, SensorEventType> factory) {
        return new HandlerAdapter(new AlarmDecorator(alarm, new HallLightEnabler(home)), factory);
    }

    @Bean
    public HandlerAdapter entranceDoorCloser(SmartHome home, Alarm alarm, HashMap<String, SensorEventType> factory) {
        return new HandlerAdapter(new AlarmDecorator(alarm, new EntranceDoorCloser(home)), factory);
    }

    @Bean
    public HandlerAdapter lightsDisabler(SmartHome home, Alarm alarm, HashMap<String, SensorEventType> factory) {
        return new HandlerAdapter(new AlarmDecorator(alarm, new LightsDisabler(home)), factory);
    }

    @Bean
    public HandlerAdapter lightsEnabler(SmartHome home, Alarm alarm, HashMap<String, SensorEventType> factory) {
        return new HandlerAdapter(new AlarmDecorator(alarm, new LightsEnabler(home)), factory);
    }

    @Bean
    public SensorEventsManager createEventManager(Collection<HandlerAdapter> handlers) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        for (HandlerAdapter handler : handlers) {
            sensorEventsManager.registerEventHandler(handler);
        }
        return sensorEventsManager;
    }


}
