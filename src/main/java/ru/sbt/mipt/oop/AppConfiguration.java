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
    public SensorEventsManager getEventManager(Collection<Handler> handlers) {

        // считываем состояние дома из файла
        //SmartHome smartHome = HomeReader.createHome("smart-home-1.js");
        // начинаем цикл обработки событий
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
//        ArrayList<Handler> handlers = new ArrayList<Handler>();
        Alarm alarm = new Alarm();
//        handlers.add(new AlarmDecorator(alarm, new LightHandler(smartHome)));
//        handlers.add(new AlarmDecorator(alarm, new DoorHandler(smartHome)));
//        handlers.add(new AlarmDecorator(alarm, new HallHandler(smartHome, new CommandSender())));
//        handlers.add(new AlarmDecorator(alarm, new DoorLockHandler(smartHome)));
        // EventCircle eventCircle = new EventCircle(handlers, new EventProvider());
        // eventCircle.run();
        for (Handler handler : handlers) {
            sensorEventsManager.registerEventHandler(new HandlerAdapter(new AlarmDecorator(alarm, handler)));
        }
        return sensorEventsManager;
    }
}
