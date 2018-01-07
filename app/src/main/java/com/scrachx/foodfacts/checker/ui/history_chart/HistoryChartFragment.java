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

package com.scrachx.foodfacts.checker.ui.history_chart;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.scrachx.foodfacts.checker.R;
import com.scrachx.foodfacts.checker.data.db.model.History;
import com.scrachx.foodfacts.checker.ui.base.BaseFragment;
import com.scrachx.foodfacts.checker.ui.history.HistoryFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import timber.log.Timber;

public class HistoryChartFragment extends BaseFragment implements HistoryChartMvpView {

    public static final String TAG = "HistoryChartFragment";

    @Inject
    public HistoryChartMvpPresenter<HistoryChartMvpView> mPresenter;

    @BindView(R.id.history_chart)
    public PieChart mPieChart;

    @BindView(R.id.list_view_stats)
    public ListView mListStats;

    public static HistoryChartFragment newInstance() {
        Bundle args = new Bundle();
        HistoryChartFragment fragment = new HistoryChartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_chart, container, false);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        mPresenter.onAttach(this);
        setUp(view);

        return view;
    }

    @Override
    public void loadHistoryStats(List<History> productsHistory) {
        int total = productsHistory.size();
        Map<String, Integer> result = new HashMap<>();
        for (History history : productsHistory) {
            if (history.getGrade() == null) {
                if (result.containsKey("nc")) {
                    result.put(history.getGrade(), result.get("nc") + 1);
                } else {
                    result.put("nc", 1);
                }
            } else {
                if (result.containsKey(history.getGrade())) {
                    result.put(history.getGrade(), result.get(history.getGrade()) + 1);
                } else {
                    result.put(history.getGrade(), 1);
                }
            }
        }

        ArrayList<HistoryStatsItem> arrayOfHistory = new ArrayList<HistoryStatsItem>();
        HistoryChartViewAdapter adapter = new HistoryChartViewAdapter(getActivity(), arrayOfHistory);
        mListStats.setAdapter(adapter);

        ArrayList<Integer> arrayDrawGraph = new ArrayList<Integer>();
        ArrayList<Integer> colors = new ArrayList<Integer>();
        ArrayList<String> percent = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            arrayDrawGraph.add(entry.getValue());
            float valuePercent = ((float) entry.getValue() / (float) total) * 100;
            percent.add(String.valueOf(valuePercent) + "%");
            int color;
            Drawable colorD;
            String description;
            switch (entry.getKey()) {
                case "a":
                    color = R.color.green_dark_scheme;
                    colorD = ContextCompat.getDrawable(getBaseActivity(), R.drawable.ic_circle_dark_green_24dp);
                    description = getString(R.string.txt_history_stats_desc_a);
                    break;
                case "b":
                    color = R.color.green_light_scheme;
                    colorD = ContextCompat.getDrawable(getBaseActivity(), R.drawable.ic_circle_green_24dp);
                    description = getString(R.string.txt_history_stats_desc_b);
                    break;
                case "c":
                    color = R.color.yellow_dark_scheme;
                    colorD = ContextCompat.getDrawable(getBaseActivity(), R.drawable.ic_circle_yellow_24dp);
                    description = getString(R.string.txt_history_stats_desc_c);
                    break;
                case "d":
                    color = R.color.orange_scheme;
                    colorD = ContextCompat.getDrawable(getBaseActivity(), R.drawable.ic_circle_orange_24dp);
                    description = getString(R.string.txt_history_stats_desc_d);
                    break;
                case "e":
                    color = R.color.red_scheme;
                    colorD = ContextCompat.getDrawable(getBaseActivity(), R.drawable.ic_circle_red_24dp);
                    description = getString(R.string.txt_history_stats_desc_e);
                    break;
                default:
                    color = R.color.grey_scheme;
                    colorD = ContextCompat.getDrawable(getBaseActivity(), R.drawable.ic_circle_grey_24dp);
                    description = getString(R.string.txt_history_stats_desc_nc);
                    break;
            }
            adapter.add(new HistoryStatsItem(description, entry.getValue(), colorD));
            colors.add(ContextCompat.getColor(getBaseActivity(), color));
        }
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        for (int i = 0; i < result.size(); i++) {
            entries.add(new PieEntry(arrayDrawGraph.get(i), percent.get(i)));
        }
        PieDataSet dataset = new PieDataSet(entries, "");
        PieData data = new PieData(dataset);
        mPieChart.setData(data);
        dataset.setColors(colors);

        Legend l = mPieChart.getLegend();
        l.setTextSize(14f);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setTextColor(Color.BLACK);
        l.setEnabled(true);

        dataset.setDrawValues(false);
        mPieChart.getLegend().setEnabled(false);
        mPieChart.getDescription().setEnabled(false);
        mPieChart.setCenterText(getString(R.string.txt_charts_begining) + ": " + String.valueOf(total) + " " + getString(R.string.txt_products_viewed));
        mPieChart.animateY(2000);
    }

    @Override
    public void loadHistoryGrade(int position) {
        HistoryStatsItem historyItem = (HistoryStatsItem) mListStats.getItemAtPosition(position);
        getBaseActivity().getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.cl_root_view, HistoryFragment.newInstance(historyItem.getName()), HistoryFragment.TAG)
                .commit();
    }

    @Override
    protected void setUp(View view) {
        mPresenter.onLoadHistoryStats();
    }

    @OnItemClick(R.id.list_view_stats)
    public void onItemClick(AdapterView<?> parent, int position) {
        mPresenter.onLoadHistoryGrade(position);
    }

}
