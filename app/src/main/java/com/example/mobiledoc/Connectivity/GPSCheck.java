package com.example.mobiledoc.Connectivity;

import android.content.Context;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobiledoc.DBHelper;
import com.example.mobiledoc.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*******
 * <p> Title: GPSCheck.java</p>
 *
 * <p> Description: A Java supporting class which will check the current
 *                  status of GPS whether it is working or not..</p>
 *
 * @author Keshav Sharma
 *
 * @version 1.00	2019-09-24
 *
 */

public class GPSCheck extends AppCompatActivity {
    Button gps_check;
    TextView txt_gps_check;
    DBHelper mydb;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpscheck);
        mydb = new DBHelper(this);

        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        final LocalDateTime now = LocalDateTime.now();

        gps_check = (Button) findViewById(R.id.btn_checkgps);
        txt_gps_check = (TextView) findViewById(R.id.text_checkgps);

        gps_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGPSConnected();
                mydb.insert("GPS Test ", dtf.format(now), " Pass");
            }
        });
    }

    private void checkGPSConnected() {
        final LocationManager manager = (LocationManager)getBaseContext().getSystemService    (Context.LOCATION_SERVICE );

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) )
            txt_gps_check.setText("GPS is Disabled!!");
        else
            txt_gps_check.setText("GPS is Enabled!!");
    }
}
