package com.projects.deus_ex_machina.clustereducation;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    private PieChart mPieChart;
    private HorizontalBarChart mBarChart;


    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Getting the rootView to access standard methods of Activity in Fragment
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //Getting ID's of 2 charts
        mPieChart = (PieChart) rootView.findViewById(R.id.pieChart);
        mBarChart = (HorizontalBarChart) rootView.findViewById(R.id.horizontalBarChart);

        mPieChart.setBackgroundColor(Color.WHITE);
        mBarChart.setBackgroundColor(Color.WHITE);

        mPieChart.setUsePercentValues(true);
        mPieChart.getDescription().setEnabled(false);

        //mPieChart.setCenterText("The worst subjects");

        mPieChart.setDrawHoleEnabled(false);
        //mPieChart.setHoleColor(Color.WHITE);

        //mPieChart.setTransparentCircleColor(Color.WHITE);
        //mPieChart.setTransparentCircleAlpha(110);

        //mPieChart.setHoleRadius(55f);
        //mPieChart.setTransparentCircleRadius(58f);
        Legend l = mPieChart.getLegend();
        l.setTextSize(15f);
        l.setForm(Legend.LegendForm.CIRCLE);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        mPieChart.setDrawCenterText(true);

        mPieChart.setHighlightPerTapEnabled(true);

        YAxis yAxis = mBarChart.getAxisRight();
        yAxis.setEnabled(false);

        yAxis = mBarChart.getAxisLeft();
        yAxis.setEnabled(false);

        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setEnabled(false);

        mBarChart.setDrawValueAboveBar(true);

        // scaling can now only be done on x- and y-axis separately
        mBarChart.setPinchZoom(false);
        mBarChart.setDrawGridBackground(false);

        mBarChart.getDescription().setEnabled(false);

        // scaling can now only be done on x- and y-axis separately
        mBarChart.setPinchZoom(false);






        YAxis yl = mBarChart.getAxisLeft();
        yl.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        yl.setInverted(true);


        Legend l2 = mBarChart.getLegend();
        l2.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l2.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l2.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l2.setDrawInside(false);
        l2.setFormSize(10f);
        l2.setTextSize(10f);

        l2.setFormToTextSpace(4f);
        l2.setXEntrySpace(6f);



        setDataForPieChart(4, 5);
        setDataForBarChart(4,80);

        mPieChart.animateY(1500, Easing.EasingOption.EaseOutQuart);
        mBarChart.animateY(1500, Easing.EasingOption.EaseOutQuart);




        return rootView;
    }

    private void setDataForPieChart(int count , int range) {
        ArrayList<PieEntry> values = new ArrayList<PieEntry>();

        for (int i = 0; i < count; i++) {
            values.add(new PieEntry((float) ((Math.random() * range) + range / 5), "Good"));
        }

        PieDataSet dataSet = new PieDataSet(values, "Mark of program");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new DefaultValueFormatter(0));
        data.setValueTextSize(17f);
        data.setValueTextColor(Color.WHITE);
        mPieChart.setData(data);

        mPieChart.invalidate();
    }

    private void setDataForBarChart(int count , int range) {

        BarData data = new BarData();

        int[] MATERIAL_COLORS = {
                rgb("#2ecc71"), rgb("#f1c40f"), rgb("#e74c3c"), rgb("#3498db")
        };

        for (int i = 0; i < count; i++) {
            ArrayList<BarEntry> value = new ArrayList<BarEntry>();
            value.add(new BarEntry(i+'f',(float) ((Math.random() * range) + range / 5)));

            BarDataSet dataSet = new BarDataSet(value,"Subject #" + i);
            dataSet.setColors(MATERIAL_COLORS[i]);
            data.addDataSet(dataSet);

        }



        //dataSet.setSelectionShift(0f);


        //data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        //data.setValueTextColor(Color.WHITE);
        mBarChart.setData(data);

        mBarChart.invalidate();
    }

}
