package ru.sbt.mipt.oop;

public class LightEnabler {
    private final String id;
    private final SmartHome home;
    LightEnabler(String id, SmartHome home) {
        this.id = id;
        this.home = home;

    }
    public void enableLight() {
        for (Room room : home.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(id)) {
                    light.setOn(true);
                    System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                }
            }
        }
    }
}
