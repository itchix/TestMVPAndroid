package com.scrachx.foodfacts.checker.ui.scanner;

import com.scrachx.foodfacts.checker.data.DataManager;
import com.scrachx.foodfacts.checker.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by scots on 07/05/2017.
 */

public class ScannerPresenter<V extends ScannerMvpView> extends BasePresenter<V> implements ScannerMvpPresenter<V> {

    private static final String TAG = ScannerPresenter.class.getSimpleName();

    @Inject
    public ScannerPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

}
