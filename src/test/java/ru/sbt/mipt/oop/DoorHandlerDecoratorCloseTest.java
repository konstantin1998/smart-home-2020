package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.alarm.Alarm;
import ru.sbt.mipt.handlers.Decorator;
import ru.sbt.mipt.handlers.DoorHandler;
import ru.sbt.mipt.homeAndComponents.Action;
import ru.sbt.mipt.homeAndComponents.Door;
import ru.sbt.mipt.homeAndComponents.SmartHome;
import ru.sbt.mipt.sensor.SensorEvent;
import ru.sbt.mipt.sensor.SensorEventType;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DoorHandlerDecoratorCloseTest {
    private Door testDoor;
    private SmartHome home;
    private SensorEvent event;

    private void init() {
        HomeCreator homeCreator = new HomeCreator();
        home = homeCreator.createHome();
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        this.event = event;
        Action action = (Object obj) -> {
            if (obj instanceof Door) {
                Door door = (Door)obj;
                if (door.getId().equals(event.getObjectId())) {
                    testDoor = door;
                }
            }
        };
        home.execute(action);
    }

    @Test
    public void closeClosesDoorWhenAlarmIsActivated() {
        //given
        init();
        Alarm alarm = new Alarm();
        String code = "123";
        alarm.activate(code);
        DoorHandler doorHandler = new DoorHandler(home);
        Decorator decorator = new Decorator(alarm, doorHandler);
        decorator.handle(event);
        //when
        boolean isTrue = testDoor.isOpen();
        //then
        assertTrue(isTrue);
    }

    @Test
    public void closeClosesDoorWhenAlarmIsDeactivated() {
        //given
        init();
        Alarm alarm = new Alarm();
        DoorHandler doorHandler = new DoorHandler(home);
        Decorator decorator = new Decorator(alarm, doorHandler);
        decorator.handle(event);
        //when
        boolean isFalse = testDoor.isOpen();
        //then
        assertFalse(isFalse);
    }

    @Test
    public void closeClosesDoorWhenAlarmIsInAnxiety() {
        //given
        init();
        Alarm alarm = new Alarm();
        alarm.switchToAnxietyMode();
        DoorHandler doorHandler = new DoorHandler(home);
        Decorator decorator = new Decorator(alarm, doorHandler);
        decorator.handle(event);
        //when
        boolean isTrue = testDoor.isOpen();
        //then
        assertTrue(isTrue);
    }
}
