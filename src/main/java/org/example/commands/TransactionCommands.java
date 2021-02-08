package org.example.commands;

import java.util.HashMap;
import java.util.Stack;

import org.example.parser.Data;

public interface TransactionCommands<T, U> {

    void transactionRollback();

    void transactionBegin(Data<T, U> data);

    void transactionCommit();

    HashMap<T, Stack<Data<T, U>>> getRollback();
}
