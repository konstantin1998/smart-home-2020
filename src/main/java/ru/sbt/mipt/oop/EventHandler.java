package ru.sbt.mipt.oop;

public class EventHandler {
    private final SensorEvent event;
    private final SmartHome home;
    EventHandler(SensorEvent event, SmartHome home) {
        this.event = event;
        this.home = home;
    }
    public void handleEvent() throws Exception {
        System.out.println("Got event: " + event);
        switch (event.getType()) {
            case LIGHT_ON:
                LightEnabler lightEnabler = new LightEnabler(event.getObjectId(), home);
                lightEnabler.enableLight();
                break;
            case LIGHT_OFF:
                LightDisabler lightDisabler = new LightDisabler(event.getObjectId(), home);
                lightDisabler.disableLight();
                break;
            case DOOR_OPEN:
                DoorOpener doorOpener = new DoorOpener(event.getObjectId(), home);
                doorOpener.openDoor();
                break;
            case DOOR_CLOSED:
                DoorCloser doorCloser = new DoorCloser(event.getObjectId(), home);
                doorCloser.closeDoor();
                break;
            default:
                throw new Exception("Unknown event");
        }
    }
}
