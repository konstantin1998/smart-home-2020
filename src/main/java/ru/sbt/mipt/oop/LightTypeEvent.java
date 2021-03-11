package ru.sbt.mipt.oop;

public enum LightTypeEvent {
    LIGHT_ON{
        public States getState(){
            return States.LIGHT_ON;
        }
    }, LIGHT_OFF{
        public States getState(){
            return States.LIGHT_OFF;
        }
    };
    abstract States getState();
}
