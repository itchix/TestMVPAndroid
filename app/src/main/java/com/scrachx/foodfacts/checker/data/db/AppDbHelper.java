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

package com.scrachx.foodfacts.checker.data.db;

import com.scrachx.foodfacts.checker.data.db.model.DaoMaster;
import com.scrachx.foodfacts.checker.data.db.model.DaoSession;
import com.scrachx.foodfacts.checker.data.db.model.History;
import com.scrachx.foodfacts.checker.data.db.model.HistoryDao;
import com.scrachx.foodfacts.checker.data.db.model.Option;
import com.scrachx.foodfacts.checker.data.db.model.Question;
import com.scrachx.foodfacts.checker.data.db.model.User;
import com.scrachx.foodfacts.checker.ui.history.HistoryItem;
import com.scrachx.foodfacts.checker.ui.history_chart.HistoryStatsItem;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


/**
 * Created by janisharali on 08/12/16.
 */

@Singleton
public class AppDbHelper implements DbHelper {

    private final DaoSession mDaoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

    @Override
    public Observable<Long> insertHistory(History history) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mDaoSession.getHistoryDao().insertOrReplace(history);
            }
        });
    }

    @Override
    public Observable<HistoryItem> getHistory(int page) {
        return Observable.fromCallable(new Callable<HistoryItem>() {
            @Override
            public HistoryItem call() throws Exception {
                return new HistoryItem(mDaoSession.getHistoryDao().queryBuilder().limit(20).offset(page).orderDesc(HistoryDao.Properties.LastSeen).list(), mDaoSession.getHistoryDao().queryBuilder().count());
            }
        });
    }

    @Override
    public Observable<List<History>> getHistoryStats() {
        return Observable.fromCallable(new Callable<List<History>>() {
            @Override
            public List<History> call() throws Exception {
                return mDaoSession.getHistoryDao().loadAll();
            }
        });
    }

    @Override
    public Observable<Long> insertUser(final User user) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mDaoSession.getUserDao().insert(user);
            }
        });
    }

    @Override
    public Observable<List<User>> getAllUsers() {
        return Observable.fromCallable(new Callable<List<User>>() {
            @Override
            public List<User> call() throws Exception {
                return mDaoSession.getUserDao().loadAll();
            }
        });
    }

    @Override
    public Observable<List<Question>> getAllQuestions() {
        return Observable.fromCallable(new Callable<List<Question>>() {
            @Override
            public List<Question> call() throws Exception {
                return mDaoSession.getQuestionDao().loadAll();
            }
        });
    }

    @Override
    public Observable<Boolean> isQuestionEmpty() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !(mDaoSession.getQuestionDao().count() > 0);
            }
        });
    }

    @Override
    public Observable<Boolean> isOptionEmpty() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !(mDaoSession.getOptionDao().count() > 0);
            }
        });
    }

    @Override
    public Observable<Boolean> saveQuestion(final Question question) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getQuestionDao().insert(question);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> saveOption(final Option option) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getOptionDao().insertInTx(option);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> saveQuestionList(final List<Question> questionList) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getQuestionDao().insertInTx(questionList);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> saveOptionList(final List<Option> optionList) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getOptionDao().insertInTx(optionList);
                return true;
            }
        });
    }

}
