package ru.sbt.mipt.oop;

public class HallHandler implements Handler{
    private final SmartHome home;

    HallHandler(SmartHome home) {
        this.home = home;
    }

    public void handle(SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_CLOSED) {
            Action action = (Object obj) -> {
                if (obj instanceof Room) {
                    Room room = (Room)obj;
                    if (room.getName().equals("hall")) {
                        Action hallAction = (Object item) -> {
                            if (item instanceof Light) {
                                Light light = (Light) item;
                                light.setOn(false);
                                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                                CommandSender commandSender = new CommandSender();
                                commandSender.sendCommand(command);
                            }
                            if (item instanceof Door) {
                                Door door = (Door)item;
                                if (door.getId().equals(event.getObjectId())) {
                                    door.setOpen(false);
                                }
                            }
                        };
                        home.execute(hallAction);
                    }
                }
            };
            home.execute(action);
        }
    }
}
