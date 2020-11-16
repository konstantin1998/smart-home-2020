package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.EventHandler;
import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
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
    public Alarm alarm() {
        return new Alarm();
    }

    @Bean
    public SmartHome createHome() {
        return HomeReader.createHome("smart-home-1.js");
    }

    @Bean
    public EventHandler lightHandler(SmartHome home, HashMap<String, SensorEventType> factory) {
        return new HandlerAdapter(decorate(new LightHandler(home)), factory);
    }

    @Bean
    public EventHandler doorHandler(SmartHome home, HashMap<String, SensorEventType> factory) {
        return new HandlerAdapter(decorate(new DoorHandler(home)), factory);
    }

    @Bean
    public EventHandler doorLockHandler(SmartHome home, HashMap<String, SensorEventType> factory) {
        return new HandlerAdapter(decorate(new DoorLockHandler(home)), factory);
    }

    @Bean
    public EventHandler hallHandler(SmartHome home, HashMap<String, SensorEventType> factory) {
        return new HandlerAdapter(decorate(new HallHandler(home, new CommandSender())), factory);
    }

    @Bean
    public SensorEventsManager createEventManager(Collection<EventHandler> handlers) {

        SensorEventsManager sensorEventsManager = new SensorEventsManager();

        for (EventHandler handler : handlers) {
            sensorEventsManager.registerEventHandler(handler);
        }
        return sensorEventsManager;
    }

    private AlarmDecorator decorate(Handler handler) {
        return new AlarmDecorator(alarm(), handler, new SMSSender());
    }
}
