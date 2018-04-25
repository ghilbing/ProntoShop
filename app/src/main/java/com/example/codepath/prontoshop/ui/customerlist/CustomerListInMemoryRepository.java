package com.example.codepath.prontoshop.ui.customerlist;

import com.example.codepath.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.codepath.prontoshop.data.SampleCustomerData;
import com.example.codepath.prontoshop.data.SampleProductData;
import com.example.codepath.prontoshop.model.Customer;

import java.util.List;

/**
 * Created by gretel on 12/1/17.
 */

public class CustomerListInMemoryRepository implements CustomerListContract.Repository {

    public CustomerListInMemoryRepository() {}

    @Override
    public List<Customer> getAllCustomers() {
        return SampleCustomerData.getCustomers();
    }

    @Override
    public Customer getCustomerById(long id) {
        return null;
    }

    @Override
    public void onDeleteCustomer(Customer customer, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public void addCustomer(Customer customer, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public void updateCustomer(Customer customer, OnDatabaseOperationCompleteListener listener) {

    }
}
