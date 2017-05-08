package com.scrachx.foodfacts.checker.ui.history;

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
import com.scrachx.foodfacts.checker.data.network.model.Product;
import com.scrachx.foodfacts.checker.data.network.model.State;
import com.scrachx.foodfacts.checker.ui.base.BaseFragment;
import com.scrachx.foodfacts.checker.ui.custom.EndlessRecyclerViewScrollListener;
import com.scrachx.foodfacts.checker.ui.search.ProductsRecyclerViewAdapter;
import com.scrachx.foodfacts.checker.ui.custom.RecyclerItemClickListener;
import com.scrachx.foodfacts.checker.ui.product.ProductActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by scots on 08/05/2017.
 */

public class HistoryFragment extends BaseFragment implements HistoryMvpView {

    public static final String TAG = "HistoryFragment";

    @Inject
    HistoryMvpPresenter<HistoryMvpView> mPresenter;

    private RecyclerView mProductsRecyclerView;
    private EndlessRecyclerViewScrollListener mScrollListener;
    private List<History> mProductsHistory;
    private int mCountProducts = 0;

    public static HistoryFragment newInstance() {
        Bundle args = new Bundle();
        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        mPresenter.onAttach(this);
        setUp(view);

        return view;
    }

    @Override
    protected void setUp(View view) {

        mProductsRecyclerView = (RecyclerView) view.findViewById(R.id.products_recycler_view);

        mProductsHistory = new ArrayList<>();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mProductsRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mProductsRecyclerView.setLayoutManager(mLayoutManager);

        // use VERTICAL divider
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mProductsRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        mProductsRecyclerView.addItemDecoration(dividerItemDecoration);

        // Retain an instance so that you can call `resetState()` for fresh searches
        mScrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if(mProductsHistory.size() < mCountProducts) {
                    mPresenter.onLoadProducts(page);
                }
            }
        };
        // Adds the scroll listener to RecyclerView
        mProductsRecyclerView.addOnScrollListener(mScrollListener);

        // Click listener on a product
        mProductsRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(view.getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Product p = ((ProductsRecyclerViewAdapter) mProductsRecyclerView.getAdapter()).getProduct(position);
                        if(p != null) {
                            mPresenter.loadProduct(p.getCode());
                        }
                    }
                })
        );

        mPresenter.onLoadProducts(1);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void loadHistory(List<History> productsHistory, long numberOfProductsHistory) {
        mCountProducts = (int)numberOfProductsHistory;
        if(mProductsRecyclerView.getAdapter() == null) {
            mProductsHistory.addAll(productsHistory);
            if(mProductsHistory.size() < mCountProducts) {
                mProductsHistory.add(null);
            }
            HistoryRecyclerViewAdapter adapter = new HistoryRecyclerViewAdapter(mProductsHistory);
            mProductsRecyclerView.setAdapter(adapter);
        } else {
            final int posStart = mProductsHistory.size();
            if (mProductsHistory.size() - 1 < mCountProducts + 1) {
                mProductsHistory.remove(mProductsHistory.size() - 1);
                mProductsHistory.addAll(productsHistory);
                if(mProductsHistory.size() < mCountProducts) {
                    mProductsHistory.add(null);
                }
                mProductsRecyclerView.getAdapter().notifyItemRangeChanged(posStart - 1, mProductsHistory.size() - 1);
            }
        }
    }

    @Override
    public void openPageProduct(State stateProduct) {
        Bundle args = new Bundle();
        args.putParcelable("state", stateProduct);
        startActivity(ProductActivity.getStartIntent(this.getActivity(), args));
    }

}
