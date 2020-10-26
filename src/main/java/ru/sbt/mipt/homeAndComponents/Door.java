package ru.sbt.mipt.homeAndComponents;

public class Door implements Actionable {
    private final String id;
    private boolean isOpen;
    private boolean isLocked;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
        this.isLocked = false;
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void execute(Action action) {
        action.perform(this);
    }
}
