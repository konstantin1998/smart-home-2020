package ru.sbt.mipt.oop;

import ru.sbt.mipt.alarm.Alarm;
import ru.sbt.mipt.handlers.AlarmHandler;
import ru.sbt.mipt.sensor.SensorEvent;
import ru.sbt.mipt.sensor.SensorEventType;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlarmHandlerTest {
    @Test
    public void activateActivatesAlarm() {
        //given
        Alarm alarm = new Alarm();
        AlarmHandler alarmHandler = new AlarmHandler(alarm);
        String id = "1";
        alarmHandler.handle(new SensorEvent(SensorEventType.ALARM_ACTIVATE, id, "123"));
        //when
        boolean isFalse = alarm.isDeactivated();
        //then
        assertFalse(isFalse);
    }

    @Test
    public void deactivateDeactivatesAlarm() {
        //given
        Alarm alarm = new Alarm();
        AlarmHandler alarmHandler = new AlarmHandler(alarm);
        String id = "1";
        String code = "123";
        alarm.activate(code);
        alarmHandler.handle(new SensorEvent(SensorEventType.ALARM_DEACTIVATE, id, code));
        //when
        boolean isTrue = alarm.isDeactivated();
        //then
        assertTrue(isTrue);
    }
}
