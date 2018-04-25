package com.example.codepath.prontoshop.ui.customerlist;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.codepath.prontoshop.R;
import com.example.codepath.prontoshop.core.listeners.OnCustomerSelectedListener;
import com.example.codepath.prontoshop.model.Customer;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gretel on 11/7/17.
 */

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.ViewHolder> {

    private List<Customer> mCustomers;
    private final Context mContext;
    private final OnCustomerSelectedListener mListener;
    private boolean shouldHighightSelectedCustomer = false;
    private int selectedPosition = 0;



    public CustomerListAdapter(List<Customer> customers, Context context, OnCustomerSelectedListener listener) {
        this.mCustomers = customers;
        this.mContext = context;
        this.mListener = listener;
    }

    public void replaceData(List<Customer> customers){
        mCustomers = customers;
        notifyDataSetChanged();
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_customer_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Customer selectedCustomer = mCustomers.get(position);
        holder.customerName.setText(selectedCustomer.getCustomerName());
        holder.customerEmail.setText(selectedCustomer.getEmailAddress());
        Picasso.with(mContext)
                .load(selectedCustomer.getProfileImagePath())
                .fit()
                .placeholder(R.mipmap.ic_launcher_round)
                .into(holder.customerHeadShot);

        if(shouldHighightSelectedCustomer){
            if(selectedPosition == position){
                holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorAccent));

            } else {
                holder.itemView.setBackgroundColor(Color.TRANSPARENT);
            }
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }

    }

    @Override
    public int getItemCount() {
        if (mCustomers != null) {
            return mCustomers.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        @Bind(R.id.image_view_customer_head_shot) ImageView customerHeadShot;
        @Bind(R.id.text_view_customer_email) TextView customerEmail;
        @Bind(R.id.text_view_customer_name) TextView customerName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            shouldHighightSelectedCustomer = true;
            selectedPosition = getLayoutPosition();
            Customer selectedCustomer = mCustomers.get(selectedPosition);
            mListener.onSelectCustomer(selectedCustomer);
            notifyDataSetChanged();

        }

        @Override
        public boolean onLongClick(View v) {
            Customer selectedCustomer = mCustomers.get(selectedPosition);
            mListener.onLongClickCustomer(selectedCustomer);
            return true;
        }
    }
}
