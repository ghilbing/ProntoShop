package com.example.codepath.prontoshop.ui.customerlist;


import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.codepath.prontoshop.R;
import com.example.codepath.prontoshop.core.listeners.OnCustomerSelectedListener;
import com.example.codepath.prontoshop.model.Customer;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerListFragment extends Fragment implements OnCustomerSelectedListener, CustomerListContract.View{

    private View mRootView;
    private CustomerListAdapter mAdapter;
    private CustomerListContract.Actions mPresenter;

    @Bind(R.id.customer_list_recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.empty_text)
    TextView mEmptyText;
    @Bind(R.id.fab)
    FloatingActionButton mFab;


    public CustomerListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView =  inflater.inflate(R.layout.fragment_customer_list, container, false);
        ButterKnife.bind(this, mRootView);

        mPresenter = new CustomerPresenter(this);

        //setup the adapter
        List<Customer> tempCustomers = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mAdapter = new CustomerListAdapter(tempCustomers, getActivity(), this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return mRootView;
    }

    @Override
    public void onResume(){
        super.onResume();
        mPresenter.loadCustomer();
    }

    @Override
    public void onSelectCustomer(Customer customer) {

    }

    @Override
    public void onLongClickCustomer(Customer customer) {

    }

    @Override
    public void showCustomers(List<Customer> customers) {
        //Update adapter
        mAdapter.replaceData(customers);


    }

    @Override
    public void showAddCustomerForm() {

    }

    @Override
    public void showDeleteCustomerPrompt(Customer customer) {

    }

    @Override
    public void showEditCustomerForm(Customer customer) {

    }

    @Override
    public void showEmptyText() {
        mRecyclerView.setVisibility(View.GONE);
        mEmptyText.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideEmptyText() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mEmptyText.setVisibility(View.GONE);

    }

    @Override
    public void showMessage(String message) {

    }
}
