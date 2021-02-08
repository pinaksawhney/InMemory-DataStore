package org.example.commands;

import java.util.HashMap;

public interface BasicCommands<T, U> {

    void set(T Key, U Value);

    U get(T Key);

    void unset(T Key);

    int numEqualTo(U Value);

    void end();

    HashMap<T, U> getDataStore();
}
