package ru.sbt.mipt.homeAndComponents;

import ru.sbt.mipt.handlers.Action;

public class Door implements Actionable {
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void execute(Action action) {
        action.perform(this);
    }
}
