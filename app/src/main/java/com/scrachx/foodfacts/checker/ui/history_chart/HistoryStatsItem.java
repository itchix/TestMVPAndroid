package com.scrachx.foodfacts.checker.ui.history_chart;

import android.graphics.drawable.Drawable;

import java.util.List;

/**
 * Created by scots on 21/05/2017.
 */

public class HistoryStatsItem {

    private String mName;
    private long mCount;
    private Drawable mImage;

    public HistoryStatsItem(String mName, long mCount, Drawable mImage) {
        this.mName = mName;
        this.mCount = mCount;
        this.mImage = mImage;
    }

    public long getCount() {
        return mCount;
    }

    public void setCount(long mCount) {
        this.mCount = mCount;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public Drawable getImage() {
        return mImage;
    }

    public void setImage(Drawable mImage) {
        this.mImage = mImage;
    }
}
