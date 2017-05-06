package com.scrachx.foodfacts.checker.ui.walkthrough;

import com.scrachx.foodfacts.checker.data.DataManager;
import com.scrachx.foodfacts.checker.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by scots on 06/05/2017.
 */

public class WalkthroughPresenter <V extends WalkthroughMvpView> extends BasePresenter<V> implements WalkthroughMvpPresenter<V> {

    private static final String TAG = "WalkthroughPresenter";

    @Inject
    public WalkthroughPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void onSignInClick() {
        getMvpView().openLoginActivity();
    }

    @Override
    public void onSkipClick() {
        getMvpView().openMainActivity();
    }
}
