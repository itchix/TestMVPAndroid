package com.scrachx.foodfacts.checker.ui.product.nutritioninfo;

import com.scrachx.foodfacts.checker.data.DataManager;
import com.scrachx.foodfacts.checker.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by scotscriven on 14/05/2017.
 */

public class NutritionInfoProductPresenter <V extends NutritionInfoProductMvpView> extends BasePresenter<V> implements NutritionInfoProductMvpPresenter<V> {

    private static final String TAG = "NutritionInfoProductPresenter";

    @Inject
    public NutritionInfoProductPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

}
