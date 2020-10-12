package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Application implements HomeCreator{

    public static void main(String... args) throws Exception {
        // считываем состояние дома из файла
        SmartHome smartHome = createHome("smart-home-1.js");
        // начинаем цикл обработки событий
        ArrayList<Handler> handlers = new ArrayList<Handler>();
        handlers.add(new LightHandler(smartHome));
        handlers.add(new DoorHandler(smartHome));
        handlers.add(new HallHandler(smartHome, new CommandSender()));
        EventCircle eventCircle = new EventCircle(handlers, new EventProvider());
        eventCircle.run();
    }

    public static SmartHome createHome(String fileName) {
        Gson gson = new Gson();
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gson.fromJson(json, SmartHome.class);
    }
}
