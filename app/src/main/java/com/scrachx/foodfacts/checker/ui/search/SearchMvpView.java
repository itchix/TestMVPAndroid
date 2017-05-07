package com.scrachx.foodfacts.checker.ui.search;

import com.scrachx.foodfacts.checker.data.network.model.Product;
import com.scrachx.foodfacts.checker.ui.base.MvpView;

import java.util.List;

/**
 * Created by scots on 07/05/2017.
 */

public interface SearchMvpView extends MvpView {

    void refreshResults(List<Product> searchResult, int numberOfProducts);

}