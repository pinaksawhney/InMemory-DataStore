package org.example.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.example.parser.Data;

public class BasicCommandsImpl<T, U> implements BasicCommands<T, U> {

    private HashMap<T, U> dataStore = new HashMap<T, U>();
    private static TransactionCommands<String, Integer> transactionCommands = new TransactionCommandsImpl<>();
    private static HashMap<String, Stack<Data<String, Integer>>> rollback = transactionCommands.getRollback();

    public void set(T Key, U Value) {
        dataStore.put(Key, Value);
        Data<String, Integer> data = new Data<>();
        data.setCommand("SET");
        data.setKey(String.valueOf(Key));
        data.setValue(Integer.parseInt(String.valueOf(Value)));
        addToRollback(Key, data);
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
            if(entry.getValue().equals(Value)) {
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

    public void addToRollback(T Key, Data<String, Integer> command) {
        Stack<Data<String, Integer>> stack;
        if(rollback.containsKey(Key)) {
            stack = rollback.get(Key);
        } else {
            stack = new Stack<>();
        }
        stack.push(command);
        rollback.put(Key.toString(), stack);
    }
}
