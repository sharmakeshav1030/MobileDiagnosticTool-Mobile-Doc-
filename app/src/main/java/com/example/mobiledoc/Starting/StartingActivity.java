package com.example.mobiledoc.Starting;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mobiledoc.DBHelper;
import com.example.mobiledoc.NavigationMenu.AboutActivity;
import com.example.mobiledoc.NavigationMenu.ContactActivity;
import com.example.mobiledoc.NavigationMenu.MyPhoneInfo;
import com.example.mobiledoc.R;
import com.example.mobiledoc.NavigationMenu.SettingsActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*******
 * <p> Title: StartingActivity Class</p>
 *
 * <p> Description: A Java class which helps to displays second screen on the app having
 *                  various executable image buttons on it and acts the Home Screen of the app.</p>
 *
 * @author Keshav Sharma
 *
 * @version 1.00	2019-08-24
 * @version 2.00	2019-11-23
 *
 */

public class StartingActivity extends AppCompatActivity {

    ImageButton camera_btn;
    ImageButton connectivity_btn;
    ImageButton hardware_btn;
    ImageButton sensors_btn;
    ImageButton speakers_btn;
    Button Report;

    private DrawerLayout drawer;
    NavigationView navigationView;

    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        mydb = new DBHelper(this);

        camera_btn = (ImageButton) findViewById(R.id.imgBtnCamera);
        camera_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraActivity = new Intent(StartingActivity.this, CameraActivity.class);
                startActivity(cameraActivity);
            }
        });

        connectivity_btn = (ImageButton) findViewById(R.id.imgBtnConnectivity);
        connectivity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent connectivityActivity = new Intent(StartingActivity.this, ConnectivityActivity.class);
                startActivity(connectivityActivity);
            }
        });

        hardware_btn = (ImageButton) findViewById(R.id.imgBtnHardware);
        hardware_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hardwareActivity = new Intent(StartingActivity.this, HardwareActivity.class);
                startActivity(hardwareActivity);
            }
        });

        sensors_btn = (ImageButton) findViewById(R.id.imgBtnSensors);
        sensors_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sensorsActivity = new Intent(StartingActivity.this, SensorsActivity.class);
                startActivity(sensorsActivity);
            }
        });

        speakers_btn = (ImageButton) findViewById(R.id.imgBtnSound);
        speakers_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent soundActivity = new Intent(StartingActivity.this, SoundActivity.class);
                startActivity(soundActivity);
            }
        });

        Report = (Button) findViewById(R.id.btnReport);
        Report.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void onClick(View v)  {
                //Report Generation from Database into a text file.
                Cursor res = mydb.getData();
                StringBuffer buffer = new StringBuffer();
                int x = 1;
                while (res.moveToNext()) {
                    buffer.append("Id :"+ x +"\n");
                    buffer.append("Name :"+ res.getString(1)+"\n");
                    buffer.append("Date & Time :"+ res.getString(2)+"\n");
                    buffer.append("Status :"+ res.getString(3)+"\n\n");
                   x++;
                }

                try {
                    createReport(buffer.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mydb.dropTable();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Mobile Doc");

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toogle);
        toogle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_Home:
                        Intent homeActivity = new Intent(StartingActivity.this, StartingActivity.class);
                        startActivity(homeActivity);
                        menuItem.setChecked(true);
                        drawer.closeDrawers();
                        break;

                    case R.id.nav_MobileInfo:
                        Intent mobileInfoActivity = new Intent(StartingActivity.this, MyPhoneInfo.class);
                        startActivity(mobileInfoActivity);
                        menuItem.setChecked(true);
                        drawer.closeDrawers();
                        break;

                    case R.id.nav_Settings:
                        Intent settingsActivity = new Intent(StartingActivity.this, SettingsActivity.class);
                        startActivity(settingsActivity);
                        menuItem.setChecked(true);
                        drawer.closeDrawers();
                        break;

                    case R.id.nav_About:
                        Intent aboutActivity = new Intent(StartingActivity.this, AboutActivity.class);
                        startActivity(aboutActivity);
                        menuItem.setChecked(true);
                        drawer.closeDrawers();
                        break;

                    case R.id.nav_Contact:
                        Intent contactActivity = new Intent(StartingActivity.this, ContactActivity.class);
                        startActivity(contactActivity);
                        menuItem.setChecked(true);
                        drawer.closeDrawers();
                        break;

                    case R.id.nav_Feedback:
                        Toast.makeText(getBaseContext(), "Feedback Sent!!" , Toast.LENGTH_SHORT ).show();
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
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void createReport(String text) throws IOException {
        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/TestReportMobileDoc/";
        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String targetPdf = directory_path + "test_report.txt";
        File filePath = new File(targetPdf);
        if (filePath.exists() && filePath.isFile())
        {
            filePath.delete();
        }
        filePath.createNewFile();

            try {
                FileWriter writer = new FileWriter(filePath, true);
                writer.append(text + "\n\n");
                writer.flush();
                writer.close();
                Toast.makeText(this, "Report Generated at:" + Environment.getExternalStorageDirectory().getPath(), Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Log.e("main", "error " + e.toString());
                Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
            }
        }
}
