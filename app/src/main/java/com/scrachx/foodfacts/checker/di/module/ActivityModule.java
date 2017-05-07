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

package com.scrachx.foodfacts.checker.di.module;

import android.app.Activity;
import android.content.Context;

import com.scrachx.foodfacts.checker.di.ActivityContext;
import com.scrachx.foodfacts.checker.di.PerActivity;
import com.scrachx.foodfacts.checker.ui.about.AboutMvpPresenter;
import com.scrachx.foodfacts.checker.ui.about.AboutMvpView;
import com.scrachx.foodfacts.checker.ui.about.AboutPresenter;
import com.scrachx.foodfacts.checker.ui.login.LoginMvpPresenter;
import com.scrachx.foodfacts.checker.ui.login.LoginMvpView;
import com.scrachx.foodfacts.checker.ui.login.LoginPresenter;
import com.scrachx.foodfacts.checker.ui.main.MainMvpPresenter;
import com.scrachx.foodfacts.checker.ui.main.MainMvpView;
import com.scrachx.foodfacts.checker.ui.main.MainPresenter;
import com.scrachx.foodfacts.checker.ui.search.SearchMvpPresenter;
import com.scrachx.foodfacts.checker.ui.search.SearchMvpView;
import com.scrachx.foodfacts.checker.ui.search.SearchPresenter;
import com.scrachx.foodfacts.checker.ui.splash.SplashMvpPresenter;
import com.scrachx.foodfacts.checker.ui.splash.SplashMvpView;
import com.scrachx.foodfacts.checker.ui.splash.SplashPresenter;
import com.scrachx.foodfacts.checker.ui.walkthrough.WalkthroughMvpPresenter;
import com.scrachx.foodfacts.checker.ui.walkthrough.WalkthroughMvpView;
import com.scrachx.foodfacts.checker.ui.walkthrough.WalkthroughPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by janisharali on 27/01/17.
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(SplashPresenter<SplashMvpView>
                                                                     presenter) {
        return presenter;
    }

    @Provides
    AboutMvpPresenter<AboutMvpView> provideAboutPresenter(AboutPresenter<AboutMvpView>
                                                                  presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(LoginPresenter<LoginMvpView>
                                                                  presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(MainPresenter<MainMvpView>
                                                               presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    WalkthroughMvpPresenter<WalkthroughMvpView> provideWalkthroughPresenter(WalkthroughPresenter<WalkthroughMvpView>
                                                               presenter) {
        return presenter;
    }

    @Provides
    SearchMvpPresenter<SearchMvpView> provideSearchPresenter(SearchPresenter<SearchMvpView>
                                                                  presenter) {
        return presenter;
    }
}
