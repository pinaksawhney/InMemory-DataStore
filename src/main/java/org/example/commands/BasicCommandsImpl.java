package org.example.commands;

import java.util.HashMap;
import java.util.Map;

public class BasicCommandsImpl<T, U> implements BasicCommands<T, U> {

    private HashMap<T, U> dataStore = new HashMap<T, U>();

    public void set(T Key, U Value) {
        dataStore.put(Key, Value);
    }

    public U get(T Key) {
        if(dataStore.containsKey(Key)) {
            return dataStore.get(Key);
        } return null;
    }

    public void unset(T Key) {
        if(dataStore.containsKey(Key)) {
            dataStore.remove(Key);
        }
    }

    public int numEqualTo(U Value) {
        int count = 0;
        for (Map.Entry<T,U> entry : dataStore.entrySet()) {
            if(entry.getValue() == Value) {
                count++;
            }
        }
        return count;
    }

    public void end() {
        System.exit(0);
    }

    public HashMap<T, U> getDataStore() {
        return dataStore;
    }
}
