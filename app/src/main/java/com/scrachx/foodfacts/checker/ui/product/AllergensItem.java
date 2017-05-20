package com.scrachx.foodfacts.checker.ui.product;

import android.graphics.drawable.Drawable;

/**
 * Created by scots on 20/05/2017.
 */

public class AllergensItem {

    private String mName;
    private Drawable mImage;

    public AllergensItem(String mName, Drawable mImage) {
        this.mName = mName;
        this.mImage = mImage;
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
