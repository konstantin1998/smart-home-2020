package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Application {

    public static void main(String... args) throws Exception {
        // считываем состояние дома из файла
        SmartHome smartHome = createHome("smart-home-1.js");
        // начинаем цикл обработки событий
        ArrayList<Handler> handlers = new ArrayList<Handler>();
        handlers.add(new LightHandler(smartHome));
        handlers.add(new DoorHandler(smartHome));
        handlers.add(new HallHandler(smartHome));
        EventCircle eventCircle = new EventCircle(handlers);
        eventCircle.run();
    }

    private static SmartHome createHome(String fileName) throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(fileName)));
        return gson.fromJson(json, SmartHome.class);
    }
}
