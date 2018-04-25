package com.example.codepath.prontoshop.ui.checkout;

import com.example.codepath.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.codepath.prontoshop.model.LineItem;
import com.example.codepath.prontoshop.model.Transaction;

import java.util.List;

/**
 * Created by gretel on 11/8/17.
 */

public interface CheckoutContract {

    public interface View{
        void showLineitems(List<LineItem> items);
        void showEmptyText();
        void showCartTotals(double tax, double subTotal, double total);
        void showConfirmCheckout();
        void showConfirmClearCart();
        void hideText();
        void showMessage(String message);
    }

    public interface Actions{
        void loadLineItem();
        void onCheckoutButtonClicked();
        void onDeleteItemButtonClicked(LineItem item);
        void checkout();
        void onClearButtonClicked();
        void clearShoppingCart();
        void setPaymentType(String paymentType);
        void markAsPaid(boolean paid);
        void onItemQtyChanged(LineItem item, int qty);

    }

    public interface Repository{
        List<LineItem> getAllLineItems();
        void saveTransaction(Transaction transaction, OnDatabaseOperationCompleteListener listener);
        void updateTransaction(Transaction transaction, OnDatabaseOperationCompleteListener listener);

    }

}
