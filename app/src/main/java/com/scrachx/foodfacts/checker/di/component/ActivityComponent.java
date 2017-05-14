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

package com.scrachx.foodfacts.checker.di.component;

import com.scrachx.foodfacts.checker.di.PerActivity;
import com.scrachx.foodfacts.checker.di.module.ActivityModule;
import com.scrachx.foodfacts.checker.ui.about.AboutFragment;
import com.scrachx.foodfacts.checker.ui.fullscreen.FullScreenImageActivity;
import com.scrachx.foodfacts.checker.ui.history.HistoryFragment;
import com.scrachx.foodfacts.checker.ui.login.LoginActivity;
import com.scrachx.foodfacts.checker.ui.main.MainActivity;
import com.scrachx.foodfacts.checker.ui.product.ProductActivity;
import com.scrachx.foodfacts.checker.ui.product.ingredients.IngredientsProductFragment;
import com.scrachx.foodfacts.checker.ui.product.nutrition.NutritionProductFragment;
import com.scrachx.foodfacts.checker.ui.product.nutritioninfo.NutritionInfoProductFragment;
import com.scrachx.foodfacts.checker.ui.product.summary.SummaryProductFragment;
import com.scrachx.foodfacts.checker.ui.scanner.ScannerActivity;
import com.scrachx.foodfacts.checker.ui.scanner.ScannerFragment;
import com.scrachx.foodfacts.checker.ui.search.SearchFragment;
import com.scrachx.foodfacts.checker.ui.splash.SplashActivity;
import com.scrachx.foodfacts.checker.ui.walkthrough.WalkthroughActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(NutritionInfoProductFragment fragment);

    void inject(IngredientsProductFragment fragment);

    void inject(NutritionProductFragment fragment);

    void inject(SummaryProductFragment fragment);

    void inject(FullScreenImageActivity activity);

    void inject(ProductActivity activity);

    void inject(ScannerFragment fragment);

    void inject(HistoryFragment fragment);

    void inject(MainActivity activity);

    void inject(WalkthroughActivity activity);

    void inject(LoginActivity activity);

    void inject(SplashActivity activity);

    void inject(AboutFragment fragment);

    void inject(SearchFragment fragment);

}
