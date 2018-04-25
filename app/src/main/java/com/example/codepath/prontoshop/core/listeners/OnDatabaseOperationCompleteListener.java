package com.example.codepath.prontoshop.core.listeners;

/**
 * Created by gretel on 12/1/17.
 */

public interface OnDatabaseOperationCompleteListener {

    void onDatabaseOperationFailed(String error);
    void onDatabaseOperationSucceded(String message);

}
