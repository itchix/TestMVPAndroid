package com.scrachx.foodfacts.checker.data.network.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static android.text.TextUtils.isEmpty;

/**
 * JSON representation of the product nutriments entry
 *
 * @see <a href="http://en.wiki.openfoodfacts.org/API#JSON_interface">JSON Structure</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Nutriments implements Serializable {

    private static final String DEFAULT_UNIT = "g";

    public final static String ENERGY ="energy";
    public static final String ENERGY_FROM_FAT = "energy-from-fat";
    public static final String FAT = "fat";
    public static final String SATURATED_FAT = "saturated-fat";
    public static final String BUTYRIC_ACID = "butyric-acid";
    public static final String CAPROIC_ACID = "caproic-acid";
    public static final String CAPRYLIC_ACID = "caprylic-acid";
    public static final String CAPRIC_ACID = "capric-acid";
    public static final String LAURIC_ACID = "lauric-acid";
    public static final String MYRISTIC_ACID = "myristic-acid";
    public static final String PALMITIC_ACID = "palmitic-acid";
    public static final String STEARIC_ACID = "stearic-acid";
    public static final String ARACHIDIC_ACID = "arachidic-acid";
    public static final String BEHENIC_ACID = "behenic-acid";
    public static final String LIGNOCERIC_ACID = "lignoceric-acid";
    public static final String CEROTIC_ACID = "cerotic-acid";
    public static final String MONTANIC_ACID = "montanic-acid";
    public static final String MELISSIC_ACID = "melissic-acid";
    public static final String MONOUNSATURATED_FAT = "monounsaturated-fat";
    public static final String POLYUNSATURATED_FAT = "polyunsaturated-fat";
    public static final String OMEGA_3_FAT = "omega-3-fat";
    public static final String ALPHA_LINOLENIC_ACID = "alpha-linolenic-acid";
    public static final String EICOSAPENTAENOIC_ACID = "eicosapentaenoic-acid";
    public static final String DOCOSAHEXAENOIC_ACID = "docosahexaenoic-acid";
    public static final String OMEGA_6_FAT = "omega-6-fat";
    public static final String LINOLEIC_ACID = "linoleic-acid";
    public static final String ARACHIDONIC_ACID = "arachidonic-acid";
    public static final String GAMMA_LINOLENIC_ACID = "gamma-linolenic-acid";
    public static final String DIHOMO_GAMMA_LINOLENIC_ACID = "dihomo-gamma-linolenic-acid";
    public static final String OMEGA_9_FAT = "omega-9-fat";
    public static final String OLEIC_ACID = "oleic-acid";
    public static final String ELAIDIC_ACID = "elaidic-acid";
    public static final String GONDOIC_ACID = "gondoic-acid";
    public static final String MEAD_ACID = "mead-acid";
    public static final String ERUCIC_ACID = "erucic-acid";
    public static final String NERVONIC_ACID = "nervonic-acid";
    public static final String TRANS_FAT = "trans-fat";
    public static final String CHOLESTEROL = "cholesterol";
    public static final String CARBOHYDRATES = "carbohydrates";
    public static final String SUGARS = "sugars";
    public static final String SUCROSE = "sucrose";
    public static final String GLUCOSE = "glucose";
    public static final String FRUCTOSE = "fructose";
    public static final String LACTOSE = "lactose";
    public static final String MALTOSE = "maltose";
    public static final String MALTODEXTRINS = "maltodextrins";
    public static final String STARCH = "starch";
    public static final String POLYOLS = "polyols";
    public static final String FIBER = "fiber";
    public static final String PROTEINS = "proteins";
    public static final String CASEIN = "casein";
    public static final String SERUM_PROTEINS = "serum-proteins";
    public static final String NUCLEOTIDES = "nucleotides";
    public static final String SALT = "salt";
    public static final String SODIUM = "sodium";
    public static final String ALCOHOL = "alcohol";
    public static final String VITAMIN_A = "vitamin-a";
    public static final String BETA_CAROTENE = "beta-carotene";
    public static final String VITAMIN_D = "vitamin-d";
    public static final String VITAMIN_E = "vitamin-e";
    public static final String VITAMIN_K = "vitamin-k";
    public static final String VITAMIN_C = "vitamin-c";
    public static final String VITAMIN_B1 = "vitamin-b1";
    public static final String VITAMIN_B2 = "vitamin-b2";
    public static final String VITAMIN_PP = "vitamin-pp";
    public static final String VITAMIN_B6 = "vitamin-b6";
    public static final String VITAMIN_B9 = "vitamin-b9";
    public static final String WATER_HARDNESS = "water-hardness";
    public static final String GLYCEMIC_INDEX = "glycemic-index";
    public static final String NUTRITION_SCORE_UK = "nutrition-score-uk";
    public static final String NUTRITION_SCORE_FR = "nutrition-score-fr";
    public static final String CARBON_FOOTPRINT = "carbon-footprint";
    public static final String CHLOROPHYL = "chlorophyl";
    public static final String COCOA = "cocoa";
    public static final String COLLAGEN_MEAT_PROTEIN_RATIO = "collagen-meat-protein-ratio";
    public static final String FRUITS_VEGETABLES_NUTS = "fruits-vegetables-nuts";
    public static final String PH = "ph";
    public static final String TAURINE = "taurine";
    public static final String CAFFEINE = "caffeine";
    public static final String IODINE = "iodine";
    public static final String MOLYBDENUM = "molybdenum";
    public static final String CHROMIUM = "chromium";
    public static final String SELENIUM = "selenium";
    public static final String FLUORIDE = "fluoride";
    public static final String MANGANESE = "manganese";
    public static final String COPPER = "copper";
    public static final String ZINC = "zinc";
    public static final String VITAMIN_B12 = "vitamin-b12";
    public static final String BIOTIN = "biotin";
    public static final String PANTOTHENIC_ACID = "pantothenic-acid";
    public static final String SILICA = "silica";
    public static final String BICARBONATE = "bicarbonate";
    public static final String POTASSIUM = "potassium";
    public static final String CHLORIDE = "chloride";
    public static final String CALCIUM = "calcium";
    public static final String PHOSPHORUS = "phosphorus";
    public static final String IRON = "iron";
    public static final String MAGNESIUM = "magnesium";

    public static final Map<String, Integer> MINERALS_MAP = new HashMap<String, Integer>() {{
        put(Nutriments.SILICA, com.scrachx.foodfacts.checker.R.string.silica);
        put(Nutriments.BICARBONATE, com.scrachx.foodfacts.checker.R.string.bicarbonate);
        put(Nutriments.POTASSIUM, com.scrachx.foodfacts.checker.R.string.potassium);
        put(Nutriments.CHLORIDE, com.scrachx.foodfacts.checker.R.string.chloride);
        put(Nutriments.CALCIUM, com.scrachx.foodfacts.checker.R.string.calcium);
        put(Nutriments.PHOSPHORUS, com.scrachx.foodfacts.checker.R.string.phosphorus);
        put(Nutriments.IRON, com.scrachx.foodfacts.checker.R.string.iron);
        put(Nutriments.MAGNESIUM, com.scrachx.foodfacts.checker.R.string.magnesium);
        put(Nutriments.ZINC, com.scrachx.foodfacts.checker.R.string.zinc);
        put(Nutriments.COPPER, com.scrachx.foodfacts.checker.R.string.copper);
        put(Nutriments.MANGANESE, com.scrachx.foodfacts.checker.R.string.manganese);
        put(Nutriments.FLUORIDE, com.scrachx.foodfacts.checker.R.string.fluoride);
        put(Nutriments.SELENIUM, com.scrachx.foodfacts.checker.R.string.selenium);
        put(Nutriments.CHROMIUM, com.scrachx.foodfacts.checker.R.string.chromium);
        put(Nutriments.MOLYBDENUM, com.scrachx.foodfacts.checker.R.string.molybdenum);
        put(Nutriments.IODINE, com.scrachx.foodfacts.checker.R.string.iodine);
        put(Nutriments.CAFFEINE, com.scrachx.foodfacts.checker.R.string.caffeine);
        put(Nutriments.TAURINE, com.scrachx.foodfacts.checker.R.string.taurine);
        put(Nutriments.PH, com.scrachx.foodfacts.checker.R.string.ph);
        put(Nutriments.FRUITS_VEGETABLES_NUTS, com.scrachx.foodfacts.checker.R.string.fruits_vegetables_nuts);
        put(Nutriments.COLLAGEN_MEAT_PROTEIN_RATIO, com.scrachx.foodfacts.checker.R.string.collagen_meat_protein_ratio);
        put(Nutriments.COCOA, com.scrachx.foodfacts.checker.R.string.cocoa);
        put(Nutriments.CHLOROPHYL, com.scrachx.foodfacts.checker.R.string.chlorophyl);
    }};

    public static final Map<String, Integer> FAT_MAP = new HashMap<String, Integer>(){{
        put(Nutriments.SATURATED_FAT, com.scrachx.foodfacts.checker.R.string.nutrition_satured_fat);
        put(Nutriments.MONOUNSATURATED_FAT, com.scrachx.foodfacts.checker.R.string.nutrition_monounsaturatedFat);
        put(Nutriments.POLYUNSATURATED_FAT, com.scrachx.foodfacts.checker.R.string.nutrition_polyunsaturatedFat);
        put(Nutriments.OMEGA_3_FAT, com.scrachx.foodfacts.checker.R.string.nutrition_omega3);
        put(Nutriments.OMEGA_6_FAT, com.scrachx.foodfacts.checker.R.string.nutrition_omega6);
        put(Nutriments.OMEGA_9_FAT, com.scrachx.foodfacts.checker.R.string.nutrition_omega9);
        put(Nutriments.TRANS_FAT, com.scrachx.foodfacts.checker.R.string.nutrition_trans_fat);
        put(Nutriments.CHOLESTEROL, com.scrachx.foodfacts.checker.R.string.nutrition_cholesterol);
    }};

    public static final Map<String, Integer> CARBO_MAP = new HashMap<String, Integer>(){{
        put(Nutriments.SUGARS, com.scrachx.foodfacts.checker.R.string.nutrition_sugars);
        put(Nutriments.SUCROSE, com.scrachx.foodfacts.checker.R.string.nutrition_sucrose);
        put(Nutriments.GLUCOSE, com.scrachx.foodfacts.checker.R.string.nutrition_glucose);
        put(Nutriments.FRUCTOSE, com.scrachx.foodfacts.checker.R.string.nutrition_fructose);
        put(Nutriments.LACTOSE, com.scrachx.foodfacts.checker.R.string.nutrition_lactose);
        put(Nutriments.MALTOSE, com.scrachx.foodfacts.checker.R.string.nutrition_maltose);
        put(Nutriments.MALTODEXTRINS, com.scrachx.foodfacts.checker.R.string.nutrition_maltodextrins);
    }};

    public static final Map<String, Integer> PROT_MAP = new HashMap<String, Integer>(){{
        put(Nutriments.CASEIN, com.scrachx.foodfacts.checker.R.string.nutrition_casein);
        put(Nutriments.SERUM_PROTEINS, com.scrachx.foodfacts.checker.R.string.nutrition_serum_proteins);
        put(Nutriments.NUCLEOTIDES, com.scrachx.foodfacts.checker.R.string.nutrition_nucleotides);
    }};

    public static final Map<String, Integer> VITAMINS_MAP = new HashMap<String, Integer>(){{
        put(Nutriments.VITAMIN_A, com.scrachx.foodfacts.checker.R.string.vitamin_a);
        put(Nutriments.BETA_CAROTENE, com.scrachx.foodfacts.checker.R.string.vitamin_a);
        put(Nutriments.VITAMIN_D, com.scrachx.foodfacts.checker.R.string.vitamin_d);
        put(Nutriments.VITAMIN_E, com.scrachx.foodfacts.checker.R.string.vitamin_e);
        put(Nutriments.VITAMIN_K, com.scrachx.foodfacts.checker.R.string.vitamin_k);
        put(Nutriments.VITAMIN_C, com.scrachx.foodfacts.checker.R.string.vitamin_c);
        put(Nutriments.VITAMIN_B1, com.scrachx.foodfacts.checker.R.string.vitamin_b1);
        put(Nutriments.VITAMIN_B2, com.scrachx.foodfacts.checker.R.string.vitamin_b2);
        put(Nutriments.VITAMIN_PP, com.scrachx.foodfacts.checker.R.string.vitamin_pp);
        put(Nutriments.VITAMIN_B6, com.scrachx.foodfacts.checker.R.string.vitamin_b6);
        put(Nutriments.VITAMIN_B9, com.scrachx.foodfacts.checker.R.string.vitamin_b9);
        put(Nutriments.VITAMIN_B12, com.scrachx.foodfacts.checker.R.string.vitamin_b12);
        put(Nutriments.BIOTIN, com.scrachx.foodfacts.checker.R.string.biotin);
        put(Nutriments.PANTOTHENIC_ACID, com.scrachx.foodfacts.checker.R.string.pantothenic_acid);
    }};

    private Map<String, Object> additionalProperties = new HashMap<>();
    private boolean containsVitamins;
    private boolean containsMinerals;

    public Nutriment get(String nutrimentName){
        if (!additionalProperties.containsKey(nutrimentName)) {
            return null;
        }

        return new Nutriment(additionalProperties.get(nutrimentName).toString(), get100g(nutrimentName), getServing(nutrimentName), getUnit(nutrimentName));
    }

    public String getUnit(String nutrimentName){
        String unit = ((String) additionalProperties.get(nutrimentName + "_unit"));
        return isEmpty(unit) ? DEFAULT_UNIT : unit;
    }

    public String getServing(String nutrimentName){
        return additionalProperties.get(nutrimentName + "_serving").toString();
    }

    public String get100g(String nutrimentName){
        return additionalProperties.get(nutrimentName + "_100g").toString();
    }

    public Double getValue(String nutrimentName){
        return ((Double) additionalProperties.get(nutrimentName + "_value"));
    }

    public boolean contains(String nutrimentName){
        return additionalProperties.containsKey(nutrimentName);
    }

    public boolean hasVitamins() {
        return containsVitamins;
    }

    public boolean hasMinerals() {
        return containsMinerals;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);

        if (VITAMINS_MAP.containsKey(name)) {
            containsVitamins = true;
        } else if (MINERALS_MAP.containsKey(name)) {
            containsMinerals = true;
        }
    }

    public static class Nutriment {

        private final String value;
        private final String for100g;
        private final String forServing;
        private final String unit;

        Nutriment(String name, String for100g, String forServing, String unit) {
            this.value = name;
            this.for100g = for100g;
            this.forServing = forServing;
            this.unit = unit;
        }

        public String getValue() {
            return value;
        }

        public String getFor100g() {
            return for100g;
        }

        public String getForServing() {
            return forServing;
        }

        public String getUnit() {
            return unit;
        }
    }

}
