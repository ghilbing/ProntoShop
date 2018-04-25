package com.example.codepath.prontoshop.core.dagger;

import android.content.Context;

import com.example.codepath.prontoshop.ui.customerlist.CustomerListContract;
import com.example.codepath.prontoshop.ui.customerlist.CustomerListInMemoryRepository;
import com.example.codepath.prontoshop.ui.productlist.ProductInMemoryRepository;
import com.example.codepath.prontoshop.ui.productlist.ProductListContract;
import com.example.codepath.prontoshop.ui.transaction.TempRepository;
import com.example.codepath.prontoshop.ui.transaction.TransactionContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gretel on 12/1/17.
 */
@Module
public class PersistenceModule {

    @Provides @Singleton
    public ProductListContract.Repository providesProductRepository(Context context){
        return new ProductInMemoryRepository();
    }

    @Provides @Singleton
    public CustomerListContract.Repository providesCustomerRepository(Context context){
        return new CustomerListInMemoryRepository();
    }

    @Provides @Singleton
    public TransactionContract.Repository providesTransactionRepository(Context context){
        return new TempRepository();
    }


}
