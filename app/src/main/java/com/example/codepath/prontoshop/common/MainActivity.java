package com.example.codepath.prontoshop.common;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.codepath.prontoshop.R;
import com.example.codepath.prontoshop.core.ProntoShopApplication;
import com.example.codepath.prontoshop.data.DatabaseHelper;
import com.example.codepath.prontoshop.model.Product;
import com.example.codepath.prontoshop.ui.checkout.CheckoutFragment;
import com.example.codepath.prontoshop.ui.customerlist.CustomerListFragment;
import com.example.codepath.prontoshop.ui.productlist.ProductListFragment;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.R.attr.fragment;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    @Bind(R.id.tabs)
    TabLayout mTabLayout;
    @Bind(R.id.text_view_number_of_items)
    TextView mQtyTextView;
    @Bind(R.id.text_view_total_amount)
    TextView mTotalTextView;
    @Bind(R.id.text_view_customer_name)
    TextView mNameTextView;

    private Bus mBus;
    @Inject ShoppingCart mCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        setupViewPager();

        mBus = ProntoShopApplication.getInstance().getBus();

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();


    }

    private void setupViewPager() {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onPause(){
        super.onPause();
        ProntoShopApplication.getInstance().getAppComponent().inject(this);
        mCart.saveCartToPreference();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        try{
            ProntoShopApplication.getInstance().getAppComponent().inject(this);
            mCart.saveCartToPreference();
            mBus.unregister(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
