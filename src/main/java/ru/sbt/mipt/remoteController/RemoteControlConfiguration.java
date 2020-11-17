package ru.sbt.mipt.remoteController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.alarm.Alarm;
import ru.sbt.mipt.handlers.*;
import ru.sbt.mipt.homeAndComponents.SmartHome;
import ru.sbt.mipt.remoteController.commands.*;

import java.util.Collection;
import java.util.HashMap;

@Configuration
public class RemoteControlConfiguration {
    @Bean
    public Handler allLightsDisabler(SmartHome home, Alarm alarm) {
        return new AlarmDecorator(alarm, new LightsDisabler(home));
    }

    @Bean
    public Handler allLightsEnabler(SmartHome home, Alarm alarm) {
        return new AlarmDecorator(alarm, new LightsEnabler(home));
    }

    @Bean
    public Handler entranceDoorCloser(SmartHome home, Alarm alarm) {
        return new AlarmDecorator(alarm, new EntranceDoorCloser(home));
    }

    @Bean
    public Handler hallLightEnabler(SmartHome home, Alarm alarm) {
        return new AlarmDecorator(alarm, new HallLightEnabler(home));
    }

    @Bean
    public Handler alarmHandler(Alarm alarm) {
        return new AlarmHandler(alarm);
    }

    @Bean
    public ActivateAlarmCommand activateAlarmCommand(Collection<Handler> handlers) {
        return new ActivateAlarmCommand(handlers);
    }

    @Bean
    public TriggerAlarmCommand triggerAlarmCommand(Collection<Handler> handlers) {
        return new TriggerAlarmCommand(handlers);
    }

    @Bean
    public EnableLightsInHallCommand enableLightsInHallCommand(Collection<Handler> handlers) {
        return new EnableLightsInHallCommand(handlers);
    }

    @Bean
    public CloseEntranceDoorCommand closeEntranceDoorCommand(Collection<Handler> handlers) {
        return new CloseEntranceDoorCommand(handlers);
    }

    @Bean
    public AllLightsDisableCommand allLightsDisableCommand(Collection<Handler> handlers) {
        return new AllLightsDisableCommand(handlers);
    }

    @Bean
    public AllLightsEnableCommand allLightsEnableCommand(Collection<Handler> handlers) {
        return new AllLightsEnableCommand(handlers);
    }

    @Bean
    public HashMap<String, Command> buttonsToCommands(ActivateAlarmCommand activateAlarmCommand,
                                                      TriggerAlarmCommand triggerAlarmCommand,
                                                      EnableLightsInHallCommand enableLightsInHallCommand,
                                                      CloseEntranceDoorCommand closeEntranceDoorCommand,
                                                      AllLightsDisableCommand allLightsDisableCommand,
                                                      AllLightsEnableCommand allLightsEnableCommand) {

        HashMap<String, Command> buttonsToCommands = new HashMap<>();
        buttonsToCommands.put("1", activateAlarmCommand);
        buttonsToCommands.put("2", triggerAlarmCommand);
        buttonsToCommands.put("3", enableLightsInHallCommand);
        buttonsToCommands.put("4", closeEntranceDoorCommand);
        buttonsToCommands.put("A", allLightsDisableCommand);
        buttonsToCommands.put("B", allLightsEnableCommand);

        return buttonsToCommands;
    }

    @Bean
    public String rcId() {
        return "1";
    }

    @Bean
    public RemoteController remoteController(HashMap<String, Command> buttonsToCommands, String rcId) {
        return new RemoteController(buttonsToCommands, rcId);
    }

    @Bean
    public RemoteControlRegistry remoteControlRegistry(RemoteController remoteController, String rcId) {
        RemoteControlRegistry registry = new RemoteControlRegistry();
        registry.registerRemoteControl(remoteController, rcId);
        return registry;
    }
}
