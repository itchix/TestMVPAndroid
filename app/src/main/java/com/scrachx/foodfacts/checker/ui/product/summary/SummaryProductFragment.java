package com.scrachx.foodfacts.checker.ui.product.summary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scrachx.foodfacts.checker.R;
import com.scrachx.foodfacts.checker.data.network.model.Product;
import com.scrachx.foodfacts.checker.data.network.model.State;
import com.scrachx.foodfacts.checker.ui.base.BaseFragment;
import com.scrachx.foodfacts.checker.ui.fullscreen.FullScreenImageActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.scrachx.foodfacts.checker.utils.CommonUtils.bold;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Created by scots on 08/05/2017.
 */

public class SummaryProductFragment extends BaseFragment implements SummaryProductMvpView {

    public static final String TAG = "NutritionInfoProductFragment";

    @Inject
    SummaryProductMvpPresenter<SummaryProductMvpView> mPresenter;

    @BindView(R.id.text_name_product)
    TextView nameProduct;

    @BindView(R.id.text_barcode_product)
    TextView barCodeProduct;

    @BindView(R.id.text_quantity_product)
    TextView quantityProduct;

    @BindView(R.id.text_packaging_product)
    TextView packagingProduct;

    @BindView(R.id.text_brand_product)
    TextView brandProduct;

    @BindView(R.id.text_manufacturing_product)
    TextView manufacturingProduct;

    @BindView(R.id.text_ingredients_origin_product)
    TextView ingredientsOrigin;

    @BindView(R.id.text_city_product)
    TextView cityProduct;

    @BindView(R.id.text_store_product)
    TextView storeProduct;

    @BindView(R.id.text_country_product)
    TextView countryProduct;

    @BindView(R.id.text_category_product)
    TextView categoryProduct;

    @BindView(R.id.text_label_product)
    TextView labelProduct;

    @BindView(R.id.image_view_front)
    ImageView mImageFront;

    @BindView(R.id.add_photo_label)
    TextView addPhotoLabel;

    private String mUrlImage;
    private String mBarcode;
    private boolean mSendOther = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summary_product, container, false);
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
        final State state = (State) intent.getBundleExtra("bundle").getParcelable("state");
        final Product product = state.getProduct();

        mBarcode = product.getCode();

        if (isNotBlank(product.getImageUrl())) {
            addPhotoLabel.setVisibility(View.GONE);

            Glide.with(view.getContext())
                    .load(product.getImageUrl())
                    .into(mImageFront);

            mUrlImage = product.getImageUrl();
        }
        if(isNotBlank(product.getProductName())) {
            nameProduct.setText(product.getProductName());
        } else {
            nameProduct.setVisibility(View.GONE);
        }
        if(isNotBlank(mBarcode)) {
            barCodeProduct.setText(bold(getString(R.string.txt_barcode)));
            barCodeProduct.append(' ' + mBarcode);
        } else {
            barCodeProduct.setVisibility(View.GONE);
        }
        if(isNotBlank(product.getQuantity())) {
            quantityProduct.setText(bold(getString(R.string.txt_quantity)));
            quantityProduct.append(' ' + product.getQuantity());
        } else {
            quantityProduct.setVisibility(View.GONE);
        }
        if(isNotBlank(product.getPackaging())) {
            packagingProduct.setText(bold(getString(R.string.txt_packaging)));
            packagingProduct.append(' ' + product.getPackaging());
        } else {
            packagingProduct.setVisibility(View.GONE);
        }
        if(isNotBlank(product.getBrands())) {
            brandProduct.setText(bold(getString(R.string.txt_brands)));
            brandProduct.append(' ' + product.getBrands());
        } else {
            brandProduct.setVisibility(View.GONE);
        }
        if(isNotBlank(product.getManufacturingPlaces())) {
            manufacturingProduct.setText(bold(getString(R.string.txt_manufacturing)));
            manufacturingProduct.append(' ' + product.getManufacturingPlaces());
        } else {
            manufacturingProduct.setVisibility(View.GONE);
        }

        if (isBlank(product.getOrigins())) {
            ingredientsOrigin.setVisibility(View.GONE);
        } else {
            ingredientsOrigin.setText(bold(getString(R.string.txt_ingredients_origins)));
            ingredientsOrigin.append(' ' + product.getOrigins());
        }

        String categ;
        if (isNotBlank(product.getCategories())) {
            categ = product.getCategories().replace(",", ", ");
            categoryProduct.setText(bold(getString(R.string.txt_categories)));
            categoryProduct.append(' ' + categ);
        } else {
            categoryProduct.setVisibility(View.GONE);
        }

        String labels = product.getLabels();
        if (isNotBlank(labels)) {
            labelProduct.append(bold(getString(R.string.txt_labels)));
            labelProduct.append(" ");
            for (String label : labels.split(",")) {
                labelProduct.append(label.trim());
                labelProduct.append(", ");
            }
        } else {
            labelProduct.setVisibility(View.GONE);
        }

        if(product.getCitiesTags() != null && !product.getCitiesTags().toString().trim().equals("[]")) {
            cityProduct.setText(bold(getString(R.string.txt_city)));
            cityProduct.append(' ' + product.getCitiesTags().toString().replace("[", "").replace("]", ""));
        } else {
            cityProduct.setVisibility(View.GONE);
        }
        if(isNotBlank(product.getStores())) {
            storeProduct.setText(bold(getString(R.string.txt_stores)));
            storeProduct.append(' ' + product.getStores());
        } else {
            storeProduct.setVisibility(View.GONE);
        }
        if(isNotBlank(product.getCountries())) {
            countryProduct.setText(bold(getString(R.string.txt_countries)));
            countryProduct.append(' ' + product.getCountries());
        } else {
            countryProduct.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.image_view_front)
    public void openFullScreen(View v) {
        Intent intent = FullScreenImageActivity.getStartIntent(v.getContext(), mUrlImage, mBarcode);
        startActivity(intent);
    }

}
