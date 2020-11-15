package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.alarm.Alarm;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlarmTest {
    @Test
    public void activateChecksIfAlarmIsDeactivated() {
        //given
        Alarm alarm = new Alarm();
        String code = "123";
        alarm.activate(code);
        //when
        boolean isFalse = alarm.isDeactivated();
        //then
        assertFalse(isFalse);
    }

    @Test
    public void deactivateWithWrongCodeChecksIfAlarmIsDeactivated() {
        //given
        Alarm alarm = new Alarm();
        String code = "123";
        alarm.activate(code);
        alarm.deactivate("321");
        //when
        boolean isFalse = alarm.isDeactivated();
        //then
        assertFalse(isFalse);
    }

    @Test
    public void deactivateWithRightCodeChecksIfAlarmIsDeactivated() {
        //given
        Alarm alarm = new Alarm();
        String code = "123";
        alarm.activate(code);
        alarm.deactivate(code);
        //when
        boolean isTrue = alarm.isDeactivated();
        //then
        assertTrue(isTrue);
    }

    @Test
    public void switchToAnxietyModeChecksIfAlarmIsDeactivated() {
        //given
        Alarm alarm = new Alarm();
        String code = "123";
        alarm.activate(code);
        alarm.switchToAnxietyMode();
        //when
        boolean isFalse = alarm.isDeactivated();
        //then
        assertFalse(isFalse);
    }
}
