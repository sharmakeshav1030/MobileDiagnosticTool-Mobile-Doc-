package com.example.mobiledoc.Starting;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mobiledoc.Connectivity.BluetoothCheck;
import com.example.mobiledoc.Connectivity.GPSCheck;
import com.example.mobiledoc.Connectivity.MobileDataCheck;
import com.example.mobiledoc.NavigationMenu.AboutActivity;
import com.example.mobiledoc.NavigationMenu.ContactActivity;
import com.example.mobiledoc.NavigationMenu.MyPhoneInfo;
import com.example.mobiledoc.NavigationMenu.SettingsActivity;
import com.example.mobiledoc.R;
import com.example.mobiledoc.Connectivity.WifiCheck;

/*******
 * <p> Title: Battery Class</p>
 *
 * <p> Description: A Java supporting class for displaying the Connectivity Activity on the screen
 *                  and make possible further uses of the activity.</p>
 *
 * @author Keshav Sharma
 *
 * @version 1.00	2019-08-24
 *
 */

public class ConnectivityActivity extends AppCompatActivity {

    ImageButton wifi;
    ImageButton bluetooth;
    ImageButton cellular;
    ImageButton gps;

    private DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connectivity);

        wifi = (ImageButton) findViewById(R.id.imgBtnWifi);
        wifi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent wifiCheck = new Intent(ConnectivityActivity.this, WifiCheck.class);
                startActivity(wifiCheck);
            }
        });

        bluetooth = (ImageButton) findViewById(R.id.imgBtnBluetooth);
        bluetooth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent bluetoothCheck = new Intent(ConnectivityActivity.this, BluetoothCheck.class);
                startActivity(bluetoothCheck);
            }
        });

        cellular = (ImageButton) findViewById(R.id.imgBtnCellular);
        cellular.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                    Intent mobileDataCheck = new Intent(ConnectivityActivity.this, MobileDataCheck.class);
                    startActivity(mobileDataCheck);
            }
        });

        gps = (ImageButton) findViewById(R.id.imgBtnGps);
        gps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent gpsCheck = new Intent(ConnectivityActivity.this, GPSCheck.class);
                startActivity(gpsCheck);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Connectivity");
        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toogle);
        toogle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_Home:
                        Intent homeActivity = new Intent(ConnectivityActivity.this, StartingActivity.class);
                        startActivity(homeActivity);
                        menuItem.setChecked(true);
                        drawer.closeDrawers();
                        break;

                    case R.id.nav_MobileInfo:
                        Intent mobileInfoActivity = new Intent(ConnectivityActivity.this, MyPhoneInfo.class);
                        startActivity(mobileInfoActivity);
                        menuItem.setChecked(true);
                        drawer.closeDrawers();
                        break;

                    case R.id.nav_Settings:
                        Intent settingsActivity = new Intent(ConnectivityActivity.this, SettingsActivity.class);
                        startActivity(settingsActivity);
                        menuItem.setChecked(true);
                        drawer.closeDrawers();
                        break;

                    case R.id.nav_About:
                        Intent aboutActivity = new Intent(ConnectivityActivity.this, AboutActivity.class);
                        startActivity(aboutActivity);
                        menuItem.setChecked(true);
                        drawer.closeDrawers();
                        break;

                    case R.id.nav_Contact:
                        Intent contactActivity = new Intent(ConnectivityActivity.this, ContactActivity.class);
                        startActivity(contactActivity);
                        menuItem.setChecked(true);
                        drawer.closeDrawers();
                        break;

                    case R.id.nav_Feedback:
                        Toast.makeText(getBaseContext(), "Feedback Sent!!", Toast.LENGTH_SHORT).show();
                        menuItem.setChecked(true);
                        drawer.closeDrawers();
                        break;
                }

                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
