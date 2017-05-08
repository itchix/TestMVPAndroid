package com.scrachx.foodfacts.checker.ui.search;

import com.scrachx.foodfacts.checker.di.PerActivity;
import com.scrachx.foodfacts.checker.ui.base.MvpPresenter;

/**
 * Created by scots on 07/05/2017.
 */

@PerActivity
public interface SearchMvpPresenter <V extends SearchMvpView> extends MvpPresenter<V> {

    void onLoadProducts(String searchTerms, int page);

    void loadProduct(String barcode);

}
