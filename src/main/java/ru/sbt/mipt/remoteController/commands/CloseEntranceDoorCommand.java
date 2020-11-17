package ru.sbt.mipt.remoteController.commands;

import ru.sbt.mipt.handlers.Handler;
import ru.sbt.mipt.sensor.SensorEvent;
import ru.sbt.mipt.sensor.SensorEventType;

import java.util.Collection;

public class CloseEntranceDoorCommand implements Command {
    private final Collection<Handler> handlers;

    public CloseEntranceDoorCommand(Collection<Handler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public void execute() {
        SensorEvent event = new SensorEvent(SensorEventType.ENTRANCE_DOOR_CLOSED);
        for (Handler handler : handlers) {
            handler.handle(event);
        }
    }
}