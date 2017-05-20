package com.scrachx.foodfacts.checker.ui.allergens;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.scrachx.foodfacts.checker.R;

/**
 * Created by scots on 20/05/2017.
 */

public class AllergensFragment extends PreferenceFragmentCompat {

    public static final String TAG = "AllergensFragment";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences_allergens, rootKey);
    }

}
