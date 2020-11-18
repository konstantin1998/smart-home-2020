package ru.sbt.mipt.handlers;

import ru.sbt.mipt.homeAndComponents.*;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.sensor.SensorCommand;
import ru.sbt.mipt.sensor.SensorEvent;
import ru.sbt.mipt.sensor.SensorEventType;

public class HallHandler implements Handler {
    private final SmartHome home;
    private final CommandSender commandSender;

    public HallHandler(SmartHome home, CommandSender commandSender) {
        this.home = home;
        this.commandSender = commandSender;
    }

    @Override
    public void handle(SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_CLOSED) {
            home.execute((Object obj) -> {
                if (obj instanceof Room) {
                    Room room = (Room)obj;
                    if (room.getName().equals("hall")) {
                        Action hallAction = (Object item) -> {
                            if (item instanceof Light) {
                                Light light = (Light) item;
                                light.setOn(false);
                                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
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
            });
        }
    }
}
