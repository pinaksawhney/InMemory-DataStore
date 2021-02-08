package org.example.parser;

public class Data<T, U> {

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public T getKey() {
        return Key;
    }

    public void setKey(T key) {
        Key = key;
    }

    public U getValue() {
        return Value;
    }

    public void setValue(U value) {
        Value = value;
    }

    private String command;
    private T Key;
    private U Value;

}
