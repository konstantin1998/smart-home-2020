package ru.sbt.mipt.remoteController;

import rc.RemoteControl;
import ru.sbt.mipt.handlers.Handler;
import ru.sbt.mipt.sensor.SensorEvent;
import ru.sbt.mipt.sensor.SensorEventType;

import java.util.Collection;
import java.util.HashMap;

public class RemoteController implements RemoteControl {
    private Collection<Handler> handlers;
    private final String rcId;
    private HashMap<String, SensorEvent> buttonsToEvents;

    public RemoteController(Collection<Handler> handlers, String rcId) {
        this.handlers = handlers;
        this.rcId = rcId;
        buttonsToEvents = new HashMap<>();
    }

    public void setAllLightsDisablingButton(String buttonId) {
        buttonsToEvents.put(buttonId, new SensorEvent(SensorEventType.LIGHT_DISABLE_ALL));
    }

    public void setAllLightsEnablingButton(String buttonId) {
        buttonsToEvents.put(buttonId, new SensorEvent(SensorEventType.LIGHT_ENABLE_ALL));
    }

    public void setClosingEntranceDoorButton(String buttonId) {
        buttonsToEvents.put(buttonId, new SensorEvent(SensorEventType.ENTRANCE_DOOR_CLOSED));
    }

    public void setEnablingLightInHallButton(String buttonId) {
        buttonsToEvents.put(buttonId, new SensorEvent(SensorEventType.LIGHT_ENABLE_HALL));
    }

    public void setTriggeringAlarmButton(String buttonId) {
        buttonsToEvents.put(buttonId, new SensorEvent(SensorEventType.ALARM_TRIGGER));
    }

    public void setActivatingAlarmButton(String buttonId) {
        buttonsToEvents.put(buttonId, new SensorEvent(SensorEventType.ALARM_ACTIVATE));
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (this.rcId.equals(rcId)) {
            SensorEvent event = buttonsToEvents.get(buttonCode);
            if (event != null) {
                for (Handler handler : handlers) {
                    handler.handle(event);
                }
            }
        }
    }
}
