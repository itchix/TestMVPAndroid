package com.scrachx.foodfacts.checker.ui.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scrachx.foodfacts.checker.R;
import com.scrachx.foodfacts.checker.data.network.model.Product;
import com.scrachx.foodfacts.checker.data.network.model.State;
import com.scrachx.foodfacts.checker.ui.base.BaseFragment;
import com.scrachx.foodfacts.checker.ui.custom.EndlessRecyclerViewScrollListener;
import com.scrachx.foodfacts.checker.ui.custom.RecyclerItemClickListener;
import com.scrachx.foodfacts.checker.ui.product.ProductActivity;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;


public class SearchFragment  extends BaseFragment implements SearchMvpView {

    public static final String TAG = "SearchFragment";

    private RecyclerView mProductsRecyclerView;
    private EndlessRecyclerViewScrollListener mScrollListener;
    private List<Product> mProducts;
    private int mCountProducts = 0;

    @Inject
    SearchMvpPresenter<SearchMvpView> mPresenter;

    public static SearchFragment newInstance(Bundle args) {
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static SearchFragment newInstance() {
        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
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

        mProducts = new ArrayList<>();

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
                if(mProducts.size() < mCountProducts) {
                    mPresenter.onLoadProducts(getArguments().getString("query"), page);
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
        if(StringUtils.isNotEmpty(getArguments().getString("query"))) {
            mPresenter.onLoadProducts(getArguments().getString("query"), 1);
        }
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void refreshResults(List<Product> searchResult, int numberOfProducts) {
        mCountProducts = numberOfProducts;
        if(mProductsRecyclerView.getAdapter() == null) {
            mProducts.addAll(searchResult);
            if(mProducts.size() < mCountProducts) {
                mProducts.add(null);
            }
            ProductsRecyclerViewAdapter adapter = new ProductsRecyclerViewAdapter(mProducts);
            mProductsRecyclerView.setAdapter(adapter);
        } else {
            final int posStart = mProducts.size();
            if (mProducts.size() - 1 < mCountProducts + 1) {
                mProducts.remove(mProducts.size() - 1);
                mProducts.addAll(searchResult);
                if(mProducts.size() < mCountProducts) {
                    mProducts.add(null);
                }
                mProductsRecyclerView.getAdapter().notifyItemRangeChanged(posStart - 1, mProducts.size() - 1);
            }
        }
    }

    @Override
    public void openPageProduct(State stateProduct) {
        Product productCloned = stateProduct.getProduct();
        mPresenter.saveProduct(productCloned);
        Bundle args = new Bundle();
        args.putParcelable("state", stateProduct);
        startActivity(ProductActivity.getStartIntent(this.getActivity(), args));
    }

}
