package ru.sbt.mipt.decoratedHandlers;

import ru.sbt.mipt.alarm.Alarm;
import ru.sbt.mipt.handlers.HallHandler;
import ru.sbt.mipt.handlers.Handler;
import ru.sbt.mipt.homeAndComponents.SmartHome;
import ru.sbt.mipt.oop.CommandSender;
import ru.sbt.mipt.sensor.SensorEvent;

public class HallHandlerDecorator implements Handler {
    private final Alarm alarm;
    private final HallHandler hallHandler;

    public HallHandlerDecorator(SmartHome home, Alarm alarm, CommandSender commandSender) {
        this.alarm = alarm;
        hallHandler = new HallHandler(home, commandSender);
    }

    @Override
    public void handle(SensorEvent event) {
        alarm.handle(event);
        if (alarm.isDeactivated()) {
            hallHandler.handle(event);
        }
    }
}
