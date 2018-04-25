package com.example.codepath.prontoshop.core.events;

import com.example.codepath.prontoshop.model.Customer;

/**
 * Created by gretel on 11/8/17.
 */

public class CustomerSelectedEvent {

    private final Customer selectedCustomer;
    private final boolean clearCustomer;

    public CustomerSelectedEvent(Customer selectedCustomer, boolean clearCustomer) {
        this.selectedCustomer = selectedCustomer;
        this.clearCustomer = clearCustomer;
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public boolean isClearCustomer() {
        return clearCustomer;
    }
}
