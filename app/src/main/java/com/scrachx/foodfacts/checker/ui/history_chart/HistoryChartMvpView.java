package com.scrachx.foodfacts.checker.ui.history_chart;

import com.scrachx.foodfacts.checker.data.db.model.History;
import com.scrachx.foodfacts.checker.ui.base.MvpView;

import java.util.List;

/**
 * Created by scots on 21/05/2017.
 */

public interface HistoryChartMvpView extends MvpView {

    void loadHistoryStats(List<History> productsHistory);

    void loadHistoryGrade(int position);

}
