package ru.sbt.mipt.oop;

public class LightHandler implements Handler{
    private final SmartHome home;

    LightHandler(SmartHome home) {
        this.home = home;
    }

    private void enable(Light light, Room room) {
        light.setOn(true);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
    }

    private void disable(Light light, Room room) {
        light.setOn(false);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
    }

    public void handle(SensorEvent event) {
        if (event.getType() == SensorEventType.LIGHT_ON || event.getType() == SensorEventType.LIGHT_OFF) {
            for (Room room : home.getRooms()) {
                for (Light light : room.getLights()) {
                    if (light.getId().equals(event.getObjectId())) {
                        if (event.getType() == SensorEventType.LIGHT_ON) {
                            enable(light, room);
                        }
                        if (event.getType() == SensorEventType.LIGHT_OFF) {
                            disable(light, room);
                        }
                    }
                }
            }
        }
    }
}
