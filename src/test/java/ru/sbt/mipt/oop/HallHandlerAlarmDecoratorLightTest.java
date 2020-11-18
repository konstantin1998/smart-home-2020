package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.alarm.Alarm;
import ru.sbt.mipt.handlers.AlarmDecorator;
import ru.sbt.mipt.handlers.HallHandler;
import ru.sbt.mipt.homeAndComponents.Action;
import ru.sbt.mipt.homeAndComponents.Light;
import ru.sbt.mipt.homeAndComponents.SmartHome;
import ru.sbt.mipt.sensor.SensorEvent;
import ru.sbt.mipt.sensor.SensorEventType;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class HallHandlerAlarmDecoratorLightTest {
    private final ArrayList<Boolean> expectedLightStates = new ArrayList<Boolean>();
    private final ArrayList<Boolean> actualLightStates = new ArrayList<Boolean>();
    private SmartHome home;
    private SensorEvent event;

    private void init() {
        HomeCreator homeCreator = new HomeCreator();
        home = homeCreator.createHome();
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "3");
        this.event = event;
    }

    @Test
    public void turnOffWhenAlarmIsActivated() {
        //given
        init();
        HallHandler hallHandler = new HallHandler(home, new CommandSender());
        Alarm alarm = new Alarm();
        String code = "123";
        alarm.activate(code);
        AlarmDecorator alarmDecorator = new AlarmDecorator(alarm, hallHandler);
        alarmDecorator.handle(event);

        Action action = (Object obj) -> {
            if (obj instanceof Light) {
                Light light = (Light)obj;
                expectedLightStates.add(false);
                actualLightStates.add(light.isOn());
            }
        };
        home.execute(action);
        //when
        boolean isFalse = true;
        for (int i = 0; i < expectedLightStates.size(); i++) {
            isFalse = isFalse && (expectedLightStates.get(i) == actualLightStates.get(i));
        }
        //then
        assertFalse(isFalse);
    }

    @Test
    public void turnOffWhenAlarmIsDeactivated() {
        //given
        init();
        HallHandler hallHandler = new HallHandler(home, new CommandSender());
        Alarm alarm = new Alarm();
        AlarmDecorator alarmDecorator = new AlarmDecorator(alarm, hallHandler);
        alarmDecorator.handle(event);
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

    @Test
    public void turnOffWhenAlarmIsInAnxiety() {
        //given
        init();
        HallHandler hallHandler = new HallHandler(home, new CommandSender());
        Alarm alarm = new Alarm();

        alarm.switchToAnxietyMode();
        AlarmDecorator alarmDecorator = new AlarmDecorator(alarm, hallHandler);
        alarmDecorator.handle(event);

        Action action = (Object obj) -> {
            if (obj instanceof Light) {
                Light light = (Light)obj;
                expectedLightStates.add(false);
                actualLightStates.add(light.isOn());
            }
        };
        home.execute(action);
        //when
        boolean isFalse = true;
        for (int i = 0; i < expectedLightStates.size(); i++) {
            isFalse = isFalse && (expectedLightStates.get(i) == actualLightStates.get(i));
        }
        //then
        assertFalse(isFalse);
    }

}
