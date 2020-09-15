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
 * <p> Title: MobileDataCheck.java</p>
 *
 * <p> Description: A Java supporting class which will check whether
 *                  there is an active internet connection or not.</p>
 *
 * @author Keshav Sharma
 *
 * @version 1.00	2019-09-24
 *
 */

public class MobileDataCheck extends AppCompatActivity {

    Button mobile_data_check;
    TextView current_status;
    DBHelper mydb;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_data_check);
        mydb = new DBHelper(this);

        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        final LocalDateTime now = LocalDateTime.now();

        mobile_data_check = (Button) findViewById(R.id.btn_checkmobiledata);
        current_status = (TextView) findViewById(R.id.text_current_status);

        mobile_data_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkMobileDataConnected();
                mydb.insert("MobileDataConnectivity Test ", dtf.format(now), " Pass");
            }
        });
    }

    private void checkMobileDataConnected() {
        boolean mobileDataConnected;
        ConnectivityManager conMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = conMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            mobileDataConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if (mobileDataConnected) {
                current_status.setText("Connected to Mobile Data!!");
            }
        }
        else
        {
            current_status.setText("Not Connected to Mobile Data!!");
        }
    }
}