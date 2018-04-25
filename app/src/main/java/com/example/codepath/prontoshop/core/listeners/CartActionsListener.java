package com.example.codepath.prontoshop.core.listeners;

import com.example.codepath.prontoshop.model.LineItem;

/**
 * Created by gretel on 11/7/17.
 */

public interface CartActionsListener {

    void onItemDeleted(LineItem item);
    void onItemQtyChange(LineItem item, int qty);
}
