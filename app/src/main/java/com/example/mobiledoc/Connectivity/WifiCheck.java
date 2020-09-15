package com.example.mobiledoc.Connectivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobiledoc.DBHelper;
import com.example.mobiledoc.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*******
 * <p> Title: WifiCheck.java</p>
 *
 * <p> Description: A Java supporting class which will check whether
 *                  there is an active wi-fi connection or not.</p>
 *
 * @author Keshav Sharma
 *
 * @version 1.00	2019-09-24
 *
 */

public class WifiCheck extends AppCompatActivity {
    Button wifi_check;
    TextView txt_wifi_check;
    DBHelper mydb;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wificheck);

        mydb = new DBHelper(this);

        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        final LocalDateTime now = LocalDateTime.now();

        wifi_check = (Button) findViewById(R.id.btn_checkwifi);
        txt_wifi_check = (TextView) findViewById(R.id.text_checkwifi);

        wifi_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 checkWifiConnected();
                mydb.insert("Wi-Fi Test ", dtf.format(now), " Pass");
            }
        });
    }

    private void checkWifiConnected() {
        boolean wifiConnected;
        ConnectivityManager conMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = conMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            if (wifiConnected) {
                txt_wifi_check.setText("Connected to Wi-Fi");
            }
        }
        else
            {
                txt_wifi_check.setText("Not Connected to Wi-Fi");
            }
    }
}