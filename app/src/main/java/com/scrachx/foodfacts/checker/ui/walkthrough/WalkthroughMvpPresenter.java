package com.scrachx.foodfacts.checker.ui.walkthrough;

import com.scrachx.foodfacts.checker.di.PerActivity;
import com.scrachx.foodfacts.checker.ui.base.MvpPresenter;

/**
 * Created by scots on 06/05/2017.
 */
@PerActivity
public interface WalkthroughMvpPresenter<V extends WalkthroughMvpView> extends MvpPresenter<V> {

    void onSignInClick();

    void onSkipClick();

}
