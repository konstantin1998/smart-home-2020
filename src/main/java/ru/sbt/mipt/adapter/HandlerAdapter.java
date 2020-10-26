package ru.sbt.mipt.adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.handlers.Handler;
import ru.sbt.mipt.sensor.SensorEvent;
import ru.sbt.mipt.sensor.SensorEventType;

public class HandlerAdapter implements EventHandler {
    private final Handler handler;

    public HandlerAdapter(Handler handler) {
        this.handler = handler;
    }

    private SensorEvent translate(CCSensorEvent event) {
        SensorEventType type;
        switch (event.getEventType()) {
            case "LightIsOn":
                type = SensorEventType.LIGHT_ON;
                break;
            case "LightIsOff":
                type = SensorEventType.LIGHT_OFF;
                break;
            case "DoorIsOpen":
                type = SensorEventType.DOOR_OPEN;
                break;
            case "DoorIsClosed":
                type = SensorEventType.DOOR_CLOSED;
                break;
            case "DoorIsLocked":
                type = SensorEventType.DOOR_LOCKED;
                break;
            case "DoorIsUnlocked":
                type = SensorEventType.DOOR_UNLOCKED;
                break;
            default:
                throw new IllegalArgumentException("unknown event type");
        }
        return new SensorEvent(type, event.getObjectId());
    }
    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = translate(event);
        handler.handle(sensorEvent);
    }
}
