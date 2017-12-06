package com.projects.deus_ex_machina.clustereducation;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    private static final int GOOD_RESULT = 12;
    private PieChart mPieChart;
    private HorizontalBarChart mBarChart;
    private View rootView;
    private ArrayList<ArrayList<Pair<String, Integer>>> chartData =
            new ArrayList<ArrayList<Pair<String, Integer>>>();
    private ProgressBar progressBar;
    private ScrollView dashboardLayout;
    private int dataIsDownloadedCounter = 0;


    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initialize array of chartData
        for (int i = 0; i < 2; i++) {
            chartData.add(null);
        }

        //Getting reference to data of chart
        DatabaseReference mDataChart = FirebaseDatabase.getInstance().getReference()
                .child("polls/5a193a33" + "/Question1/CountOfAnswers");

        //reset chart downloaded data counter
        dataIsDownloadedCounter = 0;

        //Getting query of chart data ordering by value for Question1
        mDataChart.orderByValue().limitToLast(3).addListenerForSingleValueEvent(new ValueEventListener() {

            //Initialize array of pairs
            ArrayList<Pair<String, Integer>> arrayList = new ArrayList<Pair<String, Integer>>();

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data :
                        dataSnapshot.getChildren()) {

                    //Logger for debug
                    Log.d("TAG", data.getKey() + ": " + data.getValue(Integer.class));
                    arrayList.add(new Pair<String, Integer>(data.getKey(), data.getValue(Integer.class)));
                }

                chartData.set(0, arrayList);

                //Increment counter of downloaded data for chart
                //There are 2 charts, so if counter is < 2 - wait until date will be downloaded
                dataIsDownloadedCounter++;

                //Update UI
                updateUI();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        //Getting query of chart data ordering by value for Question2
        mDataChart = FirebaseDatabase.getInstance().getReference().child("polls/5a193a33" +
                "/Question2/CountOfAnswers");

        mDataChart.orderByValue().limitToLast(4).addListenerForSingleValueEvent(new ValueEventListener() {

            ArrayList<Pair<String, Integer>> arrayList = new ArrayList<Pair<String, Integer>>();

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot data :
                        dataSnapshot.getChildren()) {
                    Log.d("TAG", data.getKey() + ": " + data.getValue(Integer.class));
                    arrayList.add(new Pair<String, Integer>(data.getKey(), data.getValue(Integer.class)));
                }

                chartData.set(1, arrayList);

                //Increment counter of downloaded data for chart
                //There are 2 charts, so if counter is < 2 - wait until date will be downloaded
                dataIsDownloadedCounter++;

                //Update UI
                updateUI();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Getting the rootView to access standard methods of Activity in Fragment
        rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);


        //Getting ID of answer button on Card View
        Button buttonAnswerOnCardView = rootView.findViewById(R.id.buttonAnswer);

        progressBar = rootView.findViewById(R.id.progressBar);

        dashboardLayout = rootView.findViewById(R.id.dashboard_layout);


        //Getting ID's of 2 charts
        mPieChart = (PieChart) rootView.findViewById(R.id.pieChart);
        mBarChart = (HorizontalBarChart) rootView.findViewById(R.id.horizontalBarChart);

        setPieChartAppearance();
        setBarChartAppearance();

        buttonAnswerOnCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(rootView.getContext(), PollActivity.class), GOOD_RESULT);
            }
        });

        updateUI();

        return rootView;
    }

    private void updateUI() {
        if (rootView == null) { // Check if view is already inflated
            return;
        }


        //TODO Download data all, not in loop
        if (dataIsDownloadedCounter >= 2) {
            // View is already inflated and data is ready - update the view!
            setDataForPieChart(3, chartData.get(0));
            setDataForBarChart(3, chartData.get(1));

            //hide progress bar
            progressBar.setVisibility(View.GONE);

            //set layout visible
            dashboardLayout.setVisibility(View.VISIBLE);
        }
    }

    private void setDataForPieChart(int count, ArrayList<Pair<String, Integer>> arrayList) {
        ArrayList<PieEntry> values = new ArrayList<PieEntry>();

        for (int i = 0; i < count; i++) {
            values.add(new PieEntry((float) arrayList.get(i).second, arrayList.get(i).first));
        }

        //Setting appearance of Pie chart
        //-----------------------------------------------------------------------
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
        //-----------------------------------------------------------------------

        mPieChart.setData(data);

        //say Pie chart that data is updated and redraw chart
        mPieChart.invalidate();
        mPieChart.animateY(1500, Easing.EasingOption.EaseOutQuart);
    }

    private void setDataForBarChart(int count, ArrayList<Pair<String, Integer>> arrayList) {

        BarData data = new BarData();

        //Creating custom array of Material Colors
        int[] MATERIAL_COLORS = {
                rgb("#2ecc71"), rgb("#f1c40f"), rgb("#e74c3c"), rgb("#3498db")
        };

        for (int i = 0; i < count; i++) {
            ArrayList<BarEntry> value = new ArrayList<BarEntry>();
            value.add(new BarEntry(i + 'f', (float) arrayList.get(i).second));

            BarDataSet dataSet = new BarDataSet(value, arrayList.get(i).first);
            dataSet.setColors(MATERIAL_COLORS[i]);
            data.addDataSet(dataSet);

        }

        data.setValueTextSize(11f);
        mBarChart.setData(data);

        //say Bar chart that data is updated and redraw chart
        mBarChart.invalidate();
        mBarChart.animateY(1500, Easing.EasingOption.EaseOutQuart);
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
        l.setWordWrapEnabled(true);

        //sets whether the legend will draw inside the chart or outside
        l.setDrawInside(false);

        //Enable wrap text to a new line
        l.setWordWrapEnabled(true);

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
