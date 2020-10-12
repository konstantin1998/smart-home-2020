package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.handlers.Action;
import ru.sbt.mipt.handlers.HallHandler;
import ru.sbt.mipt.homeAndComponents.Light;
import ru.sbt.mipt.homeAndComponents.SmartHome;
import ru.sbt.mipt.sensor.SensorEvent;
import ru.sbt.mipt.sensor.SensorEventType;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HallHandlerLightTest {
    private ArrayList<Boolean> expectedLightStates = new ArrayList<Boolean>();
    private ArrayList<Boolean> actualLightStates = new ArrayList<Boolean>();
    @Test
    public void handle() {
        //given
        HomeCreator homeCreator = new HomeCreator();
        SmartHome home = homeCreator.createHome();
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "3");
        HallHandler hallHandler = new HallHandler(home, new CommandSender());
        hallHandler.handle(event);
        //when
        Action action = (Object obj) -> {
            if (obj instanceof Light) {
                Light light = (Light)obj;
                expectedLightStates.add(false);
                actualLightStates.add(light.isOn());
            }
        };
        home.execute(action);
        //then
        assertArrayEquals(expectedLightStates.toArray(new Boolean[0]), actualLightStates.toArray(new Boolean[0]));
    }
}