package com.scrachx.foodfacts.checker.ui.product;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
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

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.scrachx.foodfacts.checker.utils.CommonUtils.bold;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class IngredientsProductFragment extends BaseFragment {

    @BindView(R.id.text_ingredient_product)
    TextView ingredientsProduct;

    @BindView(R.id.text_substance_product)
    TextView substanceProduct;

    @BindView(R.id.text_trace_product)
    TextView traceProduct;

    @BindView(R.id.text_additive_product)
    TextView additiveProduct;

    @BindView(R.id.text_palm_oil_product)
    TextView palmOilProduct;

    @BindView(R.id.text_may_be_from_palm_oil_product)
    TextView mayBeFromPalmOilProduct;

    @BindView(R.id.imageViewIngredients)
    ImageView mImageIngredients;

    @BindView(R.id.add_photo_label)
    TextView addPhotoLabel;

    private String mUrlImage;
    private State mState;
    private String mBarcode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredients_product, container, false);
        setUnBinder(ButterKnife.bind(this, view));
        setUp(view);

        return view;
    }

    @Override
    protected void setUp(View view) {
        Intent intent = getActivity().getIntent();
        mState = (State) intent.getBundleExtra("bundle").getParcelable("state");

        final Product product = mState.getProduct();
        mBarcode = product.getCode();

        if (isNotBlank(product.getImageIngredientsUrl())) {
            addPhotoLabel.setVisibility(View.GONE);

            Glide.with(view.getContext())
                    .load(product.getImageIngredientsUrl())
                    .into(mImageIngredients);

            mUrlImage = product.getImageIngredientsUrl();
        }

        if(mState != null && product.getIngredientsText() != null) {
            SpannableStringBuilder txtIngredients = new SpannableStringBuilder(product.getIngredientsText().replace("_",""));
            if(!txtIngredients.toString().substring(txtIngredients.toString().indexOf(":")).trim().isEmpty()) {
                ingredientsProduct.setText(txtIngredients);
            } else {
                ingredientsProduct.setVisibility(View.GONE);
            }
        }

        // TODO
        substanceProduct.setVisibility(View.GONE);

        String traces;
        if (product.getTraces() == null) {
            traceProduct.setVisibility(View.GONE);
        } else {
            traces = product.getTraces().replace(",", ", ");
            if(traces.isEmpty()) {
                traceProduct.setVisibility(View.GONE);
            } else {
                traceProduct.append(bold(getString(R.string.txt_traces)));
                traceProduct.append(" ");
                traceProduct.append(traces);
            }
        }

        if(!product.getAdditivesTags().isEmpty()) {
            additiveProduct.setMovementMethod(LinkMovementMethod.getInstance());
            additiveProduct.append(bold(getString(R.string.txt_additives)));
            additiveProduct.append(" ");

            for (String tag : product.getAdditivesTags()) {
                String tagWithoutLocale = tag.replaceAll("(en:|fr:)", "").toUpperCase();
                additiveProduct.append(tagWithoutLocale);
            }
        } else {
            additiveProduct.setVisibility(View.GONE);
        }

        if (product.getIngredientsFromPalmOilN() == 0 && product.getIngredientsFromOrThatMayBeFromPalmOilN() == 0) {
            palmOilProduct.setVisibility(View.GONE);
            mayBeFromPalmOilProduct.setVisibility(View.GONE);
        } else {
            if (!product.getIngredientsFromPalmOilTags().isEmpty()) {
                palmOilProduct.append(bold(getString(R.string.txt_palm_oil_product)));
                palmOilProduct.append(" ");
                palmOilProduct.append(product.getIngredientsFromPalmOilTags().toString().replaceAll("[\\[,\\]]", ""));
            } else {
                palmOilProduct.setVisibility(View.GONE);
            }
            if (!product.getIngredientsThatMayBeFromPalmOilTags().isEmpty()) {
                mayBeFromPalmOilProduct.append(bold(getString(R.string.txt_may_be_from_palm_oil_product)));
                mayBeFromPalmOilProduct.append(" ");
                mayBeFromPalmOilProduct.append(product.getIngredientsThatMayBeFromPalmOilTags().toString().replaceAll("[\\[,\\]]", ""));
            } else {
                mayBeFromPalmOilProduct.setVisibility(View.GONE);
            }
        }
    }

}
