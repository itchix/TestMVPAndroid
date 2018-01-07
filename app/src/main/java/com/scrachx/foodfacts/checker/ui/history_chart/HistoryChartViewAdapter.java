package com.scrachx.foodfacts.checker.ui.history_chart;
/*
 * Copyright (C) 20/05/2017 Scot Scriven
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.scrachx.foodfacts.checker.R;

import java.util.ArrayList;

public class HistoryChartViewAdapter extends ArrayAdapter<HistoryStatsItem> {

    public HistoryChartViewAdapter(Context context, ArrayList<HistoryStatsItem> stats) {
        super(context, 0, stats);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.stats_list_item, parent, false);
        }

        HistoryStatsItem stats = getItem(position);
        TextView tvName = convertView.findViewById(R.id.name_description);
        TextView tvCount = convertView.findViewById(R.id.stats_count);
        ImageView ivImage = convertView.findViewById(R.id.img_stats);

        tvName.setText(stats.getName());
        tvCount.setText(String.valueOf(stats.getCount()));
        ivImage.setImageDrawable(stats.getImage());

        return convertView;
    }
}
