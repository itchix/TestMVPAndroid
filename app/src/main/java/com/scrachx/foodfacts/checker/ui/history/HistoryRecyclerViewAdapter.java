package com.scrachx.foodfacts.checker.ui.history;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.scrachx.foodfacts.checker.R;
import com.scrachx.foodfacts.checker.data.db.model.History;
import com.scrachx.foodfacts.checker.data.module.GlideApp;
import com.scrachx.foodfacts.checker.utils.ImageUtils;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * Created by scots on 08/05/2017.
 */

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter {

    private static final int VIEW_ITEM = 1;
    private static final int VIEW_LOAD = 0;

    private Context context;
    private final List<History> productsHistory;

    public HistoryRecyclerViewAdapter(List<History> items) {
        this.productsHistory = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        int layoutResourceId = viewType == VIEW_ITEM ? R.layout.products_list_item : R.layout.progressbar_endless_list;
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutResourceId, parent, false);

        if (viewType == VIEW_ITEM) {
            return new HistoryRecyclerViewAdapter.HistoryViewHolder(v);
        } else {
            return new HistoryRecyclerViewAdapter.HistoryViewHolder(v);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return productsHistory.get(position) != null ? VIEW_ITEM : VIEW_LOAD;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        if (holder instanceof HistoryRecyclerViewAdapter.HistoryViewHolder) {
            HistoryRecyclerViewAdapter.HistoryViewHolder productHolder = (HistoryRecyclerViewAdapter.HistoryViewHolder) holder;
            GlideApp.with(context)
                    .load(productsHistory.get(position).getUrl())
                    .centerCrop()
                    .placeholder(R.drawable.ic_insert_photo_black_24dp)
                    .error(R.drawable.ic_error_black_24dp)
                    .fitCenter()
                    .into(productHolder.vProductImage);

            History history = productsHistory.get(position);

            productHolder.vProductName.setText(history.getTitle());

            StringBuilder stringBuilder = new StringBuilder();
            if (isNotEmpty(history.getBrands())) {
                stringBuilder.append(capitalize(history.getBrands().split(",")[0].trim()));
            }

            if (isNotEmpty(history.getQuantity())) {
                stringBuilder.append(" - ").append(history.getQuantity());
            }

            if (isNotEmpty(history.getGrade())) {
                productHolder.vProductGrade.setImageDrawable(ContextCompat.getDrawable(context, ImageUtils.getImageGradeColor(history.getGrade())));
            }

            productHolder.vProductDetails.setText(stringBuilder.toString());
        }

    }

    public History getHistory(int position) {
        return productsHistory.get(position);
    }

    @Override
    public int getItemCount() {
        return productsHistory.size();
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class HistoryViewHolder extends RecyclerView.ViewHolder {

        ImageView vProductImage;
        ImageView vProductGrade;
        TextView vProductName;
        TextView vProductDetails;

        HistoryViewHolder(View v) {
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