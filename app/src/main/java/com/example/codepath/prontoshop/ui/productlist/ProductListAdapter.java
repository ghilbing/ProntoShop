package com.example.codepath.prontoshop.ui.productlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.codepath.prontoshop.R;
import com.example.codepath.prontoshop.core.ProntoShopApplication;
import com.example.codepath.prontoshop.core.listeners.OnProductSelectedListener;
import com.example.codepath.prontoshop.model.Product;
import com.example.codepath.prontoshop.util.Formatter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gretel on 11/7/17.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private List<Product> mProducts;
    private final Context mContext;
    private final OnProductSelectedListener mListener;

    public ProductListAdapter(List<Product> products, Context context, OnProductSelectedListener listener) {
        this.mProducts = products;
        this.mContext = context;
        this.mListener = listener;
    }

    public void replaceData(List<Product> products){
        mProducts = products;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_list, parent,  false);
        ViewHolder viewHolder = new ViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mProducts != null) {
            Product product = mProducts.get(position);
            Picasso.with(mContext)
                    .load(product.getImagePath())
                    .fit()
                    .placeholder(R.drawable.default_image)
                    .into(holder.productImage);
            holder.productName.setText(product.getProductName());
            holder.category.setText(product.getCategoryName());
            holder.productPrice.setText(Formatter.formatCurrency(product.getSalePrice()));
            String productDescription = product.getDescription();
            String shortDescription = productDescription.substring(0, Math.min(productDescription.length(), 70));
            holder.description.setText(shortDescription);
        }


    }

    @Override
    public int getItemCount() {
        if(mProducts != null){
            return mProducts.size();
        } else {
            return 0;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        @Bind(R.id.textview_product_name) TextView productName;
        @Bind(R.id.textview_product_category) TextView category;
        @Bind(R.id.textview_product_description) TextView description;
        @Bind(R.id.image_view_add_to_cart_button) ImageView addToCartButton;
        @Bind(R.id.textview_product_price) TextView productPrice;
        @Bind(R.id.product_image) ImageView productImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            addToCartButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Product selectedProduct = mProducts.get(getLayoutPosition());
            mListener.onSelectProduct(selectedProduct);


        }

        @Override
        public boolean onLongClick(View v) {
            Product clickedProduct = mProducts.get(getLayoutPosition());
            mListener.onLongClickProduct(clickedProduct);
            return true;
        }
    }
}
