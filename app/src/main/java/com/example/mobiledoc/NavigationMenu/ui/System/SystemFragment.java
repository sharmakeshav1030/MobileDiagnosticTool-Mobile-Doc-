package com.example.mobiledoc.NavigationMenu.ui.System;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.mobiledoc.R;

/*******
 * <p> Title: SystemFragment.java</p>
 *
 * <p> Description: A Java class which extends from fragments and
 *                  displays the System Info and Screen Info as well.</p>
 *
 * @author Keshav Sharma
 *
 * @version 1.00	2019-08-22
 *
 */

public class SystemFragment extends Fragment {

    String imei1 = "";
    String imei2 = "";
    String ker;
    TelephonyManager tm;
    final int IMEI_PERMISSION_REQUEST_CODE = 111;
    ListView list_info;
    DisplayMetrics displaymetrics;
    float value = 0;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_mobile_info,container,false);

        ker = System.getProperty("os.version");
        BluetoothAdapter myDevice = BluetoothAdapter.getDefaultAdapter();
        String deviceName = myDevice.getName();
        String model = Build.MODEL;
        String brand = Build.BRAND;
        String release = Build.VERSION.RELEASE;
        String cpu1 =  Build.CPU_ABI;
        String cpu2 = Build.CPU_ABI2;
        int sdk = Build.VERSION.SDK_INT;

        tm = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);

        if (checkPermission(Manifest.permission.READ_PHONE_STATE)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                imei1 = tm.getImei(0);
                imei2 = tm.getImei(1);
            }
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_PHONE_STATE},
                    IMEI_PERMISSION_REQUEST_CODE);
        }

        displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        value = getResources().getDisplayMetrics().density * 160f;
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        double x = Math.pow(width/displaymetrics.xdpi,2);
        double y = Math.pow(height/displaymetrics.ydpi,2);
        double screenInches = Math.round((Math.sqrt(x+y)) * 100) / 100;

        String[] info_type = {" ","\n"+"Device Name" +"\n"+ deviceName,"Model" +"\n"+ model,"Brand" +"\n"+ brand,"Release Version"+"\n"
                              +release,"SDK Version"+"\n"+sdk, "Kernel Version"+"\n"+ ker, "IMEI 1"+"\n"+ imei1, "IMEI 2"
                              +"\n"+ imei2, "CPU 1"+"\n"+ cpu1, "CPU 2"+"\n"+ cpu2
                ,"Screen Resolution : " + "\n" + height + "*" + width
                , "Screen Density : " + "\n" + String.valueOf(value) + "dpi"
                , "Screen Size : " + "\n" + screenInches + "'"};

        list_info = (ListView) view.findViewById(R.id.list_info_view);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                info_type
        );
        list_info.setAdapter(listViewAdapter);

        return view;
    }
    private boolean checkPermission(String permission) {
        int checkPermission = ContextCompat.checkSelfPermission(getActivity(), permission);
        return (checkPermission == PackageManager.PERMISSION_GRANTED);
    }
}
