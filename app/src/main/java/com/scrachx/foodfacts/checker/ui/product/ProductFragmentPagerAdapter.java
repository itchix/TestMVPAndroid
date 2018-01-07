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

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProductFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<String> navMenuTitles;
    private List<Fragment> fragments;

    public ProductFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragments = new ArrayList<>();
        this.navMenuTitles = new ArrayList<>();
    }

    public void addFragment(Fragment fragment, String title) {
        this.fragments.add(fragment);
        this.navMenuTitles.add(title);
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return navMenuTitles.get(position);
    }
}
