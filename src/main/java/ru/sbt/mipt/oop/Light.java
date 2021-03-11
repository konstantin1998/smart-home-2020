package ru.sbt.mipt.oop;



public class Light extends RoomObject implements RoomObjectInterface {
    private States isOn;
    private static final String[] st = {" was turned on.", " was turned off."};
    private SmartHome home;

    public Light(String id, String isOn) {
        super(id);
        this.isOn = States.valueOf(isOn);
    }

    public Light(String id, States isOn) {
        super(id);
        this.isOn = isOn;
    }

    public States isOn() {
        return isOn;
    }

    String getId() {
        return id;
    }


    @Override
    public void setState(States on) {
        isOn = on;
    }

    @Override
    public void setHome(SmartHome home) {
        this.home = home;
    }

    @Override
    public String getString() {
        return "Light " + this.getId() + " in room " + home.findRoomForLight(this.getId()).getName() + (isOn.getString());
    }
}
