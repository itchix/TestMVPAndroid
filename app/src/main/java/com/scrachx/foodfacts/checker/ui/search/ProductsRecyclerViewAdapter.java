package com.scrachx.foodfacts.checker.ui.search;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scrachx.foodfacts.checker.R;
import com.scrachx.foodfacts.checker.data.module.GlideApp;
import com.scrachx.foodfacts.checker.data.network.model.Product;
import com.scrachx.foodfacts.checker.utils.ImageUtils;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * @author herau & itchix
 */
public class ProductsRecyclerViewAdapter extends RecyclerView.Adapter {

    private static final int VIEW_ITEM = 1;
    private static final int VIEW_LOAD = 0;

    private Context context;
    private final List<Product> products;

    public ProductsRecyclerViewAdapter(List<Product> items){
        this.products = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        int layoutResourceId = viewType == VIEW_ITEM ? R.layout.products_list_item: R.layout.progressbar_endless_list;
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutResourceId, parent, false);

        if (viewType == VIEW_ITEM) {
            return new ProductViewHolder(v);
        } else {
            return new ProgressViewHolder(v);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return products.get(position) != null ? VIEW_ITEM : VIEW_LOAD;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        if (holder instanceof ProductViewHolder) {
            ProductViewHolder productHolder = (ProductViewHolder) holder;
            GlideApp.with(context)
                    .load(products.get(position).getImageSmallUrl())
                    .centerCrop()
                    .placeholder(R.drawable.ic_insert_photo_black_24dp)
                    .error(R.drawable.ic_error_black_24dp)
                    .fitCenter()
                    .into(productHolder.vProductImage);

            Product product = products.get(position);

            productHolder.vProductName.setText(product.getProductName());

            StringBuilder stringBuilder = new StringBuilder();
            if (isNotEmpty(product.getBrands())) {
                stringBuilder.append(capitalize(product.getBrands().split(",")[0].trim()));
            }

            if (isNotEmpty(product.getQuantity())) {
                stringBuilder.append(" - ").append(product.getQuantity());
            }

            if (isNotEmpty(product.getNutritionGradeFr())) {
                productHolder.vProductGrade.setImageDrawable(ContextCompat.getDrawable(context, ImageUtils.getImageGradeColor(product.getNutritionGradeFr())));
            }

            productHolder.vProductDetails.setText(stringBuilder.toString());
        }

    }

    public Product getProduct(int position) {
        return products.get(position);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView vProductImage;
        ImageView vProductGrade;
        TextView vProductName;
        TextView vProductDetails;

        ProductViewHolder(View v) {
            super(v);
            vProductImage = (ImageView) v.findViewById(R.id.img_product);
            vProductGrade = (ImageView) v.findViewById(R.id.img_grade);
            vProductName = (TextView) v.findViewById(R.id.name_product);
            vProductDetails = (TextView) v.findViewById(R.id.product_details);
        }
    }

    static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progress_bar_1);
        }
    }

}

