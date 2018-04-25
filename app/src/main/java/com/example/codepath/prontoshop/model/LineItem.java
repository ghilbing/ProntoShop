package com.example.codepath.prontoshop.model;

/**
 * Created by gretel on 11/7/17.
 */

public class LineItem extends Product {

    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public LineItem(Product product, int quantity){
        super(product);
        this.setQuantity(quantity);
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSumPrice() {
        return getSalePrice() * quantity;
    }
}
