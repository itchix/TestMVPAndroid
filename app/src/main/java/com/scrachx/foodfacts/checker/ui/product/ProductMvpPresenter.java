package com.scrachx.foodfacts.checker.ui.product;

import com.scrachx.foodfacts.checker.data.network.model.Product;
import com.scrachx.foodfacts.checker.di.PerActivity;
import com.scrachx.foodfacts.checker.ui.base.MvpPresenter;

/**
 * Created by scots on 08/05/2017.
 */
@PerActivity
public interface ProductMvpPresenter <V extends ProductMvpView> extends MvpPresenter<V> {

    void onScannerClick();

    void saveProduct(Product product);

}
