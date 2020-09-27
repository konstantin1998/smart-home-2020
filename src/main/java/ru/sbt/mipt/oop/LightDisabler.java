package ru.sbt.mipt.oop;

public class LightDisabler {
    private final String id;
    private final SmartHome home;
    LightDisabler(String id, SmartHome home) {
        this.id = id;
        this.home = home;

    }
    public void disableLight() {
        for (Room room : home.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(id)) {
                    light.setOn(false);
                    System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                }
            }
        }
    }

    public void disableAll() {
        for (Room homeRoom : home.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                sendCommand(command);
            }
        }
    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
