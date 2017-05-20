package com.scrachx.foodfacts.checker.ui.allergens;

import com.scrachx.foodfacts.checker.data.DataManager;
import com.scrachx.foodfacts.checker.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by scots on 20/05/2017.
 */

public class AllergensPresenter<V extends AllergensMvpView> extends BasePresenter<V> implements AllergensMvpPresenter<V> {

    private static final String TAG = AllergensPresenter.class.getSimpleName();

    @Inject
    public AllergensPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

}
