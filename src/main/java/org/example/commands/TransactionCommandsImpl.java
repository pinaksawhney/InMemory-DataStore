package org.example.commands;

import java.util.HashMap;
import java.util.Stack;

import org.example.parser.Data;

public class TransactionCommandsImpl<T, U> implements TransactionCommands<T, U> {

    private final String BEGIN = "BEGIN";
    private final String NO_TRANSACTION = "NO TRANSACTION";

    private HashMap<T, Stack<Data<T, U>>> rollback = new HashMap<T, Stack<Data<T, U>>>();
    private Stack<Data<T, U>> transaction = new Stack<Data<T, U>>();



    public void transactionRollback() {
        if (transaction.empty()) {
            System.out.println(NO_TRANSACTION);
            return;
        }
        BasicCommands<T, U> basicCommands = new BasicCommandsImpl<T, U>();
        while (!transaction.peek().getCommand().equals(BEGIN)) {
            T Key = transaction.peek().getKey();
            U Value = transaction.peek().getValue();
            basicCommands.getDataStore().put(Key, Value);
            transaction.pop();
        }
        transaction.pop();
    }

    public void transactionBegin(Data<T, U> data) {
        transaction.push(data);
    }

    public void transactionCommit() {
        BasicCommands<T, U> basicCommands = new BasicCommandsImpl<T, U>();
        while (!transaction.empty()) {
            if(transaction.peek().getCommand().equals(BEGIN)) {
                transaction.pop();
                continue;
            }
            T Key = transaction.peek().getKey();
            U Value = transaction.peek().getValue();
            basicCommands.getDataStore().put(Key, Value);
            transaction.pop();
        }
    }
}
