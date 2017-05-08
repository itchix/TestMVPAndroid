package com.scrachx.foodfacts.checker.ui.product;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scrachx.foodfacts.checker.R;

import java.util.List;

/**
 * @author herau
 */
public class NutrimentsRecyclerViewAdapter extends RecyclerView.Adapter {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private final List<NutrimentItem> nutrimentItems;

    public NutrimentsRecyclerViewAdapter(List<NutrimentItem> nutrimentItems) {
        super();
        this.nutrimentItems = nutrimentItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        boolean isViewTypeHeader = viewType == TYPE_HEADER;

        int layoutResourceId = isViewTypeHeader ? R.layout.nutriment_item_list_header : R.layout.nutriment_item_list;
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutResourceId, parent, false);

        return isViewTypeHeader ? new NutrimentHeaderViewHolder(v) : new NutrimentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (!(holder instanceof NutrimentViewHolder)) {
            return;
        }

        NutrimentItem item = nutrimentItems.get(position);

        NutrimentViewHolder nutrimentViewHolder = (NutrimentViewHolder) holder;

        nutrimentViewHolder.vNutrimentName.setText(item.getTitle());
        nutrimentViewHolder.vNutrimentValue.append(item.getValue());
        nutrimentViewHolder.vNutrimentValue.append(" ");
        nutrimentViewHolder.vNutrimentValue.append(item.getUnit());

        nutrimentViewHolder.vNutrimentServingValue.append(item.getServingValue());
        nutrimentViewHolder.vNutrimentServingValue.append(" ");
        nutrimentViewHolder.vNutrimentServingValue.append(item.getUnit());
    }

    @Override
    public int getItemViewType(int position) {
        return isPositionHeader(position) ? TYPE_HEADER : TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    @Override
    public int getItemCount() {
        return nutrimentItems.size();
    }

    class NutrimentViewHolder extends RecyclerView.ViewHolder {
        TextView vNutrimentName;
        TextView vNutrimentValue;
        TextView vNutrimentServingValue;

        public NutrimentViewHolder(View v) {
            super(v);
            vNutrimentName = (TextView) v.findViewById(R.id.nutriment_name);
            vNutrimentValue = (TextView) v.findViewById(R.id.nutriment_value);
            vNutrimentServingValue = (TextView) v.findViewById(R.id.nutriment_serving_value);
        }
    }

    class NutrimentHeaderViewHolder extends RecyclerView.ViewHolder {
        public NutrimentHeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

}
