package com.example.codepath.prontoshop.core.dagger;

import com.example.codepath.prontoshop.common.MainActivity;
import com.example.codepath.prontoshop.common.ShoppingCart;
import com.example.codepath.prontoshop.ui.checkout.CheckoutContract;
import com.example.codepath.prontoshop.ui.checkout.CheckoutPresenter;
import com.example.codepath.prontoshop.ui.customerlist.CustomerPresenter;
import com.example.codepath.prontoshop.ui.productlist.ProductPresenter;
import com.example.codepath.prontoshop.ui.transaction.TransactionPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by gretel on 11/7/17.
 */

@Singleton
@Component(
        modules = {
                AppModule.class,
                ShoppingCartModule.class,
                BusModule.class,
                PersistenceModule.class
        }
)

public interface AppComponent {
    void inject(MainActivity activity);
    void inject(ShoppingCart cart);
    void inject(ProductPresenter presenter);
    void inject(CustomerPresenter presenter);
    void inject(TransactionPresenter presenter);

}
