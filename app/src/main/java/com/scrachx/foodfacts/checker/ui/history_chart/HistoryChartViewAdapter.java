package com.scrachx.foodfacts.checker.ui.history_chart;

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
 * Created by scots on 21/05/2017.
 */

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
        TextView tvName = (TextView) convertView.findViewById(R.id.name_description);
        TextView tvCount = (TextView) convertView.findViewById(R.id.stats_count);
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.img_stats);

        tvName.setText(stats.getName());
        tvCount.setText(String.valueOf(stats.getCount()));
        ivImage.setImageDrawable(stats.getImage());

        return convertView;
    }
}
