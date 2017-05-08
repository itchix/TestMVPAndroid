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

    @Override
    public void saveProduct(Product product) {
        History history = new History(product.getProductName(), product.getBrands(), product.getImageFrontUrl(), new Date(), product.getCode(), product.getQuantity());
        getCompositeDisposable().add(getDataManager()
                .insertHistory(history)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long id) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                    }
                }));
    }
}
