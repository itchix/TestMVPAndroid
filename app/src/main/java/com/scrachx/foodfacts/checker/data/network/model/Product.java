package com.scrachx.foodfacts.checker.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.StdConverter;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class ProductStringConverter extends StdConverter<String, String> {
    public String convert(String value) {
        return StringEscapeUtils.unescapeHtml4(value).replace("\\'", "'");
    }
}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable, Parcelable {

    @JsonProperty("image_small_url")
    private String imageSmallUrl;
    @JsonProperty("image_nutrition_url")
    private String imageNutritionUrl;
    @JsonProperty("image_front_url")
    private String imageFrontUrl;
    @JsonProperty("image_ingredients_url")
    private String imageIngredientsUrl;
    private String url;
    private String code;
    @JsonProperty("traces_tags")
    private List<String> tracesTags = new ArrayList<>();
    @JsonProperty("ingredients_that_may_be_from_palm_oil_tags")
    private List<String> ingredientsThatMayBeFromPalmOilTags = new ArrayList<>();
    @JsonProperty("additives_tags")
    private List<String> additivesTags = new ArrayList<>();
    @JsonProperty("allergens_hierarchy")
    private List<String> allergensHierarchy = new ArrayList<>();
    @JsonProperty("manufacturing_places")
    private String manufacturingPlaces;
    private Nutriments nutriments;
    @JsonProperty("ingredients_from_palm_oil_tags")
    private List<Object> ingredientsFromPalmOilTags = new ArrayList<>();
    @JsonProperty("brands_tags")
    private List<String> brandsTags = new ArrayList<>();
    private String traces;
    private String categories;
    @JsonProperty("ingredients_text")
    @JsonDeserialize(converter = ProductStringConverter.class)
    private String ingredientsText;
    @JsonProperty("product_name")
    @JsonDeserialize(converter = ProductStringConverter.class)
    private String productName;
    @JsonProperty("generic_name")
    @JsonDeserialize(converter = ProductStringConverter.class)
    private String genericName;
    @JsonProperty("ingredients_from_or_that_may_be_from_palm_oil_n")
    private long ingredientsFromOrThatMayBeFromPalmOilN;
    @JsonProperty("serving_size")
    private String servingSize;
    @JsonProperty("last_modified_by")
    private String lastModifiedBy;
    private String allergens;
    private String origins;
    private String stores;
    @JsonProperty("nutrition_grade_fr")
    private String nutritionGradeFr;
    @JsonProperty("nutrient_levels")
    private NutrientLevels nutrientLevels;
    private String countries;
    private String brands;
    private String packaging;
    private String labels;
    @JsonProperty("cities_tags")
    private List<Object> citiesTags = new ArrayList<>();
    private String quantity;
    @JsonProperty("ingredients_from_palm_oil_n")
    private long ingredientsFromPalmOilN;
    @JsonProperty("image_url")
    private String imageUrl;

    public Product() {

    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * @return The imageSmallUrl
     */
    public String getImageSmallUrl() {
        return imageSmallUrl;
    }

    /**
     * @return The imageFrontUrl
     */
    public String getImageFrontUrl() {
        return imageFrontUrl;
    }

    /**
     * @return The imageIngredientsUrl
     */
    public String getImageIngredientsUrl() {
        return imageIngredientsUrl;
    }

    /**
     * @return The imageNutritionUrl
     */
    public String getImageNutritionUrl() {
        return imageNutritionUrl;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return The code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return The tracesTags
     */
    public List<String> getTracesTags() {
        return tracesTags;
    }

    /**
     * @return The ingredientsThatMayBeFromPalmOilTags
     */
    public List<String> getIngredientsThatMayBeFromPalmOilTags() {
        return ingredientsThatMayBeFromPalmOilTags;
    }

    /**
     * @return The additivesTags
     */
    public List<String> getAdditivesTags() {
        return additivesTags;
    }


    /**
     * @return The allergensHierarchy
     */
    public List<String> getAllergensHierarchy() {
        return allergensHierarchy;
    }


    /**
     * @return The manufacturingPlaces
     */
    public String getManufacturingPlaces() {
        return manufacturingPlaces;
    }


    /**
     * @return The nutriments
     */
    public Nutriments getNutriments() {
        return nutriments;
    }


    /**
     * @return The ingredientsFromPalmOilTags
     */
    public List<Object> getIngredientsFromPalmOilTags() {
        return ingredientsFromPalmOilTags;
    }


    /**
     * @return The brandsTags
     */
    public List<String> getBrandsTags() {
        return brandsTags;
    }


    /**
     * @return The traces
     */
    public String getTraces() {
        return traces;
    }


    /**
     * @return The categories
     */
    public String getCategories() {
        return categories;
    }


    /**
     * @return The ingredientsText
     */
    public String getIngredientsText() {
        return ingredientsText;
    }


    /**
     * @return The productName
     */
    public String getProductName() {
        return productName;
    }


    /**
     * @return The genericName
     */
    public String getGenericName() {
        return genericName;
    }

    /**
     * @return The ingredientsFromOrThatMayBeFromPalmOilN
     */
    public long getIngredientsFromOrThatMayBeFromPalmOilN() {
        return ingredientsFromOrThatMayBeFromPalmOilN;
    }


    /**
     * @return The servingSize
     */


    public String getServingSize() {
        return servingSize;
    }


    /**
     * @return The allergens
     */
    public String getAllergens() {
        return allergens;
    }


    /**
     * @return The origins
     */
    public String getOrigins() {
        return origins;
    }


    /**
     * @return The stores
     */
    public String getStores() {
        return stores;
    }


    /**
     * @return The nutritionGradeFr
     */
    public String getNutritionGradeFr() {
        return nutritionGradeFr;
    }


    /**
     * @return The nutrientLevels
     */
    public NutrientLevels getNutrientLevels() {
        return nutrientLevels;
    }


    /**
     * @return The countries
     */
    public String getCountries() {
        return countries;
    }


    /**
     * @return The brands
     */
    public String getBrands() {
        return brands;
    }


    /**
     * @return The packaging
     */
    public String getPackaging() {
        return packaging;
    }


    /**
     * @return The labels
     */
    public String getLabels() {
        return labels;
    }


    /**
     * @return The citiesTags
     */
    public List<Object> getCitiesTags() {
        return citiesTags;
    }


    /**
     * @return The quantity
     */
    public String getQuantity() {
        return quantity;
    }


    /**
     * @return The ingredientsFromPalmOilN
     */
    public long getIngredientsFromPalmOilN() {
        return ingredientsFromPalmOilN;
    }


    /**
     * @return The imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", code)
                .append("productName", productName)
                .toString();
    }

    protected Product(Parcel in) {
        imageSmallUrl = in.readString();
        imageNutritionUrl = in.readString();
        imageFrontUrl = in.readString();
        imageIngredientsUrl = in.readString();
        url = in.readString();
        code = in.readString();
        if (in.readByte() == 0x01) {
            tracesTags = new ArrayList<String>();
            in.readList(tracesTags, String.class.getClassLoader());
        } else {
            tracesTags = null;
        }
        if (in.readByte() == 0x01) {
            ingredientsThatMayBeFromPalmOilTags = new ArrayList<String>();
            in.readList(ingredientsThatMayBeFromPalmOilTags, String.class.getClassLoader());
        } else {
            ingredientsThatMayBeFromPalmOilTags = null;
        }
        if (in.readByte() == 0x01) {
            additivesTags = new ArrayList<String>();
            in.readList(additivesTags, String.class.getClassLoader());
        } else {
            additivesTags = null;
        }
        if (in.readByte() == 0x01) {
            allergensHierarchy = new ArrayList<String>();
            in.readList(allergensHierarchy, String.class.getClassLoader());
        } else {
            allergensHierarchy = null;
        }
        manufacturingPlaces = in.readString();
        nutriments = (Nutriments) in.readValue(Nutriments.class.getClassLoader());
        if (in.readByte() == 0x01) {
            ingredientsFromPalmOilTags = new ArrayList<Object>();
            in.readList(ingredientsFromPalmOilTags, Object.class.getClassLoader());
        } else {
            ingredientsFromPalmOilTags = null;
        }
        if (in.readByte() == 0x01) {
            brandsTags = new ArrayList<String>();
            in.readList(brandsTags, String.class.getClassLoader());
        } else {
            brandsTags = null;
        }
        traces = in.readString();
        categories = in.readString();
        ingredientsFromOrThatMayBeFromPalmOilN = in.readLong();
        servingSize = in.readString();
        lastModifiedBy = in.readString();
        allergens = in.readString();
        origins = in.readString();
        stores = in.readString();
        nutritionGradeFr = in.readString();
        nutrientLevels = (NutrientLevels) in.readValue(NutrientLevels.class.getClassLoader());
        countries = in.readString();
        brands = in.readString();
        packaging = in.readString();
        labels = in.readString();
        if (in.readByte() == 0x01) {
            citiesTags = new ArrayList<Object>();
            in.readList(citiesTags, Object.class.getClassLoader());
        } else {
            citiesTags = null;
        }
        quantity = in.readString();
        ingredientsFromPalmOilN = in.readLong();
        imageUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageSmallUrl);
        dest.writeString(imageNutritionUrl);
        dest.writeString(imageFrontUrl);
        dest.writeString(imageIngredientsUrl);
        dest.writeString(url);
        dest.writeString(code);
        if (tracesTags == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(tracesTags);
        }
        if (ingredientsThatMayBeFromPalmOilTags == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(ingredientsThatMayBeFromPalmOilTags);
        }
        if (additivesTags == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(additivesTags);
        }
        if (allergensHierarchy == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(allergensHierarchy);
        }
        dest.writeString(manufacturingPlaces);
        dest.writeValue(nutriments);
        if (ingredientsFromPalmOilTags == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(ingredientsFromPalmOilTags);
        }
        if (brandsTags == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(brandsTags);
        }
        dest.writeString(traces);
        dest.writeString(categories);
        dest.writeLong(ingredientsFromOrThatMayBeFromPalmOilN);
        dest.writeString(servingSize);
        dest.writeString(lastModifiedBy);
        dest.writeString(allergens);
        dest.writeString(origins);
        dest.writeString(stores);
        dest.writeString(nutritionGradeFr);
        dest.writeValue(nutrientLevels);
        dest.writeString(countries);
        dest.writeString(brands);
        dest.writeString(packaging);
        dest.writeString(labels);
        if (citiesTags == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(citiesTags);
        }
        dest.writeString(quantity);
        dest.writeLong(ingredientsFromPalmOilN);
        dest.writeString(imageUrl);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

}
