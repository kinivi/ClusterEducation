package com.projects.deus_ex_machina.clustereducation;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
import com.github.mikephil.charting.formatter.PercentFormatter;
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
        final View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //Getting ID of answer button on Card View
        Button buttonAnswerOnCardView = rootView.findViewById(R.id.buttonAnswer);

        //Getting ID's of 2 charts
        mPieChart = (PieChart) rootView.findViewById(R.id.pieChart);
        mBarChart = (HorizontalBarChart) rootView.findViewById(R.id.horizontalBarChart);

        setPieChartAppearance();
        setBarChartAppearance();

        buttonAnswerOnCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(rootView.getContext(), PollActivity.class));
            }
        });

        setDataForPieChart(4, 85);
        setDataForBarChart(4, 80);

        //Animate charts
        mPieChart.animateY(1500, Easing.EasingOption.EaseOutQuart);
        mBarChart.animateY(1500, Easing.EasingOption.EaseOutQuart);


        return rootView;
    }

    private void setDataForPieChart(int count, int range) {
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
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(17f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(Typeface.DEFAULT_BOLD);

        mPieChart.setData(data);

        mPieChart.invalidate();
    }

    private void setDataForBarChart(int count, int range) {

        BarData data = new BarData();

        int[] MATERIAL_COLORS = {
                rgb("#2ecc71"), rgb("#f1c40f"), rgb("#e74c3c"), rgb("#3498db")
        };

        for (int i = 0; i < count; i++) {
            ArrayList<BarEntry> value = new ArrayList<BarEntry>();
            value.add(new BarEntry(i + 'f', (float) ((Math.random() * range) + range / 5)));

            BarDataSet dataSet = new BarDataSet(value, "Subject #" + i);
            dataSet.setColors(MATERIAL_COLORS[i]);
            data.addDataSet(dataSet);

        }

        data.setValueTextSize(11f);
        mBarChart.setData(data);

        mBarChart.invalidate();
    }

    private void setPieChartAppearance() {
        Legend l = mPieChart.getLegend();

        mPieChart.setBackgroundColor(Color.WHITE);

        //Setting that Pie Chart will use percent values
        mPieChart.setUsePercentValues(true);

        //Disable the description
        mPieChart.getDescription().setEnabled(false);

        //Don't draw hole inside Pie chart
        mPieChart.setDrawHoleEnabled(false);
        //Don't draw labels on PieChart
        mPieChart.setDrawEntryLabels(false);

        //Setting the appearance of the legend
        l.setTextSize(15f);
        l.setForm(Legend.LegendForm.CIRCLE);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
    }

    private void setBarChartAppearance() {
        mBarChart.setBackgroundColor(Color.WHITE);

        mBarChart.setHighlightPerTapEnabled(false);

        //Disable drawing all axis  of chart
        YAxis yAxis = mBarChart.getAxisRight();
        yAxis.setEnabled(false);

        yAxis = mBarChart.getAxisLeft();
        yAxis.setEnabled(false);

        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setEnabled(false);

        // scaling can now only be done on x- and y-axis separately
        mBarChart.setPinchZoom(false);

        //disable double-tap zoom
        mBarChart.setDoubleTapToZoomEnabled(false);

        //Disable drawing grid on the background
        mBarChart.setDrawGridBackground(false);

        //Disable the description
        mBarChart.getDescription().setEnabled(false);

        Legend l = mBarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);

        //sets whether the legend will draw inside the chart or outside
        l.setDrawInside(false);

        l.setFormSize(10f);
        l.setTextSize(12f);

        //sets the space between the form and the actual label/text, converts to dp internally
        l.setFormToTextSpace(4f);

        //sets the space between the legend entries on a horizontal axis in pixels,
        //converts to dp internally
        l.setXEntrySpace(8f);

        //Set axis minimum to 0 and it provides labels under each Bar Chart
        YAxis y = mBarChart.getAxisLeft();
        y.setAxisMinimum(0f);
    }

}
