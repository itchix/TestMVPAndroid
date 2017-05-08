package com.scrachx.foodfacts.checker.ui.history;

import com.androidnetworking.error.ANError;
import com.scrachx.foodfacts.checker.data.DataManager;
import com.scrachx.foodfacts.checker.data.db.model.History;
import com.scrachx.foodfacts.checker.data.network.model.State;
import com.scrachx.foodfacts.checker.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by scots on 08/05/2017.
 */

public class HistoryPresenter <V extends HistoryMvpView> extends BasePresenter<V> implements HistoryMvpPresenter<V> {

    @Inject
    public HistoryPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void onLoadProducts(int page) {
        getCompositeDisposable().add(getDataManager()
                .getHistory(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HistoryItem>() {
                    @Override
                    public void accept(HistoryItem historyItem) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        if (historyItem != null) {
                            getMvpView().loadHistory(historyItem.getProductsHistory(), historyItem.getCount());
                        }
                    }
                }));
    }

    @Override
    public void loadProduct(String barcode) {
        getCompositeDisposable().add(getDataManager()
                .searchProductByBarcode(barcode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<State>() {
                    @Override
                    public void accept(State stateProduct) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        if (stateProduct != null) {
                            getMvpView().hideLoading();
                            getMvpView().openPageProduct(stateProduct);
                        }

                        getMvpView().hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        // handle the login error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }

}
