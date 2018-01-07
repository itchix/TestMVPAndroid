package com.scrachx.foodfacts.checker.ui.history_chart;

import com.scrachx.foodfacts.checker.ui.base.MvpPresenter;
import com.scrachx.foodfacts.checker.ui.history.HistoryMvpView;

/**
 * Created by scots on 21/05/2017.
 */

public interface HistoryChartMvpPresenter<V extends HistoryChartMvpView> extends MvpPresenter<V> {

    void onLoadHistoryStats();

    void onLoadHistoryGrade(int position);

}
