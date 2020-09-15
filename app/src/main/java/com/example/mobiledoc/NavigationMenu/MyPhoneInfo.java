package com.example.mobiledoc.NavigationMenu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.mobiledoc.NavigationMenu.ui.Battery.BatteryFragment;
import com.example.mobiledoc.NavigationMenu.ui.Memory.MemoryFragment;
import com.example.mobiledoc.NavigationMenu.ui.Network.NetworkFragment;
import com.example.mobiledoc.NavigationMenu.ui.Storage.StorageFragment;
import com.example.mobiledoc.NavigationMenu.ui.System.SystemFragment;
import com.example.mobiledoc.R;
import com.example.mobiledoc.Starting.CameraActivity;
import com.example.mobiledoc.Starting.StartingActivity;

/*******
 * <p> Title: MyPhoneInfo.java</p>
 *
 * <p> Description: A Java supporting class which implements Bottom
 *                  Navigation along with fragments and displays the
 *                  phone related info onto fragments.</p>
 *
 * @author Keshav Sharma
 *
 * @version 1.00	2019-08-22
 *
 */

public class MyPhoneInfo extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    NavigationView navigationView;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        //loading the default fragment
        loadFragment(new SystemFragment());

        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

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
                        Intent homeActivity = new Intent(MyPhoneInfo.this, StartingActivity.class);
                        startActivity(homeActivity);
                        menuItem.setChecked(true);
                        drawer.closeDrawers();
                        break;

                    case R.id.nav_MobileInfo:
                        Intent mobileInfoActivity = new Intent(MyPhoneInfo.this, MyPhoneInfo.class);
                        startActivity(mobileInfoActivity);
                        menuItem.setChecked(true);
                        drawer.closeDrawers();
                        break;

                    case R.id.nav_Settings:
                        Intent settingsActivity = new Intent(MyPhoneInfo.this, SettingsActivity.class);
                        startActivity(settingsActivity);
                        menuItem.setChecked(true);
                        drawer.closeDrawers();
                        break;

                    case R.id.nav_About:
                        Intent aboutActivity = new Intent(MyPhoneInfo.this, AboutActivity.class);
                        startActivity(aboutActivity);
                        menuItem.setChecked(true);
                        drawer.closeDrawers();
                        break;

                    case R.id.nav_Contact:
                        Intent contactActivity = new Intent(MyPhoneInfo.this, ContactActivity.class);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_system:
                fragment = new SystemFragment();
                break;

            case R.id.navigation_network:
                fragment = new NetworkFragment();
                break;

            case R.id.navigation_battery:
                fragment = new BatteryFragment();
                break;

            case R.id.navigation_memory:
                fragment = new MemoryFragment();
                break;

            case R.id.navigation_storage:
                fragment = new StorageFragment();
                break;
        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_top, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}

