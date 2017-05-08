package com.scrachx.foodfacts.checker.ui.product;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.scrachx.foodfacts.checker.R;
import com.scrachx.foodfacts.checker.data.network.model.NutrientLevels;
import com.scrachx.foodfacts.checker.data.network.model.NutrimentLevel;
import com.scrachx.foodfacts.checker.data.network.model.Nutriments;
import com.scrachx.foodfacts.checker.data.network.model.Product;
import com.scrachx.foodfacts.checker.data.network.model.State;
import com.scrachx.foodfacts.checker.ui.base.BaseFragment;
import com.scrachx.foodfacts.checker.ui.custom.CustomTabActivityHelper;
import com.scrachx.foodfacts.checker.ui.custom.WebViewFallback;
import com.scrachx.foodfacts.checker.utils.CustomTabsHelper;
import com.scrachx.foodfacts.checker.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.scrachx.foodfacts.checker.utils.CommonUtils.bold;
import static com.scrachx.foodfacts.checker.utils.CommonUtils.getRoundNumber;

/**
 * Created by scots on 08/05/2017.
 */

public class NutritionProductFragment extends BaseFragment implements CustomTabActivityHelper.ConnectionCallback {

    @BindView(R.id.image_grade)
    ImageView img;

    @BindView(R.id.list_nutrient_levels)
    ListView lv;

    @BindView(R.id.text_serving_size)
    TextView serving;

    @BindView(R.id.text_carbon_footprint)
    TextView carbonFootprint;

    private CustomTabActivityHelper mCustomTabActivityHelper;
    private Uri mNutritionScoreUri;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nutrition_product, container, false);
        setUnBinder(ButterKnife.bind(this, view));
        setUp(view);

        return view;
    }

    @Override
    protected void setUp(View view) {
        Intent intent = getActivity().getIntent();
        State state = (State) intent.getBundleExtra("bundle").getParcelable("state");

        final Product product = state.getProduct();
        List<NutrientLevelItem> levelItem = new ArrayList<>();

        Nutriments nutriments = product.getNutriments();

        NutrientLevels nutrientLevels = product.getNutrientLevels();
        NutrimentLevel fat = null;
        NutrimentLevel saturatedFat = null;
        NutrimentLevel sugars = null;
        NutrimentLevel salt = null;
        if(nutrientLevels != null) {
            fat = nutrientLevels.getFat();
            saturatedFat = nutrientLevels.getSaturatedFat();
            sugars = nutrientLevels.getSugars();
            salt = nutrientLevels.getSalt();
        }

        if (fat == null && salt == null && saturatedFat == null && sugars == null) {
            levelItem.add(new NutrientLevelItem(getString(R.string.txt_no_data), "", "", R.drawable.ic_error_black_24dp));
        } else {
            mCustomTabActivityHelper = new CustomTabActivityHelper();
            mCustomTabActivityHelper.setConnectionCallback(this);
            // currently only available in french translations
            mNutritionScoreUri = Uri.parse("https://fr.openfoodfacts.org/score-nutritionnel-france");
            mCustomTabActivityHelper.mayLaunchUrl(mNutritionScoreUri, null, null);

            Context context = this.getContext();

            if (fat != null) {
                String fatNutrimentLevel = fat.getLocalize(context);
                Nutriments.Nutriment nutriment = nutriments.get(Nutriments.FAT);
                levelItem.add(new NutrientLevelItem(getString(R.string.txt_fat), getRoundNumber(nutriment.getFor100g()) + " " + nutriment.getUnit(), fatNutrimentLevel, fat.getImageLevel()));
            }

            if (saturatedFat != null) {
                String saturatedFatLocalize = saturatedFat.getLocalize(context);
                Nutriments.Nutriment nutriment = nutriments.get(Nutriments.SATURATED_FAT);
                String saturatedFatValue = getRoundNumber(nutriment.getFor100g()) + " " + nutriment.getUnit();
                levelItem.add(new NutrientLevelItem(getString(R.string.txt_saturated_fat), saturatedFatValue, saturatedFatLocalize, saturatedFat.getImageLevel()));
            }

            if (sugars != null) {
                String sugarsLocalize = sugars.getLocalize(context);
                Nutriments.Nutriment nutriment = nutriments.get(Nutriments.SUGARS);
                String sugarsValue = getRoundNumber(nutriment.getFor100g()) + " " + nutriment.getUnit();
                levelItem.add(new NutrientLevelItem(getString(R.string.txt_sugars), sugarsValue, sugarsLocalize, sugars.getImageLevel()));
            }

            if (salt != null) {
                String saltLocalize = salt.getLocalize(context);
                Nutriments.Nutriment nutriment = nutriments.get(Nutriments.SALT);
                String saltValue = getRoundNumber(nutriment.getFor100g()) + " " + nutriment.getUnit();
                levelItem.add(new NutrientLevelItem(getString(R.string.txt_salt), saltValue, saltLocalize, salt.getImageLevel()));
            }

            img.setImageDrawable(ContextCompat.getDrawable(context, ImageUtils.getImageGrade(product.getNutritionGradeFr())));
            img.setOnClickListener(view1 -> {
                CustomTabsIntent customTabsIntent = CustomTabsHelper.getCustomTabsIntent(getContext(), mCustomTabActivityHelper.getSession());
                CustomTabActivityHelper.openCustomTab(NutritionProductFragment.this.getActivity(), customTabsIntent, mNutritionScoreUri, new WebViewFallback());
            });
        }

        lv.setAdapter(new NutrientLevelListAdapter(getContext(), levelItem));

        if (TextUtils.isEmpty(product.getServingSize())) {
            serving.setVisibility(View.GONE);
        } else {
            serving.append(bold(getString(R.string.txt_serving_size)));
            serving.append(" ");
            serving.append(product.getServingSize());
        }

        if (!nutriments.contains(Nutriments.CARBON_FOOTPRINT)) {
            carbonFootprint.setVisibility(View.GONE);
        } else {
            Nutriments.Nutriment carbonFootprintNutriment = nutriments.get(Nutriments.CARBON_FOOTPRINT);
            carbonFootprint.append(bold(getString(R.string.txt_carbon_footprint)));
            carbonFootprint.append(carbonFootprintNutriment.getFor100g());
            carbonFootprint.append(carbonFootprintNutriment.getUnit());
        }
    }

    @Override
    public void onCustomTabsConnected() {
        img.setClickable(true);
    }

    @Override
    public void onCustomTabsDisconnected() {
        img.setClickable(false);
    }
}

