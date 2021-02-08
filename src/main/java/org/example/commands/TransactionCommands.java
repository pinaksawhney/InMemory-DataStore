package org.example.commands;

import org.example.parser.Data;

public interface TransactionCommands<T, U> {

    void transactionRollback();

    void transactionBegin(Data<T, U> data);

    void transactionCommit();
}
