package ru.sbt.mipt.oop;


public class SmartHomeManager {
    private SmartHome home;
    private EventHandler eventHandler;
    private EventGenerator eventGenerator;


    public SmartHomeManager(SmartHome home, EventHandler eventHandler, EventGenerator eventGenerator) {
        this.home = home;
        this.eventHandler = eventHandler;
        this.eventGenerator = eventGenerator;
    }

    public void startTrackingEvents(){
        Event event = eventGenerator.generateEvent();
        while (event != null) {
            eventHandler.handleEvent(event);
            event = eventGenerator.generateEvent();
        }
    }

}
