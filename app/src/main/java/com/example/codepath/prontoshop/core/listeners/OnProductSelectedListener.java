package com.example.codepath.prontoshop.core.listeners;

import com.example.codepath.prontoshop.model.Product;

/**
 * Created by gretel on 11/7/17.
 */

public interface OnProductSelectedListener {

    void onSelectProduct(Product selectedProduct);
    void onLongClickProduct(Product clickedProduct);

}
