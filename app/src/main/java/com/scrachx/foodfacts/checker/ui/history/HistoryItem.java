package com.scrachx.foodfacts.checker.ui.history;

import com.scrachx.foodfacts.checker.data.db.model.History;

import java.util.List;

/**
 * Created by scots on 08/05/2017.
 */

public class HistoryItem {

    private List<History> mProductsHistory;
    private long mCount;

    public HistoryItem(List<History> mProductsHistory, long mCount) {
        this.mProductsHistory = mProductsHistory;
        this.mCount = mCount;
    }

    public List<History> getProductsHistory() {
        return mProductsHistory;
    }

    public void setProductsHistory(List<History> mProductsHistory) {
        this.mProductsHistory = mProductsHistory;
    }

    public long getCount() {
        return mCount;
    }

    public void setCount(long mCount) {
        this.mCount = mCount;
    }

}
