package com.example.codepath.prontoshop.core.events;

import com.example.codepath.prontoshop.model.LineItem;

import java.util.List;

/**
 * Created by gretel on 11/8/17.
 */

public class UpdateToolbarEvent {
    private final List<LineItem> mLineItems;

    public UpdateToolbarEvent(List<LineItem> mLineItems) {
        this.mLineItems = mLineItems;
    }

    public List<LineItem> getmLineItems() {
        return mLineItems;
    }
}
