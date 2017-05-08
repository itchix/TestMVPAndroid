package com.scrachx.foodfacts.checker.ui.scanner;

import com.scrachx.foodfacts.checker.data.network.model.State;
import com.scrachx.foodfacts.checker.ui.base.MvpView;

/**
 * Created by scots on 07/05/2017.
 */

public interface ScannerMvpView extends MvpView {

    void openPageProduct(State stateProduct);

}
