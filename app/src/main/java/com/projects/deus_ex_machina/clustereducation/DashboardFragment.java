package com.projects.deus_ex_machina.clustereducation;


import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    private PieChart mChart;
    private HorizontalBarChart mBarChart;


    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        ConstraintLayout constraintLayout = rootView.findViewById(R.id.pieLayout);
        int height = constraintLayout.getMaxHeight();


        mChart = (PieChart) rootView.findViewById(R.id.pieChart);
        mBarChart = (HorizontalBarChart) rootView.findViewById(R.id.horizontalBarChart);
        mChart.setBackgroundColor(Color.WHITE);

        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);

        //mChart.setCenterText("Hello World");

        mChart.setDrawHoleEnabled(false);
        //mChart.setHoleColor(Color.WHITE);

        //mChart.setTransparentCircleColor(Color.WHITE);
        //mChart.setTransparentCircleAlpha(110);

        //mChart.setHoleRadius(55f);
        //mChart.setTransparentCircleRadius(58f);
        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        mChart.setDrawCenterText(true);

        mChart.setHighlightPerTapEnabled(true);

        setData(4, 100);
        setDataForBarChart(4,100);

        mChart.animateY(1500, Easing.EasingOption.EaseOutQuart);
        mBarChart.animateY(1500, Easing.EasingOption.EaseOutQuart);




        return rootView;
    }

    private void setData(int count , int range) {
        ArrayList<PieEntry> values = new ArrayList<PieEntry>();

        for (int i = 0; i < count; i++) {
            values.add(new PieEntry((float) ((Math.random() * range) + range / 5), "Quarter"));
        }

        PieDataSet dataSet = new PieDataSet(values, "Election Results");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        mChart.setData(data);

        mChart.invalidate();
    }

    private void setDataForBarChart(int count , int range) {
        ArrayList<BarEntry> values = new ArrayList<BarEntry>();

        for (int i = 0; i < count; i++) {
            values.add(new BarEntry(i+'f',(float) ((Math.random() * range) + range / 5), "Quarter"));
        }

        BarDataSet dataSet = new BarDataSet(values, "Election Results");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        //dataSet.setSelectionShift(0f);

        BarData data = new BarData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        //data.setValueTextColor(Color.WHITE);
        mBarChart.setData(data);

        mBarChart.invalidate();
    }

}
