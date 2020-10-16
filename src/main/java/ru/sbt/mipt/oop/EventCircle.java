package ru.sbt.mipt.oop;

import java.util.Collection;

public class EventCircle {
    private final Collection<Handler> handlers;
    private final EventGenerator eventGenerator;


    EventCircle(Collection<Handler> handlers, EventGenerator eventGenerator) {
        this.handlers = handlers;
        this.eventGenerator = eventGenerator;
    }

    private void handleSingleEvent(SensorEvent event) {
        System.out.println("Got event: " + event);
        for (Handler handler : handlers) {
            handler.handle(event);
        }
    }

    public void run() {
        SensorEvent event = eventGenerator.getNextSensorEvent();
        while (event != null) {
            handleSingleEvent(event);
            event = eventGenerator.getNextSensorEvent();
        }
    }

}
