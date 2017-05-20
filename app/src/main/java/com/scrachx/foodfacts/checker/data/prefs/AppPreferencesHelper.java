/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.scrachx.foodfacts.checker.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.scrachx.foodfacts.checker.data.DataManager;
import com.scrachx.foodfacts.checker.di.ApplicationContext;
import com.scrachx.foodfacts.checker.di.PreferenceInfo;
import com.scrachx.foodfacts.checker.utils.AppConstants;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by janisharali on 27/01/17.
 */

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";
    private static final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";
    private static final String PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";
    private static final String PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL";
    private static final String PREF_KEY_CURRENT_USER_PROFILE_PIC_URL = "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL";
    private static final String PREF_KEY_FIRST_USAGE = "PREF_KEY_FIRST_USAGE";

    private static final String PREF_KEY_ALLERGENS_PALM_OIL = "allergens_palm_oil";
    private static final String PREF_KEY_ALLERGENS_GLUTEN = "allergens_gluten";
    private static final String PREF_KEY_ALLERGENS_EGGS = "allergens_eggs";
    private static final String PREF_KEY_ALLERGENS_FISH = "allergens_fish";
    private static final String PREF_KEY_ALLERGENS_SOY = "allergens_soy";
    private static final String PREF_KEY_ALLERGENS_MILK = "allergens_milk";
    private static final String PREF_KEY_ALLERGENS_NUTS = "allergens_nuts";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public Long getCurrentUserId() {
        long userId = mPrefs.getLong(PREF_KEY_CURRENT_USER_ID, AppConstants.NULL_INDEX);
        return userId == AppConstants.NULL_INDEX ? null : userId;
    }

    @Override
    public void setCurrentUserId(Long userId) {
        long id = userId == null ? AppConstants.NULL_INDEX : userId;
        mPrefs.edit().putLong(PREF_KEY_CURRENT_USER_ID, id).apply();
    }

    @Override
    public String getCurrentUserName() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_NAME, null);
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply();
    }

    @Override
    public String getCurrentUserEmail() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_EMAIL, null);
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_EMAIL, email).apply();
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, null);
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, profilePicUrl).apply();
    }

    @Override
    public boolean getAllergensPalmOil() {
        return mPrefs.getBoolean(PREF_KEY_ALLERGENS_PALM_OIL, false);
    }

    @Override
    public void setAllergensPalmOil(boolean activate) {
        mPrefs.edit().putBoolean(PREF_KEY_ALLERGENS_PALM_OIL, activate).apply();
    }

    @Override
    public boolean getAllergensGluten() {
        return mPrefs.getBoolean(PREF_KEY_ALLERGENS_GLUTEN, false);
    }

    @Override
    public void setAllergensGluten(boolean activate) {
        mPrefs.edit().putBoolean(PREF_KEY_ALLERGENS_GLUTEN, activate).apply();
    }

    @Override
    public boolean getAllergensEggs() {
        return mPrefs.getBoolean(PREF_KEY_ALLERGENS_EGGS, false);
    }

    @Override
    public void setAllergensEggs(boolean activate) {
        mPrefs.edit().putBoolean(PREF_KEY_ALLERGENS_EGGS, activate).apply();
    }

    @Override
    public boolean getAllergensFish() {
        return mPrefs.getBoolean(PREF_KEY_ALLERGENS_FISH, false);
    }

    @Override
    public void setAllergensFish(boolean activate) {
        mPrefs.edit().putBoolean(PREF_KEY_ALLERGENS_FISH, activate).apply();
    }

    @Override
    public boolean getAllergensSoy() {
        return mPrefs.getBoolean(PREF_KEY_ALLERGENS_SOY, false);
    }

    @Override
    public void setAllergensSoy(boolean activate) {
        mPrefs.edit().putBoolean(PREF_KEY_ALLERGENS_SOY, activate).apply();
    }

    @Override
    public boolean getAllergensMilk() {
        return mPrefs.getBoolean(PREF_KEY_ALLERGENS_MILK, false);
    }

    @Override
    public void setAllergensMilk(boolean activate) {
        mPrefs.edit().putBoolean(PREF_KEY_ALLERGENS_MILK, activate).apply();
    }

    @Override
    public boolean getAllergensNuts() {
        return mPrefs.getBoolean(PREF_KEY_ALLERGENS_NUTS, false);
    }

    @Override
    public void setAllergensNuts(boolean activate) {
        mPrefs.edit().putBoolean(PREF_KEY_ALLERGENS_NUTS, activate).apply();
    }

    @Override
    public boolean isFirstUsage() {
        return mPrefs.getBoolean(PREF_KEY_FIRST_USAGE, true);
    }

    @Override
    public void setFirstUsage(Boolean firstUsage) {
        mPrefs.edit().putBoolean(PREF_KEY_FIRST_USAGE, firstUsage).apply();
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType());
    }

    @Override
    public void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply();
    }

}
