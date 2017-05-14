package com.scrachx.foodfacts.checker.ui.fullscreen;

import com.scrachx.foodfacts.checker.data.DataManager;
import com.scrachx.foodfacts.checker.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by scotscriven on 13/05/2017.
 */

public class FullScreenImagePresenter<V extends FullScreenImageMvpView> extends BasePresenter<V>
        implements FullScreenImageMvpPresenter<V> {

    private static final String TAG = FullScreenImagePresenter.class.getSimpleName();

    @Inject
    public FullScreenImagePresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

}
