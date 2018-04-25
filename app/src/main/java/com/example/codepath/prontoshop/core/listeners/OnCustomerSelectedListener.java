package com.example.codepath.prontoshop.core.listeners;

import com.example.codepath.prontoshop.model.Customer;

/**
 * Created by gretel on 11/7/17.
 */

public interface OnCustomerSelectedListener {
    void onSelectCustomer(Customer customer);
    void onLongClickCustomer(Customer customer);
}
