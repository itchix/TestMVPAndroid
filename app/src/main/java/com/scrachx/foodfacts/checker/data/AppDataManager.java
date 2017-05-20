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

package com.scrachx.foodfacts.checker.data;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;
import com.scrachx.foodfacts.checker.data.db.DbHelper;
import com.scrachx.foodfacts.checker.data.db.model.History;
import com.scrachx.foodfacts.checker.data.db.model.Option;
import com.scrachx.foodfacts.checker.data.db.model.Question;
import com.scrachx.foodfacts.checker.data.db.model.User;
import com.scrachx.foodfacts.checker.data.network.ApiHeader;
import com.scrachx.foodfacts.checker.data.network.ApiHelper;
import com.scrachx.foodfacts.checker.data.network.model.LoginRequest;
import com.scrachx.foodfacts.checker.data.network.model.LoginResponse;
import com.scrachx.foodfacts.checker.data.network.model.LogoutResponse;
import com.scrachx.foodfacts.checker.data.network.model.Search;
import com.scrachx.foodfacts.checker.data.network.model.SearchRequest;
import com.scrachx.foodfacts.checker.data.network.model.State;
import com.scrachx.foodfacts.checker.data.prefs.PreferencesHelper;
import com.scrachx.foodfacts.checker.di.ApplicationContext;
import com.scrachx.foodfacts.checker.ui.history.HistoryItem;
import com.scrachx.foodfacts.checker.utils.AppConstants;
import com.scrachx.foodfacts.checker.utils.CommonUtils;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          DbHelper dbHelper,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public Observable<Long> insertHistory(History history) {
        return mDbHelper.insertHistory(history);
    }

    @Override
    public Observable<HistoryItem> getHistory(int page) {
        return mDbHelper.getHistory(page);
    }

    @Override
    public Observable<Long> insertUser(User user) {
        return mDbHelper.insertUser(user);
    }

    @Override
    public Observable<List<User>> getAllUsers() {
        return mDbHelper.getAllUsers();
    }

    @Override
    public boolean isFirstUsage() {
        return mPreferencesHelper.isFirstUsage();
    }

    @Override
    public void setFirstUsage(Boolean firstUsage) {
        mPreferencesHelper.setFirstUsage(firstUsage);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public Long getCurrentUserId() {
        return mPreferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(Long userId) {
        mPreferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public String getCurrentUserName() {
        return mPreferencesHelper.getCurrentUserName();
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPreferencesHelper.setCurrentUserName(userName);
    }

    @Override
    public String getCurrentUserEmail() {
        return mPreferencesHelper.getCurrentUserEmail();
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPreferencesHelper.setCurrentUserEmail(email);
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPreferencesHelper.getCurrentUserProfilePicUrl();
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        mPreferencesHelper.setCurrentUserProfilePicUrl(profilePicUrl);
    }

    @Override
    public boolean getAllergensPalmOil() {
        return mPreferencesHelper.getAllergensPalmOil();
    }

    @Override
    public void setAllergensPalmOil(boolean activate) {
        mPreferencesHelper.setAllergensPalmOil(activate);
    }

    @Override
    public boolean getAllergensGluten() {
        return mPreferencesHelper.getAllergensGluten();
    }

    @Override
    public void setAllergensGluten(boolean activate) {
        mPreferencesHelper.setAllergensGluten(activate);
    }

    @Override
    public boolean getAllergensEggs() {
        return mPreferencesHelper.getAllergensEggs();
    }

    @Override
    public void setAllergensEggs(boolean activate) {
        mPreferencesHelper.setAllergensEggs(activate);
    }

    @Override
    public boolean getAllergensFish() {
        return mPreferencesHelper.getAllergensFish();
    }

    @Override
    public void setAllergensFish(boolean activate) {
        mPreferencesHelper.setAllergensFish(activate);
    }

    @Override
    public boolean getAllergensSoy() {
        return mPreferencesHelper.getAllergensSoy();
    }

    @Override
    public void setAllergensSoy(boolean activate) {
        mPreferencesHelper.setAllergensSoy(activate);
    }

    @Override
    public boolean getAllergensMilk() {
        return mPreferencesHelper.getAllergensMilk();
    }

    @Override
    public void setAllergensMilk(boolean activate) {
        mPreferencesHelper.setAllergensMilk(activate);
    }

    @Override
    public boolean getAllergensNuts() {
        return mPreferencesHelper.getAllergensNuts();
    }

    @Override
    public void setAllergensNuts(boolean activate) {
        mPreferencesHelper.setAllergensNuts(activate);
    }

    @Override
    public void updateUserInfo(
            String accessToken,
            Long userId,
            LoggedInMode loggedInMode,
            String userName,
            String email,
            String profilePicPath) {

        setCurrentUserId(userId);
        setCurrentUserLoggedInMode(loggedInMode);
        setCurrentUserName(userName);
        setCurrentUserEmail(email);
        setCurrentUserProfilePicUrl(profilePicPath);
    }

    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(
                null,
                null,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
                null,
                null,
                null);
    }

    @Override
    public Observable<Boolean> isQuestionEmpty() {
        return mDbHelper.isQuestionEmpty();
    }

    @Override
    public Observable<Boolean> isOptionEmpty() {
        return mDbHelper.isOptionEmpty();
    }

    @Override
    public Observable<Boolean> saveQuestion(Question question) {
        return mDbHelper.saveQuestion(question);
    }

    @Override
    public Observable<Boolean> saveOption(Option option) {
        return mDbHelper.saveOption(option);
    }

    @Override
    public Observable<Boolean> saveQuestionList(List<Question> questionList) {
        return mDbHelper.saveQuestionList(questionList);
    }

    @Override
    public Observable<Boolean> saveOptionList(List<Option> optionList) {
        return mDbHelper.saveOptionList(optionList);
    }

    @Override
    public Observable<List<Question>> getAllQuestions() {
        return mDbHelper.getAllQuestions();
    }

    @Override
    public Observable<Boolean> seedDatabaseQuestions() {

        GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();

        return mDbHelper.isQuestionEmpty()
                .concatMap(new Function<Boolean, ObservableSource<? extends Boolean>>() {
                    @Override
                    public ObservableSource<? extends Boolean> apply(Boolean isEmpty)
                            throws Exception {
                        if (isEmpty) {
                            Type type = $Gson$Types
                                    .newParameterizedTypeWithOwner(null, List.class,
                                            Question.class);
                            List<Question> questionList = gson.fromJson(
                                    CommonUtils.loadJSONFromAsset(mContext,
                                            AppConstants.SEED_DATABASE_QUESTIONS),
                                    type);

                            return saveQuestionList(questionList);
                        }
                        return Observable.just(false);
                    }
                });
    }

    @Override
    public Observable<Boolean> seedDatabaseOptions() {

        GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();

        return mDbHelper.isOptionEmpty()
                .concatMap(new Function<Boolean, ObservableSource<? extends Boolean>>() {
                    @Override
                    public ObservableSource<? extends Boolean> apply(Boolean isEmpty)
                            throws Exception {
                        if (isEmpty) {
                            Type type = new TypeToken<List<Option>>() {
                            }
                                    .getType();
                            List<Option> optionList = gson.fromJson(
                                    CommonUtils.loadJSONFromAsset(mContext,
                                            AppConstants.SEED_DATABASE_OPTIONS),
                                    type);

                            return saveOptionList(optionList);
                        }
                        return Observable.just(false);
                    }
                });
    }

    @Override
    public Observable<Search> searchProductByName(SearchRequest request) {
        return mApiHelper.searchProductByName(request);
    }

    @Override
    public Observable<State> searchProductByBarcode(String barcode) {
        return mApiHelper.searchProductByBarcode(barcode);
    }
}
