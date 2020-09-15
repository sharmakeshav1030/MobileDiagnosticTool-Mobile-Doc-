package com.example.mobiledoc.Hardware;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mobiledoc.DBHelper;
import com.example.mobiledoc.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*******
 * <p> Title: Battery.java</p>
 *
 * <p> Description: A Java supporting class which will check the current
 *                  battery status and perecntage level.</p>
 *
 * @author Keshav Sharma
 *
 * @version 1.00	2019-09-26
 *
 */

public class Battery extends AppCompatActivity {

    private BatteryReceiver mBatteryReceiver = new BatteryReceiver();
    private IntentFilter mIntentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    DBHelper mydb;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        mydb = new DBHelper(this);

        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        final LocalDateTime now = LocalDateTime.now();

        mydb.insert("Battery Test ", dtf.format(now), " Pass");
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mBatteryReceiver, mIntentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(mBatteryReceiver);
        super.onPause();
    }
}
