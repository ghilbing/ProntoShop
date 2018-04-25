package com.example.codepath.prontoshop.ui.transaction;

import com.example.codepath.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.codepath.prontoshop.model.Transaction;

import java.util.List;

/**
 * Created by gretel on 12/1/17.
 */

public class TempRepository implements TransactionContract.Repository {
    @Override
    public List<Transaction> getAllTransactions() {
        return null;
    }

    @Override
    public void updateTransaction(Transaction transaction, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public Transaction getTransaction(long id) {
        return null;
    }

    @Override
    public void deleteTransaction(long id, OnDatabaseOperationCompleteListener listener) {

    }
}
