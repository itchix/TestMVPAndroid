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

import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.scrachx.foodfacts.checker.data.network.model.Search;
import com.scrachx.foodfacts.checker.data.network.model.SearchRequest;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


@Singleton
public class AppApiHelper implements ApiHelper {

    @Inject
    public AppApiHelper() {

    }

    @Override
    public Observable<Search> searchProductByName(SearchRequest request) {
        Map<String, String> pathMap = new HashMap<>();
        pathMap.put("search_terms", request.getSearch_terms());
        pathMap.put("page", String.valueOf(request.getPage()));
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_SEARCH)
                .addQueryParameter(pathMap)
                .build()
                .getObjectObservable(Search.class);
    }

}

