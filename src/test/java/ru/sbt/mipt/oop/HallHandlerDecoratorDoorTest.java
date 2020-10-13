package ru.sbt.mipt.oop;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import ru.sbt.mipt.alarm.Alarm;
import ru.sbt.mipt.decoratedHandlers.DoorHandlerDecorator;
import ru.sbt.mipt.decoratedHandlers.HallHandlerDecorator;
import ru.sbt.mipt.handlers.DoorHandler;
import ru.sbt.mipt.handlers.HallHandler;
import ru.sbt.mipt.homeAndComponents.Action;
import ru.sbt.mipt.homeAndComponents.Door;
import ru.sbt.mipt.homeAndComponents.SmartHome;
import ru.sbt.mipt.sensor.SensorEvent;
import ru.sbt.mipt.sensor.SensorEventType;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HallHandlerDecoratorDoorTest {
    private Door testDoor;
    private SmartHome home;
    private SensorEvent event;

    private void init() {
        HomeCreator homeCreator = new HomeCreator();
        home = homeCreator.createHome();
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "3");
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
    public void closeChecksIfDoorIsClosed() {
        //given
        init();
        Alarm alarm = new Alarm();
        String code = "123";
        alarm.activate(code);
        HallHandler hallHandler = new HallHandler(home, new CommandSender());
        HallHandlerDecorator hallHandlerDecorator = new HallHandlerDecorator(alarm, hallHandler);
        hallHandlerDecorator.handle(event);
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
        DoorHandlerDecorator doorHandlerDecorator = new DoorHandlerDecorator(alarm, doorHandler);
        doorHandlerDecorator.handle(event);
        //when
        boolean isFalse = testDoor.isOpen();
        //then
        Assertions.assertFalse(isFalse);
    }

    @Test
    public void closeClosesDoorWhenAlarmIsInAnxiety() {
        //given
        init();
        Alarm alarm = new Alarm();
        alarm.switchToAnxietyMode();
        DoorHandler doorHandler = new DoorHandler(home);
        DoorHandlerDecorator doorHandlerDecorator = new DoorHandlerDecorator(alarm, doorHandler);
        doorHandlerDecorator.handle(event);
        //when
        boolean isTrue = testDoor.isOpen();
        //then
        assertTrue(isTrue);
    }
}
