package com.scrachx.foodfacts.checker.ui.product.nutritioninfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scrachx.foodfacts.checker.R;
import com.scrachx.foodfacts.checker.data.network.model.Nutriments;
import com.scrachx.foodfacts.checker.data.network.model.Product;
import com.scrachx.foodfacts.checker.data.network.model.State;
import com.scrachx.foodfacts.checker.ui.base.BaseFragment;
import com.scrachx.foodfacts.checker.ui.fullscreen.FullScreenImageActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;
import static android.text.TextUtils.isEmpty;
import static com.scrachx.foodfacts.checker.data.network.model.Nutriments.CARBOHYDRATES;
import static com.scrachx.foodfacts.checker.data.network.model.Nutriments.CARBO_MAP;
import static com.scrachx.foodfacts.checker.data.network.model.Nutriments.ENERGY;
import static com.scrachx.foodfacts.checker.data.network.model.Nutriments.FAT;
import static com.scrachx.foodfacts.checker.data.network.model.Nutriments.FAT_MAP;
import static com.scrachx.foodfacts.checker.data.network.model.Nutriments.MINERALS_MAP;
import static com.scrachx.foodfacts.checker.data.network.model.Nutriments.PROTEINS;
import static com.scrachx.foodfacts.checker.data.network.model.Nutriments.PROT_MAP;
import static com.scrachx.foodfacts.checker.data.network.model.Nutriments.VITAMINS_MAP;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class NutritionInfoProductFragment extends BaseFragment implements NutritionInfoProductMvpView {

    public static final String TAG = "NutritionInfoProductFragment";

    @Inject
    NutritionInfoProductMvpPresenter<NutritionInfoProductMvpView> mPresenter;

    @BindView(R.id.text_per_portion)
    TextView mTextPerPortion;

    @BindView(R.id.image_view_nutrition)
    ImageView mImageNutrition;

    @BindView(R.id.add_photo_label)
    TextView addPhotoLabel;

    @BindView(R.id.nutriments_recycler_view)
    RecyclerView mNutrimentsRecyclerView;

    private String mUrlImage;
    private String mBarcode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nutrition_info_product, container, false);
        setUnBinder(ButterKnife.bind(this, view));
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        mPresenter.onAttach(this);
        setUp(view);

        return view;
    }

    @Override
    protected void setUp(View view) {
        Intent intent = getActivity().getIntent();
        State state = (State) intent.getBundleExtra("bundle").getParcelable("state");
        final Product product = state.getProduct();
        mBarcode = product.getCode();

        Nutriments nutriments = product.getNutriments();
        List<NutrimentItem> nutrimentItems = new ArrayList<>();

        if (isNotBlank(product.getServingSize())) {
            mTextPerPortion.setText(getString(R.string.nutriment_serving_size) + " " + product.getServingSize());
        } else {
            mTextPerPortion.setVisibility(View.GONE);
        }

        if (isNotBlank(product.getImageNutritionUrl())) {
            addPhotoLabel.setVisibility(View.GONE);

            Glide.with(view.getContext())
                    .load(product.getImageNutritionUrl())
                    .into(mImageNutrition);

            mUrlImage = product.getImageNutritionUrl();
        }

        if (nutriments == null) {
            return;
        }

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mNutrimentsRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mNutrimentsRecyclerView.setLayoutManager(mLayoutManager);

        mNutrimentsRecyclerView.setNestedScrollingEnabled(false);

        // use VERTICAL divider
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mNutrimentsRecyclerView.getContext(), VERTICAL);
        mNutrimentsRecyclerView.addItemDecoration(dividerItemDecoration);

        // Header hack
        nutrimentItems.add(new NutrimentItem(null, null, null, null));

        // Energy
        Nutriments.Nutriment energy = nutriments.get(ENERGY);
        if (energy != null) {
            nutrimentItems.add(new NutrimentItem(getString(R.string.nutrition_energy_short_name), getEnergy(energy.getFor100g()), getEnergy(energy.getForServing()), "kcal"));
        }

        // Fat
        Nutriments.Nutriment fat = nutriments.get(FAT);
        if (fat != null) {
            nutrimentItems.add(new HeaderNutrimentItem(getString(R.string.nutrition_fat), fat.getFor100g(), fat.getForServing(), fat.getUnit()));

            nutrimentItems.addAll(getNutrimentItems(nutriments, FAT_MAP));
        }

        // Carbohydrates
        Nutriments.Nutriment carbohydrates = nutriments.get(CARBOHYDRATES);
        if (carbohydrates != null) {
            nutrimentItems.add(new HeaderNutrimentItem(getString(R.string.nutrition_carbohydrate),
                    carbohydrates.getFor100g(),
                    carbohydrates.getForServing(),
                    carbohydrates.getUnit()));

            nutrimentItems.addAll(getNutrimentItems(nutriments, CARBO_MAP));
        }

        // fiber
        nutrimentItems.addAll(getNutrimentItems(nutriments, Collections.singletonMap(Nutriments.FIBER, R.string.nutrition_fiber)));

        // Proteins
        Nutriments.Nutriment proteins = nutriments.get(PROTEINS);
        if (proteins != null) {
            nutrimentItems.add(new HeaderNutrimentItem(getString(R.string.nutrition_proteins),
                    proteins.getFor100g(),
                    proteins.getForServing(),
                    proteins.getUnit()));

            nutrimentItems.addAll(getNutrimentItems(nutriments, PROT_MAP));
        }

        // salt and alcohol
        Map<String, Integer> map = new HashMap<>();
        map.put(Nutriments.SALT, R.string.nutrition_salt);
        map.put(Nutriments.SODIUM, R.string.nutrition_sodium);
        map.put(Nutriments.ALCOHOL, R.string.nutrition_alcohol);
        nutrimentItems.addAll(getNutrimentItems(nutriments, map));

        // Vitamins
        if (nutriments.hasVitamins()) {
            nutrimentItems.add(new HeaderNutrimentItem(getString(R.string.nutrition_vitamins)));

            nutrimentItems.addAll(getNutrimentItems(nutriments, VITAMINS_MAP));
        }

        // Minerals
        if (nutriments.hasMinerals()) {
            nutrimentItems.add(new HeaderNutrimentItem(getString(R.string.nutrition_minerals)));

            nutrimentItems.addAll(getNutrimentItems(nutriments, MINERALS_MAP));
        }

        RecyclerView.Adapter adapter = new NutrimentsRecyclerViewAdapter(nutrimentItems);
        mNutrimentsRecyclerView.setAdapter(adapter);
    }

    private List<NutrimentItem> getNutrimentItems(Nutriments nutriments, Map<String, Integer> nutrimentMap) {
        List<NutrimentItem> items = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : nutrimentMap.entrySet()) {
            Nutriments.Nutriment nutriment = nutriments.get(entry.getKey());
            if (nutriment != null) {
                items.add(new NutrimentItem(getString(entry.getValue()),
                        nutriment.getFor100g(), nutriment.getForServing(), nutriment.getUnit()));
            }
        }

        return items;
    }

    private String getEnergy(String value) {
        String defaultValue = "0";
        if (defaultValue.equals(value) || isEmpty(value)) {
            return defaultValue;
        }

        try {
            int energyKcal = convertKjToKcal(Integer.parseInt(value));
            return String.valueOf(energyKcal);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private int convertKjToKcal(int kj) {
        return kj != 0 ? Double.valueOf(((double) kj) / 4.1868d).intValue() : -1;
    }

    @OnClick(R.id.image_view_nutrition)
    public void openFullScreen(View v) {
        Intent intent = FullScreenImageActivity.getStartIntent(v.getContext(), mUrlImage, mBarcode);
        startActivity(intent);
    }

}
