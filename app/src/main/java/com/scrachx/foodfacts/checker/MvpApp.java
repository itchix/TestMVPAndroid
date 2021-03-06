/*
 * Copyright (C) 20/05/2017 Scot Scriven
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.scrachx.foodfacts.checker;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor.Level;
import com.jacksonandroidnetworking.JacksonParserFactory;
import com.scrachx.foodfacts.checker.data.DataManager;
import com.scrachx.foodfacts.checker.di.component.ApplicationComponent;
import com.scrachx.foodfacts.checker.di.component.DaggerApplicationComponent;
import com.scrachx.foodfacts.checker.di.module.ApplicationModule;
import com.scrachx.foodfacts.checker.utils.AppLogger;
import com.scrachx.foodfacts.checker.utils.OkHttpUtil;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MvpApp extends Application {

    @Inject
    public DataManager mDataManager;

    @Inject
    public CalligraphyConfig mCalligraphyConfig;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

        AppLogger.init();
        try {
            OkHttpUtil.init(false);
        } catch (Exception e) {
            Timber.e(e);
        }

        OkHttpClient client = OkHttpUtil.getClient();
        AndroidNetworking.initialize(getApplicationContext(), client);
        AndroidNetworking.setParserFactory(new JacksonParserFactory());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(Level.BODY);
        }

        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

}
