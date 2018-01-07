/*
 * Copyright (C) 20/05/2017 Scot Scriven
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.scrachx.foodfacts.checker.ui.history;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scrachx.foodfacts.checker.R;
import com.scrachx.foodfacts.checker.data.db.model.History;
import com.scrachx.foodfacts.checker.data.network.model.State;
import com.scrachx.foodfacts.checker.ui.base.BaseFragment;
import com.scrachx.foodfacts.checker.ui.custom.EndlessRecyclerViewScrollListener;
import com.scrachx.foodfacts.checker.ui.custom.RecyclerItemClickListener;
import com.scrachx.foodfacts.checker.ui.product.ProductActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoryFragment extends BaseFragment implements HistoryMvpView {

    public static final String TAG = "HistoryFragment";

    @Inject
    public HistoryMvpPresenter<HistoryMvpView> mPresenter;

    private RecyclerView mProductsHistoryRecyclerView;
    private EndlessRecyclerViewScrollListener mScrollListener;
    private List<History> mProductsHistory;
    private int mCountProducts = 0;

    public static HistoryFragment newInstance() {
        Bundle args = new Bundle();
        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static HistoryFragment newInstance(String grade) {
        Bundle args = new Bundle();
        args.putString("grade", grade);
        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        mPresenter.onAttach(this);
        setUp(view);

        return view;
    }

    @Override
    protected void setUp(View view) {
        String grade = "";
        if(this.getArguments() != null) {
            grade = convertDescriptionToGrade(this.getArguments().getString("grade"));
        }

        mProductsHistoryRecyclerView = view.findViewById(R.id.products_recycler_view);
        mProductsHistory = new ArrayList<>();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mProductsHistoryRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getBaseActivity());
        mProductsHistoryRecyclerView.setLayoutManager(mLayoutManager);

        // use VERTICAL divider
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mProductsHistoryRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        mProductsHistoryRecyclerView.addItemDecoration(dividerItemDecoration);

        // Retain an instance so that you can call `resetState()` for fresh searches
        String finalGrade = grade;
        mScrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (mProductsHistory.size() < mCountProducts) {
                    mPresenter.onLoadProducts(page, finalGrade);
                }
            }
        };
        // Adds the scroll listener to RecyclerView
        mProductsHistoryRecyclerView.addOnScrollListener(mScrollListener);

        // Click listener on a product
        mProductsHistoryRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(view.getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        History hp = ((HistoryRecyclerViewAdapter) mProductsHistoryRecyclerView.getAdapter()).getHistory(position);
                        if (hp != null) {
                            mPresenter.loadProduct(hp.getBarcode());
                        }
                    }
                })
        );

        mPresenter.onLoadProducts(0, grade);
    }

    @OnClick(R.id.nav_back_btn)
    void onNavBackClick() {
        getBaseActivity().onFragmentDetached(HistoryFragment.class.getSimpleName());
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void loadHistory(List<History> productsHistory, long numberOfProductsHistory) {
        mCountProducts = (int) numberOfProductsHistory;
        if (mProductsHistoryRecyclerView.getAdapter() == null) {
            mProductsHistory.addAll(productsHistory);
            if (mProductsHistory.size() < mCountProducts) {
                mProductsHistory.add(null);
            }
            HistoryRecyclerViewAdapter adapter = new HistoryRecyclerViewAdapter(mProductsHistory);
            mProductsHistoryRecyclerView.setAdapter(adapter);
        } else {
            final int posStart = mProductsHistory.size();
            if (mProductsHistory.size() - 1 < mCountProducts + 1) {
                mProductsHistory.remove(mProductsHistory.size() - 1);
                mProductsHistory.addAll(productsHistory);
                if (mProductsHistory.size() < mCountProducts) {
                    mProductsHistory.add(null);
                }
                mProductsHistoryRecyclerView.getAdapter().notifyItemRangeChanged(posStart - 1, mProductsHistory.size() - 1);
            }
        }
    }

    @Override
    public void openPageProduct(State stateProduct) {
        Bundle args = new Bundle();
        args.putParcelable("state", stateProduct);
        startActivity(ProductActivity.getStartIntent(this.getActivity(), args));
    }

    private String convertDescriptionToGrade(String descGrade) {
        if(descGrade.equals(getString(R.string.txt_history_stats_desc_a))) {
            return "a";
        } else if(descGrade.equals(getString(R.string.txt_history_stats_desc_b))) {
            return "a";
        } else if(descGrade.equals(getString(R.string.txt_history_stats_desc_c))) {
            return "a";
        } else if(descGrade.equals(getString(R.string.txt_history_stats_desc_d))) {
            return "a";
        } else if(descGrade.equals(getString(R.string.txt_history_stats_desc_e))) {
            return "a";
        } else  {
            return "nc";
        }
    }

}
