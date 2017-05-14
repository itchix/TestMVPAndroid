package com.scrachx.foodfacts.checker.ui.product.ingredients;

import com.scrachx.foodfacts.checker.data.DataManager;
import com.scrachx.foodfacts.checker.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by scotscriven on 14/05/2017.
 */

public class IngredientsProductPresenter<V extends IngredientsProductMvpView> extends BasePresenter<V> implements IngredientsProductMvpPresenter<V> {

    private static final String TAG = "IngredientsProductPresenter";

    @Inject
    public IngredientsProductPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

}
