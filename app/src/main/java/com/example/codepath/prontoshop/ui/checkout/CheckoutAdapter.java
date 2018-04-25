package com.example.codepath.prontoshop.ui.checkout;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codepath.prontoshop.R;
import com.example.codepath.prontoshop.core.listeners.CartActionsListener;
import com.example.codepath.prontoshop.model.LineItem;
import com.example.codepath.prontoshop.util.Formatter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gretel on 11/7/17.
 */

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder> {

    private List<LineItem> mLineItems;
    private final Activity mContext;
    private final CartActionsListener mListener;

    public CheckoutAdapter(List<LineItem> lineItems, Context context, CartActionsListener listener) {
        this.mLineItems = lineItems;
        this.mContext = (Activity) context;
        this.mListener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_shopping_cart_items, parent, false);
        ViewHolder viewHoder = new ViewHolder(rowView);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        LineItem item = mLineItems.get(position);
        Picasso.with(mContext)
                .load(item.getImagePath())
                .fit()
                .placeholder(R.drawable.default_image)
                .into(holder.productImage);
        holder.productName.setText(item.getProductName());
        holder.productPrice.setText(Formatter.formatCurrency(item.getSalePrice()));
        holder.qty.setText(String.valueOf(item.getQuantity()));



    }

    @Override
    public int getItemCount() {
        if (mLineItems != null) {
            return mLineItems.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.product_image) ImageView productImage;
        @Bind(R.id.text_view_product_name) TextView productName;
        @Bind(R.id.text_view_price) TextView productPrice;
        @Bind(R.id.edit_text_qty) EditText qty;
        @Bind(R.id.button_delete) Button deleteButton;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            deleteButton.setOnClickListener(this);
            qty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //handle the change of Line Item Qty
                    LineItem item = mLineItems.get(getLayoutPosition());
                    updateQtyDialog(item);
                }
            });
        }

        @Override
        public void onClick(View v) {

            LineItem item = mLineItems.get(getLayoutPosition());
            mListener.onItemDeleted(item);

        }
    }

    private void updateQtyDialog(final LineItem item){
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = mContext.getLayoutInflater();

        View rootView = inflater.inflate(R.layout.dialog_enter_item_qty, null);
        dialog.setView(rootView);

        View titleView = inflater.inflate(R.layout.dialog_title, null);
        TextView titleText = titleView.findViewById(R.id.text_view_dialog_title);
        titleText.setText(item.getProductName());
        dialog.setCustomTitle(titleView);

        final EditText qty = rootView.findViewById(R.id.edit_text_item_qty);
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!qty.getText().toString().isEmpty()){
                    int qtyEntered = Integer.parseInt(qty.getText().toString());
                    mListener.onItemQtyChange(item, qtyEntered);
                } else {
                    Toast.makeText(mContext, "Invalid Quantity", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.show();




    }
}
