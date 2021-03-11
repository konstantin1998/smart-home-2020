package ru.sbt.mipt.oop;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        String path = "smart-home-1.js";
        // считываем состояние дома из файла
        JsonSmartHomeStateProvider json = new JsonSmartHomeStateProvider(path);
        SmartHome smartHome = json.provideSmartHome();
        //создам обработчик событий и генератор событий
        EventHandler eventHandler = new EventHandler(smartHome);
        EventGenerator eventGenerator = new EventGenerator();
        // начинаем цикл обработки событий
        SmartHomeManager smartHomeManager = new SmartHomeManager(smartHome, eventHandler, eventGenerator);
        smartHomeManager.startTrackingEvents();
    }


}
