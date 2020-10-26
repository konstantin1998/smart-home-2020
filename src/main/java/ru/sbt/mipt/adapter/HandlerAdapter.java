package ru.sbt.mipt.adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.handlers.Handler;
import ru.sbt.mipt.sensor.SensorEvent;
import ru.sbt.mipt.sensor.SensorEventType;

import java.util.HashMap;

public class HandlerAdapter implements EventHandler {
    private final Handler handler;

    public HandlerAdapter(Handler handler) {
        this.handler = handler;
    }

    private SensorEvent translate(CCSensorEvent event) {
        HashMap<String, SensorEventType> factory = new HashMap<>();
        factory.put("LightIsOn", SensorEventType.LIGHT_ON);
        factory.put("LightIsOff", SensorEventType.LIGHT_OFF);
        factory.put("DoorIsOpen", SensorEventType.DOOR_OPEN);
        factory.put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        factory.put("DoorIsLocked", SensorEventType.DOOR_LOCKED);
        factory.put("DoorIsUnlocked", SensorEventType.DOOR_UNLOCKED);
        return new SensorEvent(factory.get(event.getEventType()), event.getObjectId());
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = translate(event);
        handler.handle(sensorEvent);
    }
}
