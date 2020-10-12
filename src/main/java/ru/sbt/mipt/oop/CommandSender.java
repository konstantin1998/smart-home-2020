package ru.sbt.mipt.oop;

import ru.sbt.mipt.sensor.SensorCommand;

public class CommandSender {
    public void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
