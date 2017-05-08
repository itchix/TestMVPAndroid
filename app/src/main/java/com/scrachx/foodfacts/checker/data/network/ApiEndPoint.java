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

package com.scrachx.foodfacts.checker.data.network;

import com.scrachx.foodfacts.checker.BuildConfig;

/**
 * Created by amitshekhar on 01/02/17.
 */

public final class ApiEndPoint {

    public static final String ENDPOINT_SEARCH = BuildConfig.BASE_URL + "/cgi/search.pl?search_simple=1&json=1&action=process";
    public static final String ENDPOINT_BARCODE = BuildConfig.BASE_URL + "/api/v0/produit/{barcode}.json";
    public static final String ENDPOINT_LOGIN_LOGOUT = BuildConfig.BASE_URL + "/cgi/session.pl";

    private ApiEndPoint() {
        // This class is not publicly instantiable
    }

}
