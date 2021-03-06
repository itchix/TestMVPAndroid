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

import com.scrachx.foodfacts.checker.data.DataManager;

/**
 * Created by janisharali on 27/01/17.
 */

public interface PreferencesHelper {

    boolean isFirstUsage();
    void setFirstUsage(Boolean firstUsage);

    int getCurrentUserLoggedInMode();
    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    Long getCurrentUserId();
    void setCurrentUserId(Long userId);

    String getCurrentUserName();
    void setCurrentUserName(String userName);

    String getCurrentUserEmail();
    void setCurrentUserEmail(String email);

    String getCurrentUserProfilePicUrl();
    void setCurrentUserProfilePicUrl(String profilePicUrl);

    boolean getAllergensPalmOil();
    void setAllergensPalmOil(boolean activate);

    boolean getAllergensGluten();
    void setAllergensGluten(boolean activate);

    boolean getAllergensEggs();
    void setAllergensEggs(boolean activate);

    boolean getAllergensFish();
    void setAllergensFish(boolean activate);

    boolean getAllergensSoy();
    void setAllergensSoy(boolean activate);

    boolean getAllergensMilk();
    void setAllergensMilk(boolean activate);

    boolean getAllergensNuts();
    void setAllergensNuts(boolean activate);

}
