package ru.sbt.mipt.oop;

import ru.sbt.mipt.alarm.Alarm;
import ru.sbt.mipt.handlers.*;
import ru.sbt.mipt.homeAndComponents.SmartHome;

import java.util.ArrayList;

public class Application{

    public static void main(String... args) throws Exception {
        // считываем состояние дома из файла
        SmartHome smartHome = HomeReader.createHome("smart-home-1.js");
        // начинаем цикл обработки событий
        ArrayList<Handler> handlers = new ArrayList<Handler>();
        Alarm alarm = new Alarm();
        handlers.add(new AlarmDecorator(alarm, new LightHandler(smartHome)));
        handlers.add(new AlarmDecorator(alarm, new DoorHandler(smartHome)));
        handlers.add(new AlarmDecorator(alarm, new HallHandler(smartHome, new CommandSender())));
        EventCircle eventCircle = new EventCircle(handlers, new EventProvider());
        eventCircle.run();
    }
}

