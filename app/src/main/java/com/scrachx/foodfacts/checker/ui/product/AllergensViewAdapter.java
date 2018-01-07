/*
 * Copyright (C) 20/05/2017 scots
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

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
        TextView tvName = convertView.findViewById(R.id.name_allergens);
        ImageView ivImage = convertView.findViewById(R.id.img_allergen);

        tvName.setText(allergen.getName());
        ivImage.setImageDrawable(allergen.getImage());

        return convertView;
    }

}
