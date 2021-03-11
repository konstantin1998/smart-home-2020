package ru.sbt.mipt.oop;


public interface SmartHomeProvider {
    SmartHome provideSmartHome();
    void setHomeToAll(SmartHome smartHome);
}
