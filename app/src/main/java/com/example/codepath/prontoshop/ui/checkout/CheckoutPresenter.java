package com.example.codepath.prontoshop.ui.checkout;

import com.example.codepath.prontoshop.common.ShoppingCart;
import com.example.codepath.prontoshop.core.ProntoShopApplication;
import com.example.codepath.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.codepath.prontoshop.model.LineItem;
import com.example.codepath.prontoshop.model.Transaction;
import com.example.codepath.prontoshop.ui.customerlist.CustomerListContract;
import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

import static com.example.codepath.prontoshop.R.string.tax;
import static com.example.codepath.prontoshop.R.string.total;

/**
 * Created by gretel on 12/1/17.
 */

public class CheckoutPresenter implements CheckoutContract.Actions, OnDatabaseOperationCompleteListener {

    private final CheckoutContract.View mView;
    @Inject CheckoutContract.Repository mRepository;
    @Inject ShoppingCart mCart;
    @Inject Bus mBus;

    private double subTotal = 0.0;
    private double total;
    private double tax;
    private double taxRate = 0.8;
    private String selectedPaymentType = "";
    private boolean paid = false;

    public CheckoutPresenter(CheckoutContract.View mView) {
        this.mView = mView;

    }


    @Override
    public void onDatabaseOperationFailed(String error) {
        mView.showMessage("Error message: " + error);

    }

    @Override
    public void onDatabaseOperationSucceded(String message) {
        mView.showMessage(message);

    }

    @Override
    public void loadLineItem() {
        List<LineItem> availableLineItems = mCart.getShoppingCart();
        calculateTotals(availableLineItems);
        if (availableLineItems != null && availableLineItems.size() > 0) {
            mView.hideText();
            mView.showLineitems(availableLineItems);
        } else {
            mView.showEmptyText();
        }
    }

    private void calculateTotals(List<LineItem> availableItems){

        subTotal = 0.0;
        total = 0.0;
        tax = 0.0;

        for (LineItem item: availableItems){
            subTotal += item.getSumPrice();
        }


        tax = subTotal * taxRate;
        total = tax + subTotal;
        mView.showCartTotals(tax, subTotal, total);

    }

    @Override
    public void onCheckoutButtonClicked() {
        mView.showConfirmCheckout();


    }

    @Override
    public void onDeleteItemButtonClicked(LineItem item) {
        mCart.removeItemFromCart(item);
        loadLineItem();

    }

    @Override
    public void checkout() {
        //Ensure a customer is selected
        if (mCart.getShoppingCart() == null || mCart.getShoppingCart().size() == 0){
            mView.showMessage("Cart is empty");
            return;
        }
        if (mCart.getSelectedCustomer() == null || mCart.getSelectedCustomer().getId() == 0){
            mView.showMessage("No customer is selected");
            return;
        }

        Transaction transaction = new Transaction();
        transaction.setCustomerId(mCart.getSelectedCustomer().getId());
        transaction.setLineItems(mCart.getShoppingCart());
        transaction.setTaxAmount(tax);
        transaction.setSubTotalAmount(subTotal);
        transaction.setTotalAmount(total);
        transaction.setPaymentType(selectedPaymentType);
        transaction.setPaid(paid);
        mRepository.saveTransaction(transaction, this);

    }

    @Override
    public void onClearButtonClicked() {
        mView.showConfirmClearCart();
    }

    @Override
    public void clearShoppingCart() {
        mCart.clearAllItemsFromCart();
        loadLineItem();

    }

    @Override
    public void setPaymentType(String paymentType) {
        selectedPaymentType = paymentType;

    }

    @Override
    public void markAsPaid(boolean paid) {
        this.paid = paid;

    }

    @Override
    public void onItemQtyChanged(LineItem item, int qty) {
        mCart.updateItemQty(item, qty);

    }
}
