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

import com.example.mobiledoc.NavigationMenu.AboutActivity;
import com.example.mobiledoc.NavigationMenu.ContactActivity;
import com.example.mobiledoc.NavigationMenu.MyPhoneInfo;
import com.example.mobiledoc.NavigationMenu.SettingsActivity;
import com.example.mobiledoc.R;
import com.example.mobiledoc.Sound.MicrophoneActivity;

/*******
 * <p> Title: SoundActivity Class</p>
 *
 * <p> Description: A Java supporting class for displaying the Sound Activity on the screen and
 *                  make possible further uses of the activity.</p>
 *
 * @author Keshav Sharma
 *
 * @version 1.00	2019-08-24
 *
 */

public class SoundActivity extends AppCompatActivity {

    ImageButton mic;
    private DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        mic = (ImageButton) findViewById(R.id.imgBtnMic);
        mic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent microphone = new Intent(SoundActivity.this, MicrophoneActivity.class);
                startActivity(microphone);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Sound");
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
                        Intent homeActivity = new Intent(SoundActivity.this, StartingActivity.class);
                        startActivity(homeActivity);
                        menuItem.setChecked(true);
                        drawer.closeDrawers();
                        break;

                    case R.id.nav_MobileInfo:
                        Intent mobileInfoActivity = new Intent(SoundActivity.this, MyPhoneInfo.class);
                        startActivity(mobileInfoActivity);
                        menuItem.setChecked(true);
                        drawer.closeDrawers();
                        break;

                    case R.id.nav_Settings:
                        Intent settingsActivity = new Intent(SoundActivity.this, SettingsActivity.class);
                        startActivity(settingsActivity);
                        menuItem.setChecked(true);
                        drawer.closeDrawers();
                        break;

                    case R.id.nav_About:
                        Intent aboutActivity = new Intent(SoundActivity.this, AboutActivity.class);
                        startActivity(aboutActivity);
                        menuItem.setChecked(true);
                        drawer.closeDrawers();
                        break;

                    case R.id.nav_Contact:
                        Intent contactActivity = new Intent(SoundActivity.this, ContactActivity.class);
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

