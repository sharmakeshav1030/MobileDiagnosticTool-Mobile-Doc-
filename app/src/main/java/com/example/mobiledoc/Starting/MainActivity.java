package com.example.mobiledoc.Starting;

 import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mobiledoc.R;

/*******
 * <p> Title: Main Activity</p>
 *
 * <p> Description: A Java class which runs at the time of opening of the application.
 *                  It basically displays the flash screen at the starting of the app.</p>
 *
 * @author Keshav Sharma
 *
 * @version 1.00	2019-08-24
 *
 */

public class MainActivity extends AppCompatActivity {
    private static int Time_Out = 600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent starting = new Intent(MainActivity.this, StartingActivity.class);
                startActivity(starting);
                finish();
            }
        },Time_Out);
    }
}