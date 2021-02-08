package org.example.commands;

import java.util.HashMap;

import org.example.parser.Data;

public interface BasicCommands<T, U> {

    void set(T Key, U Value);

    U get(T Key);

    void unset(T Key);

    int numEqualTo(U Value);

    void end();

    HashMap<T, U> getDataStore();

    void addToRollback(T Key, Data<String, Integer> command);
}
