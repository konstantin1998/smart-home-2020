package ru.sbt.mipt.oop;


public enum CommandType {
    LIGHT_OFF{
        public boolean getState(){
            return false;
        }
    };
    abstract boolean getState();
}

