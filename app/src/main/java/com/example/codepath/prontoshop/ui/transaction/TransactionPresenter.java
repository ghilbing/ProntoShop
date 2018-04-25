package com.example.codepath.prontoshop.ui.transaction;

import com.example.codepath.prontoshop.core.ProntoShopApplication;
import com.example.codepath.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.codepath.prontoshop.model.Customer;
import com.example.codepath.prontoshop.model.Transaction;
import com.example.codepath.prontoshop.ui.customerlist.CustomerListContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by gretel on 12/1/17.
 */

public class TransactionPresenter implements TransactionContract.Actions, OnDatabaseOperationCompleteListener {

    private final TransactionContract.View mView;
    @Inject TransactionContract.Repository mRepository;
    @Inject CustomerListContract.Repository mCustomerRepository;

    public TransactionPresenter(TransactionContract.View mView) {
        this.mView = mView;
        ProntoShopApplication.getInstance().getAppComponent().inject(this);
    }

    @Override
    public void onDatabaseOperationFailed(String error) {
        mView.showMessage("Error: " + error);

    }

    @Override
    public void onDatabaseOperationSucceded(String message) {
        mView.showMessage(message);

    }

    @Override
    public void loadTransaction() {
        List<Transaction> transactions = mRepository.getAllTransactions();
        if (transactions != null && transactions.size() > 0){
            mView.hideEmptyText();
            mView.showTransaction(transactions);
        }else{
            mView.showEmptyText();
        }

    }

    @Override
    public void onDeleteItemButtonClicked(Transaction transaction) {
        mView.showCofirmDeletePromt(transaction);

    }

    @Override
    public void editTransaction(Transaction transaction) {
        mRepository.updateTransaction(transaction, this);

    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        mRepository.deleteTransaction(transaction.getId(), this);

    }

    @Override
    public Customer getCustomerById(long id) {
        return mCustomerRepository.getCustomerById(id);
    }
}
