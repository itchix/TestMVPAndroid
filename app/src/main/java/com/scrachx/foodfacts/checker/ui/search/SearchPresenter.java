package com.scrachx.foodfacts.checker.ui.search;

import com.androidnetworking.error.ANError;
import com.scrachx.foodfacts.checker.data.DataManager;
import com.scrachx.foodfacts.checker.data.db.model.History;
import com.scrachx.foodfacts.checker.data.network.model.Product;
import com.scrachx.foodfacts.checker.data.network.model.Search;
import com.scrachx.foodfacts.checker.data.network.model.SearchRequest;
import com.scrachx.foodfacts.checker.data.network.model.State;
import com.scrachx.foodfacts.checker.ui.base.BasePresenter;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by scots on 07/05/2017.
 */

public class SearchPresenter <V extends SearchMvpView> extends BasePresenter<V> implements SearchMvpPresenter<V> {

    @Inject
    public SearchPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void onLoadProducts(String searchTerms, final int page) {
        if(page == 1) getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .searchProductByName(new SearchRequest(searchTerms, page))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Search>() {
                    @Override
                    public void accept(Search searchResult) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        if (searchResult != null) {
                            if(page == 1) getMvpView().hideLoading();
                            getMvpView().refreshResults(searchResult.getProducts(), Integer.parseInt(searchResult.getCount()));
                        }

                        if(page == 1) getMvpView().hideLoading();
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
