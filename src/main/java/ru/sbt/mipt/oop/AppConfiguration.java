package ru.sbt.mipt.oop;
import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.adapter.HandlerAdapter;
import ru.sbt.mipt.alarm.Alarm;
import ru.sbt.mipt.handlers.*;
import ru.sbt.mipt.homeAndComponents.SmartHome;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
public class AppConfiguration {
    SmartHome home = null;

    private void createHome() {
        home = HomeReader.createHome("smart-home-1.js");
    }

    @Bean
    public Handler lightHandler() {
        if (home == null) {
            createHome();
        }
        return new LightHandler(home);
    }

    @Bean
    public Handler doorHandler() {
        if (home == null) {
            createHome();
        }
        return new DoorHandler(home);
    }

    @Bean
    public Handler doorLockHandler() {
        if (home == null) {
            createHome();
        }
        return new DoorLockHandler(home);
    }

    @Bean
    public Handler hallHandler() {
        if (home == null) {
            createHome();
        }
        return new HallHandler(home, new CommandSender());
    }

    @Bean
    public Handler alarmHandler() {
        return new AlarmHandler(new Alarm());
    }

    @Bean
    public Handler hallLightHandler() {
        if (home == null) {
            createHome();
        }
        return new HallLightEnabler(home);
    }

    @Bean
    public Handler entranceDoorCloser() {
        if (home == null) {
            createHome();
        }
        return new EntranceDoorCloser(home);
    }

    @Bean
    public Handler lightsDisabler() {
        if (home == null) {
            createHome();
        }
        return new LightsDisabler(home);
    }

    @Bean
    public Handler lightsEnabler() {
        if (home == null) {
            createHome();
        }
        return new LightsEnabler(home);
    }

    @Bean
    public SensorEventsManager getEventManager(Collection<Handler> handlers) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        Alarm alarm = new Alarm();

        for (Handler handler : handlers) {
            sensorEventsManager.registerEventHandler(new HandlerAdapter(new AlarmDecorator(alarm, handler)));
        }
        return sensorEventsManager;
    }
}
