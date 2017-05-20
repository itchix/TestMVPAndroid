package com.scrachx.foodfacts.checker.ui.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.scrachx.foodfacts.checker.R;

import java.util.ArrayList;

/**
 * Created by scots on 20/05/2017.
 */

public class AllergensViewAdapter extends ArrayAdapter<AllergensItem> {

    public AllergensViewAdapter(Context context, ArrayList<AllergensItem> allergens) {
        super(context, 0, allergens);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.alert_allergens_list_item, parent, false);
        }

        AllergensItem allergen = getItem(position);
        TextView tvName = (TextView) convertView.findViewById(R.id.name_allergens);
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.img_allergen);

        tvName.setText(allergen.getName());
        ivImage.setImageDrawable(allergen.getImage());

        return convertView;
    }

}
