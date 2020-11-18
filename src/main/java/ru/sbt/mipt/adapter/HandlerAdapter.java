package ru.sbt.mipt.adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.sbt.mipt.alarm.Alarm;
import ru.sbt.mipt.handlers.AdapterConfiguration;
import ru.sbt.mipt.handlers.Handler;
import ru.sbt.mipt.oop.AppConfiguration;
import ru.sbt.mipt.sensor.SensorEvent;
import ru.sbt.mipt.sensor.SensorEventType;

import java.util.HashMap;
import java.util.Map;

public class HandlerAdapter implements EventHandler {
    private final Handler handler;
    private final Map<String, SensorEventType> factory;


    public HandlerAdapter(Handler handler, Map<String, SensorEventType> factory) {
        this.handler = handler;
        this.factory = factory;
    }

    private SensorEvent translate(CCSensorEvent event) {
        if (factory.containsKey(event.getEventType())) {
            return new SensorEvent(factory.get(event.getEventType()), event.getObjectId());
        } else {
            throw new IllegalArgumentException("unknown event type");
        }
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = translate(event);
        handler.handle(sensorEvent);
    }
}
