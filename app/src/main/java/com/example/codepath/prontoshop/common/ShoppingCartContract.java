package com.example.codepath.prontoshop.common;

import com.example.codepath.prontoshop.model.Customer;
import com.example.codepath.prontoshop.model.LineItem;

import java.util.List;

/**
 * Created by gretel on 11/8/17.
 */

public interface ShoppingCartContract {

    void addItemToCart(LineItem item);
    void removeItemFromCart(LineItem item);
    void clearAllItemsFromCart();
    List<LineItem> getShoppingCart();
    void setCustomer(Customer customer);
    void updateItemQty(LineItem item, int qty);
    Customer getSelectedCustomer();
    void completeCheckout();
}
