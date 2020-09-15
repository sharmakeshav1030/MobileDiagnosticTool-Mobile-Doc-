package com.example.mobiledoc.NavigationMenu.ui.Memory;

import android.app.ActivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mobiledoc.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;

/*******
 * <p> Title: MemoryFragment.java</p>
 *
 * <p> Description: A Java class which extends from fragments and
 *                  displays the Memory(RAM) Info.</p>
 *
 * @author Keshav Sharma
 *
 * @version 1.00	2019-08-22
 *
 */

public class MemoryFragment extends Fragment {

    View view;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_memory_info,container,false);

        //For RAM
        ActivityManager am = (ActivityManager) getActivity().getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        float totalram = Math.round(mi.totalMem/(1024*1024));
        float freeram = Math.round(mi.availMem/(1024*1024));
        float usedram = Math.round(totalram - freeram);

        PieChart pieChart = (PieChart) view.findViewById(R.id.chart_pie);

        List<PieEntry> value = new ArrayList<>();
        value.add(new PieEntry(freeram,"Free RAM"));
        value.add(new PieEntry(usedram,"Used RAM"));

        PieDataSet dataSet = new PieDataSet(value, "RAM Usage");
        PieData pieData = new PieData(dataSet);

        pieChart.setData(pieData);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        return view;
    }
}
