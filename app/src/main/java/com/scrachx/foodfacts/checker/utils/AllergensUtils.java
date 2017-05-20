package com.scrachx.foodfacts.checker.utils;

import com.scrachx.foodfacts.checker.data.network.model.Product;

/**
 * Created by scots on 20/05/2017.
 */

public class AllergensUtils {

    private static final String TAG = "AllergensUtils";

    private AllergensUtils() {
        // This utility class is not publicly instantiable
    }

    public static boolean checkForAllergens(String prefAllergens, Product product) {
        if(prefAllergens.equals("palmoil")) {
            return product.getIngredientsFromPalmOilN() > 0 || product.getIngredientsFromOrThatMayBeFromPalmOilN() > 0;
        } else {
            boolean returnValue = false;
            for (String allergen: product.getAllergensHierarchy()) {
                if(allergen.contains(prefAllergens)) {
                    returnValue = true;
                }
            }
            for (String trace: product.getTracesTags()) {
                if(trace.contains(prefAllergens)) {
                    returnValue = true;
                }
            }
            return returnValue;
        }
    }

}
