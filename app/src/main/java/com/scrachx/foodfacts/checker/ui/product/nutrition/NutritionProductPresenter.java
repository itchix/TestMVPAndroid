package com.scrachx.foodfacts.checker.ui.product.nutrition;

import com.scrachx.foodfacts.checker.data.DataManager;
import com.scrachx.foodfacts.checker.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by scotscriven on 14/05/2017.
 */

public class NutritionProductPresenter<V extends NutritionProductMvpView> extends BasePresenter<V> implements NutritionProductMvpPresenter<V> {

    private static final String TAG = "NutritionProductPresenter";

    @Inject
    public NutritionProductPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

}
