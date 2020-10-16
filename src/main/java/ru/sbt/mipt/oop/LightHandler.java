package ru.sbt.mipt.oop;

public class LightHandler implements Handler{
    private final SmartHome home;

    LightHandler(SmartHome home) {
        this.home = home;
    }

    public void handle(SensorEvent event) {
        if (event.getType() == SensorEventType.LIGHT_ON) {
            Action action = (Object obj) -> {
                if (obj instanceof Light) {
                    Light light = (Light)obj;
                    if (light.getId().equals(event.getObjectId())) {
                        light.setOn(true);
                    }
                }
            };
            home.execute(action);
        }

        if (event.getType() == SensorEventType.LIGHT_OFF) {
            Action action = (Object obj) -> {
                if (obj instanceof Light) {
                    Light light = (Light)obj;
                    if (light.getId().equals(event.getObjectId())) {
                        light.setOn(false);
                    }
                }
            };
            home.execute(action);
        }
    }
}
