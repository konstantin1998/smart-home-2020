package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    public static void main(String... args) throws Exception {
        // считываем состояние дома из файла
        SmartHome smartHome = createHome("smart-home-1.js");
        // начинаем цикл обработки событий
        SensorEvent event = getNextSensorEvent();

        while (event != null) {
            EventHandler eventHandler = new EventHandler(event, smartHome);
            eventHandler.handleEvent();
            event = getNextSensorEvent();
        }
    }

    private static SmartHome createHome(String fileName) throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(fileName)));
        return gson.fromJson(json, SmartHome.class);
    }

    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
