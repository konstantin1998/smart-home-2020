package ru.sbt.mipt.oop;

public enum DoorTypeEvent {
    DOOR_OPEN {
        public States getState(){
            return States.DOOR_OPEN;
        }
    }, DOOR_CLOSE{
        public States getState(){
            return States.DOOR_CLOSED;
        }
    };
    abstract States getState();
}
