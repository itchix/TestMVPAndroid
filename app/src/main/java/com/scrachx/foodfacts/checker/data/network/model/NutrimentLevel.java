package com.scrachx.foodfacts.checker.data.network.model;

import android.content.Context;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.scrachx.foodfacts.checker.R;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author itchix
 */
public enum NutrimentLevel {
    LOW, MODERATE, HIGH;

    @JsonCreator
    public static NutrimentLevel fromJson(String level){
        if (isBlank(level)) {
            return null;
        }

        return NutrimentLevel.valueOf(level.toUpperCase());
    }

    @JsonValue
    @Override
    public String toString() {
        return name().toLowerCase();
    }

    /**
     * get the localize text of a nutriment level
     * @param context to fetch localised strings
     * @return The localised word for the nutrition amount. If nutritionAmount is neither low,
     * moderate nor high, return nutritionAmount
     */
    public String getLocalize(Context context){
        switch (this){
            case LOW:
                return context.getString(R.string.nutrition_level_low);
            case MODERATE:
                return context.getString(R.string.nutrition_level_moderate);
            case HIGH:
                return context.getString(R.string.nutrition_level_high);
            default:
                return null;
        }
    }

    public int getImageLevel() {
        int drawable = 0;

        switch (this) {
            case MODERATE:
                drawable = R.drawable.moderate;
                break;
            case LOW:
                drawable = R.drawable.low;
                break;
            case HIGH:
                drawable = R.drawable.high;
                break;
            default:
                // no job
                break;
        }

        return drawable;
    }
}
