package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.alarm.Alarm;
import ru.sbt.mipt.decoratedHandlers.LightHandlerDecorator;
import ru.sbt.mipt.handlers.LightHandler;
import ru.sbt.mipt.homeAndComponents.Action;
import ru.sbt.mipt.homeAndComponents.Light;
import ru.sbt.mipt.homeAndComponents.SmartHome;
import ru.sbt.mipt.sensor.SensorEvent;
import ru.sbt.mipt.sensor.SensorEventType;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LightHandlerDecoratorTurnOnTest {
    private Light testLight;
    private SmartHome home;
    private SensorEvent event;

    private void init() {
        HomeCreator homeCreator = new HomeCreator();
        home = homeCreator.createHome();
        event = new SensorEvent(SensorEventType.LIGHT_ON, "2");
        Action action = (Object obj) -> {
            if (obj instanceof Light) {
                Light light = (Light)obj;
                if (light.getId().equals(event.getObjectId())) {
                    testLight = light;
                }
            }
        };
        home.execute(action);
    }

    @Test
    public void turnOnWhenAlarmIsActivated() {
        //given
        init();
        Alarm alarm = new Alarm();
        String code = "123";
        alarm.activate(code);
        LightHandler lightHandler = new LightHandler(home);
        LightHandlerDecorator lightHandlerDecorator = new LightHandlerDecorator(alarm, lightHandler);
        lightHandlerDecorator.handle(event);
        //when
        boolean isFalse = testLight.isOn();
        //then
        assertFalse(isFalse);
    }

    @Test
    public void turnOnWhenAlarmIsDeactivated() {
        //given
        init();
        Alarm alarm = new Alarm();
        LightHandler lightHandler = new LightHandler(home);
        LightHandlerDecorator lightHandlerDecorator = new LightHandlerDecorator(alarm, lightHandler);
        lightHandlerDecorator.handle(event);
        //when
        boolean isTrue = testLight.isOn();
        //then
        assertTrue(isTrue);
    }

    @Test
    public void turnOnWhenAlarmIsInAnxiety() {
        //given
        init();
        Alarm alarm = new Alarm();
        alarm.switchToAnxietyMode();
        LightHandler lightHandler = new LightHandler(home);
        LightHandlerDecorator lightHandlerDecorator = new LightHandlerDecorator(alarm, lightHandler);
        lightHandlerDecorator.handle(event);
        //when
        boolean isFalse = testLight.isOn();
        //then
        assertFalse(isFalse);
    }
}
