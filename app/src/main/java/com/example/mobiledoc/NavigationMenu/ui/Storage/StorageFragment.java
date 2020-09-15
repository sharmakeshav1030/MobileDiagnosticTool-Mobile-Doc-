package com.example.mobiledoc.NavigationMenu.ui.Storage;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
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
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;

/*******
 * <p> Title: StorageFragment.java</p>
 *
 * <p> Description: A Java class which extends from fragments and
 *                  displays the Storage(Internal) Info.</p>
 *
 * @author Keshav Sharma
 *
 * @version 1.00	2019-08-22
 *
 */

public class StorageFragment extends Fragment {

    View view;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_storage_info,container,false);

        //For Internal Memory
        File location = Environment.getDataDirectory();
        StatFs statFs = new StatFs(location.getPath());
        float blockSize = statFs.getBlockSize();
        float totalblocks = statFs.getBlockCount();
        float availableblocks = statFs.getAvailableBlocks();
        float totalrom = Math.round((totalblocks*blockSize)/(1024*1024));
        float freerom = Math.round((availableblocks*blockSize)/(1024*1024));
        float usedrom = Math.round(totalrom - freerom);

        PieChart pieChart = (PieChart) view.findViewById(R.id.chart_pie2);

        List<PieEntry> value = new ArrayList<>();
        value.add(new PieEntry(freerom,"Free Storage"));
        value.add(new PieEntry(usedrom,"Used Storage"));

        PieDataSet dataSet = new PieDataSet(value, "Internal Storage Usage");
        PieData pieData = new PieData(dataSet);

        pieChart.setData(pieData);
        dataSet.setColors(ColorTemplate.LIBERTY_COLORS);

        return view;
    }
}
