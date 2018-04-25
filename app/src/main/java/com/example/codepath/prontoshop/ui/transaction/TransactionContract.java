package com.example.codepath.prontoshop.ui.transaction;

import com.example.codepath.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.codepath.prontoshop.model.Customer;
import com.example.codepath.prontoshop.model.Transaction;

import java.util.List;

/**
 * Created by gretel on 12/1/17.
 */

public interface TransactionContract {

    public interface View{
        void showTransaction(List<Transaction> transactions);
        void showEmptyText();
        void hideEmptyText();
        void showCofirmDeletePromt(Transaction transaction);
        void showMessage(String message);

    }

    public interface Actions{
        void loadTransaction();
        void onDeleteItemButtonClicked(Transaction transaction);
        void editTransaction(Transaction transaction);
        void deleteTransaction(Transaction transaction);
        Customer getCustomerById(long id);

    }

    public interface Repository{
        List<Transaction> getAllTransactions();
        void updateTransaction(Transaction transaction, OnDatabaseOperationCompleteListener listener);
        Transaction getTransaction(long id);
        void deleteTransaction(long id, OnDatabaseOperationCompleteListener listener);
    }

}
