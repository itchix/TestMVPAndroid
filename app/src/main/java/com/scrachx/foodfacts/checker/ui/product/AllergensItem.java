/*
 * Copyright (C) 20/05/2017 scots
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.scrachx.foodfacts.checker.ui.product;

import android.graphics.drawable.Drawable;

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
