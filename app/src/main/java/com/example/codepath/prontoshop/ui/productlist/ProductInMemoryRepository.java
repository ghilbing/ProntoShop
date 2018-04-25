package com.example.codepath.prontoshop.ui.productlist;

import com.example.codepath.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.codepath.prontoshop.data.SampleProductData;
import com.example.codepath.prontoshop.model.Category;
import com.example.codepath.prontoshop.model.Product;

import java.util.List;

/**
 * Created by gretel on 12/1/17.
 */

public class ProductInMemoryRepository implements ProductListContract.Repository {

    public ProductInMemoryRepository(){}


    @Override
    public List<Product> getAllProducts() {
        return SampleProductData.getSampleProducts();
    }

    @Override
    public Product getProductById(long id) {
        return null;
    }

    @Override
    public void deleteProduct(Product product, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public void addProduct(Product product, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public void updateProduct(Product product, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }
}
