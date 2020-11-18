package ru.sbt.mipt.remoteController;

import ru.sbt.mipt.homeAndComponents.Action;
import ru.sbt.mipt.homeAndComponents.SmartHome;

import java.util.HashMap;

public class RemoteController {
    private final SmartHome home;
    private final String rcId;
    private HashMap<String, Action> buttonsToActions;

    RemoteController(SmartHome home, String rcId) {
        this.home = home;
        this.rcId = rcId;
        buttonsToActions = new HashMap<>();
    }
}
