package com.scrachx.foodfacts.checker.ui.product;

import com.scrachx.foodfacts.checker.data.DataManager;
import com.scrachx.foodfacts.checker.data.db.model.History;
import com.scrachx.foodfacts.checker.data.db.model.Question;
import com.scrachx.foodfacts.checker.data.network.model.Product;
import com.scrachx.foodfacts.checker.ui.base.BasePresenter;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
