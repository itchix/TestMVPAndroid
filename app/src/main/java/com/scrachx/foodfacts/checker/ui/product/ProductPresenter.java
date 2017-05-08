package com.scrachx.foodfacts.checker.ui.product;

import com.scrachx.foodfacts.checker.data.DataManager;
import com.scrachx.foodfacts.checker.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by scots on 08/05/2017.
 */

public class ProductPresenter <V extends ProductMvpView> extends BasePresenter<V> implements ProductMvpPresenter<V> {

    private static final String TAG = "ProductPresenter";

    @Inject
    public ProductPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void onScannerClick() {
        getMvpView().openScannerActivity();
    }
}
