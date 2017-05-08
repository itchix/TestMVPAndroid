package com.scrachx.foodfacts.checker.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NutrientLevels implements Serializable, Parcelable {

    private NutrimentLevel salt;
    private NutrimentLevel fat;
    private NutrimentLevel sugars;
    @JsonProperty("saturated-fat")
    private NutrimentLevel saturatedFat;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public NutrientLevels() {

    }

    /**
     *
     * @return
     * The salt
     */
    public NutrimentLevel getSalt() {
        return salt;
    }

    /**
     *
     * @param salt
     * The salt
     */
    public void setSalt(NutrimentLevel salt) {
        this.salt = salt;
    }

    /**
     *
     * @return
     * The fat
     */
    public NutrimentLevel getFat() {
        return fat;
    }

    /**
     *
     * @param fat
     * The fat
     */
    public void setFat(NutrimentLevel fat) {
        this.fat = fat;
    }

    /**
     *
     * @return
     * The sugars
     */
    public NutrimentLevel getSugars() {
        return sugars;
    }

    /**
     *
     * @param sugars
     * The sugars
     */
    public void setSugars(NutrimentLevel sugars) {
        this.sugars = sugars;
    }

    /**
     *
     * @return
     * The saturatedFat
     */
    public NutrimentLevel getSaturatedFat() {
        return saturatedFat;
    }

    /**
     *
     * @param saturatedFat
     * The saturated-fat
     */
    public void setSaturatedFat(NutrimentLevel saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "NutrientLevels{" +
                "salt='" + salt + '\'' +
                ", fat='" + fat + '\'' +
                ", sugars='" + sugars + '\'' +
                ", saturatedFat='" + saturatedFat + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    protected NutrientLevels(Parcel in) {
        salt = (NutrimentLevel) in.readValue(NutrimentLevel.class.getClassLoader());
        fat = (NutrimentLevel) in.readValue(NutrimentLevel.class.getClassLoader());
        sugars = (NutrimentLevel) in.readValue(NutrimentLevel.class.getClassLoader());
        saturatedFat = (NutrimentLevel) in.readValue(NutrimentLevel.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(salt);
        dest.writeValue(fat);
        dest.writeValue(sugars);
        dest.writeValue(saturatedFat);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<NutrientLevels> CREATOR = new Parcelable.Creator<NutrientLevels>() {
        @Override
        public NutrientLevels createFromParcel(Parcel in) {
            return new NutrientLevels(in);
        }

        @Override
        public NutrientLevels[] newArray(int size) {
            return new NutrientLevels[size];
        }
    };

}
