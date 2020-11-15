package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import ru.sbt.mipt.homeAndComponents.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HomeReader implements HomeLoader{

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
