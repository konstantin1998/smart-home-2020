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

    @Bean
    public Alarm alarm() {
        return new Alarm();
    }

    @Bean
    public SmartHome getHome() {
        return HomeReader.createHome("smart-home-1.js");
    }

    @Bean
    public EventHandler lightHandler(SmartHome home) {
        return new HandlerAdapter(decorate(new LightHandler(home)));
    }

    @Bean
    public EventHandler doorHandler(SmartHome home) {
        return new HandlerAdapter(decorate(new DoorHandler(home)));
    }

    @Bean
    public EventHandler doorLockHandler(SmartHome home) {
        return new HandlerAdapter(decorate(new DoorLockHandler(home)));
    }

    @Bean
    public EventHandler hallHandler(SmartHome home) {
        return new HandlerAdapter(decorate(new HallHandler(home, new CommandSender())));
    }

    @Bean
    public SensorEventsManager getEventManager(Collection<EventHandler> handlers) {

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
