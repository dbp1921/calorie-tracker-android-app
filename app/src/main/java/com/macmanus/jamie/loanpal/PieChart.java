package com.macmanus.jamie.loanpal;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

/**
 * Created by jamie on 15/04/17.
 */

public class PieChart {
    public GraphicalView getGraphicalView(Context context, int protein, int carbs, int fat) {

        // create an instance of the pie chart category series
        CategorySeries series = new CategorySeries("Nutrition Pie");


        int total = protein + carbs + fat;
        double proteinPercentage = (((double)protein) / ((double)total) * 100);
        double carbPercentage = (((double)carbs) / ((double)total) * 100);
        double fatPercentage = (((double)fat) / ((double)total) * 100);

        String pPercentAsString = String.format("%.1f", proteinPercentage);
        String cPercentAsString = String.format("%.1f", carbPercentage);
        String fPercentAsString = String.format("%.1f", fatPercentage);


        // create arrays for name and size of pie chart slides
        int[] portions = {protein, carbs, fat};
        String[] seriesNames = new String[]{"Protein: " + pPercentAsString + "%", "Carbs: "+ cPercentAsString + "%", "Fat: " + fPercentAsString + "%"};

        // assign names and sizes to the pie chart
        int numSlide = 3;
        for (int i = 0; i < numSlide; i++) {
            series.add(seriesNames[i], portions[i]);
        }

        DefaultRenderer defaultRenderer = new DefaultRenderer();
        SimpleSeriesRenderer simpleSeriesRenderer = null;

        int[] colors = {ContextCompat.getColor(context, R.color.myRed), ContextCompat.getColor(context, R.color.green), ContextCompat.getColor(context, R.color.colorPrimary)};

        defaultRenderer.setLabelsTextSize(18);
        defaultRenderer.setLabelsColor(Color.BLACK);
        defaultRenderer.setShowLegend(false);

        // for loop to assign colors
        for (int i = 0; i < numSlide; i++) {
            simpleSeriesRenderer = new SimpleSeriesRenderer();
            simpleSeriesRenderer.setColor(colors[i]);
            defaultRenderer.addSeriesRenderer(simpleSeriesRenderer);
            defaultRenderer.setPanEnabled(false);
            defaultRenderer.setZoomEnabled(false);
        }

        //return the pie chart view
        return ChartFactory.getPieChartView(context, series, defaultRenderer);
    }
}

