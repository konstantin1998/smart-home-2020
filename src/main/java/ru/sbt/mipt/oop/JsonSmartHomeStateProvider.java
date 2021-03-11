package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonSmartHomeStateProvider implements SmartHomeProvider {
    String path;

    public JsonSmartHomeStateProvider(String path) {
        this.path = path;
    }


    @Override
    public SmartHome provideSmartHome() {
        Gson gson = new Gson();
        String json;
        try {
            json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));
        }catch (IOException e){
            System.out.println("State file not founded, returned new SmartHome");
            return new SmartHome();
        }
        SmartHome smartHome = gson.fromJson(json, SmartHome.class);
        setHomeToAll(smartHome);
        return smartHome;
    }

    @Override
    public void setHomeToAll(SmartHome smartHome) {
        for (Room room:smartHome.getRooms()){
            for (Door door: room.getDoors()){
                door.setHome(smartHome);
            }
            for (Light light:room.getLights()){
                light.setHome(smartHome);
            }
        }
    }


}
