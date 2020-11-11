package ru.sbt.mipt.oop;
import com.coolcompany.smarthome.events.EventHandler;
import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import ru.sbt.mipt.adapter.HandlerAdapter;
import ru.sbt.mipt.alarm.Alarm;
import ru.sbt.mipt.handlers.*;
import ru.sbt.mipt.homeAndComponents.SmartHome;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
public class AppConfiguration {

    private final Alarm alarm = new Alarm();

    private AlarmDecorator decorate(Handler handler) {
        return new AlarmDecorator(alarm, handler, new SMSSender());
    }

    @Bean
    public SmartHome getHome() {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(HomeConfiguration.class);
        return context.getBean(SmartHome.class);
    }

    @Bean
    public EventHandler lightHandler(SmartHome home) {
        return  new HandlerAdapter(decorate(new LightHandler(home)));
    }

    @Bean
    public EventHandler doorHandler(SmartHome home) {
        return new HandlerAdapter(decorate(new DoorHandler(home)));
    }

    @Bean
    public EventHandler doorLockHandler(SmartHome home) {
        return new HandlerAdapter(decorate(new DoorLockHandler(home)));
    }

    @Bean
    public EventHandler hallHandler(SmartHome home) {
        return new HandlerAdapter(decorate(new HallHandler(home, new CommandSender())));
    }

    @Bean
    public SensorEventsManager getEventManager(Collection<EventHandler> handlers) {

        SensorEventsManager sensorEventsManager = new SensorEventsManager();

        for (EventHandler handler : handlers) {
            sensorEventsManager.registerEventHandler(handler);
        }
        return sensorEventsManager;
    }
}
