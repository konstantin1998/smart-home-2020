package ru.sbt.mipt.oop;

import ru.sbt.mipt.handlers.DoorHandler;
import ru.sbt.mipt.handlers.HallHandler;
import ru.sbt.mipt.handlers.Handler;
import ru.sbt.mipt.handlers.LightHandler;
import ru.sbt.mipt.homeAndComponents.SmartHome;

import java.util.ArrayList;

public class Application{

    public static void main(String... args) throws Exception {
        // считываем состояние дома из файла
        SmartHome smartHome = HomeReader.createHome("smart-home-1.js");
        // начинаем цикл обработки событий
        ArrayList<Handler> handlers = new ArrayList<Handler>();
        handlers.add(new LightHandler(smartHome));
        handlers.add(new DoorHandler(smartHome));
        handlers.add(new HallHandler(smartHome, new CommandSender()));
        EventCircle eventCircle = new EventCircle(handlers, new EventProvider());
        eventCircle.run();
    }

}

