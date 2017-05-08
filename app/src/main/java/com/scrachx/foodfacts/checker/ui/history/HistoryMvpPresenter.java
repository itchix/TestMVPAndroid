package com.scrachx.foodfacts.checker.ui.history;

import com.scrachx.foodfacts.checker.di.PerActivity;
import com.scrachx.foodfacts.checker.ui.base.MvpPresenter;

/**
 * Created by scots on 08/05/2017.
 */
@PerActivity
public interface HistoryMvpPresenter<V extends HistoryMvpView> extends MvpPresenter<V> {

    void onLoadProducts(int limit);

    void loadProduct(String barcode);

}
