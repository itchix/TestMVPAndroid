package com.scrachx.foodfacts.checker.ui.history;

import com.scrachx.foodfacts.checker.data.db.model.History;
import com.scrachx.foodfacts.checker.data.network.model.State;
import com.scrachx.foodfacts.checker.ui.base.MvpView;

import java.util.List;

/**
 * Created by scots on 08/05/2017.
 */

public interface HistoryMvpView extends MvpView {

    void loadHistory(List<History> productsHistory, long count);

    void openPageProduct(State stateProduct);

}
