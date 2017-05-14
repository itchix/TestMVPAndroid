package com.scrachx.foodfacts.checker.ui.product.summary;

import com.scrachx.foodfacts.checker.data.DataManager;
import com.scrachx.foodfacts.checker.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by scotscriven on 14/05/2017.
 */

public class SummaryProductPresenter  <V extends SummaryProductMvpView> extends BasePresenter<V> implements SummaryProductMvpPresenter<V> {

    private static final String TAG = "IngredientsProductPresenter";

    @Inject
    public SummaryProductPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

}
