package com.scrachx.foodfacts.checker.ui.history_chart;

import com.scrachx.foodfacts.checker.data.DataManager;
import com.scrachx.foodfacts.checker.data.db.model.History;
import com.scrachx.foodfacts.checker.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by scots on 21/05/2017.
 */

public class HistoryChartPresenter<V extends HistoryChartMvpView> extends BasePresenter<V> implements HistoryChartMvpPresenter<V> {

    @Inject
    public HistoryChartPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void onLoadHistoryStats() {
        getCompositeDisposable().add(getDataManager()
                .getHistoryStats()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<History>>() {
                    @Override
                    public void accept(List<History> productsHistory) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        if (productsHistory.size() > 0) {
                            getMvpView().loadHistoryStats(productsHistory);
                        }
                    }
                }));
    }
}
