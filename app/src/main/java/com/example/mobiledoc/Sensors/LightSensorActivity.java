package com.example.mobiledoc.Sensors;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobiledoc.DBHelper;
import com.example.mobiledoc.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*******
 * <p> Title: LightSensorActivity Class</p>
 *
 * <p> Description: A Java supporting class which determines the luminous intensity
 *                  as per LightSenor and as the light increases the color of
 *                  background changes on the device screen.</p>
 *
 * @author Keshav Sharma
 *
 * @version 1.00	2019-10-10
 *
 */

public class LightSensorActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor lightSensor;
    private SensorEventListener lightEventListener;
    private View root;
    private float maxValue;
    TextView luxVal;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_sensor);
        root = findViewById(R.id.root);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        luxVal = findViewById(R.id.txt_lux_val);

        mydb = new DBHelper(this);

        // max value for light sensor
        maxValue = lightSensor.getMaximumRange();

        lightEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float value = sensorEvent.values[0];
                luxVal.setText("Luminosity : " + value + " lx");

                // between 0 and 255
                int newValue = (int) (255f * value / maxValue);
                root.setBackgroundColor(Color.rgb(newValue, newValue, newValue));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();

        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        final LocalDateTime now = LocalDateTime.now();


        if(lightSensor != null) {
            sensorManager.registerListener(lightEventListener, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);
            mydb.insert("LightSensor Test ", dtf.format(now), " Pass");
        }
        else {
            luxVal.setText("Light Sensor Not Supported!!");
            mydb.insert("LightSensor Test ", dtf.format(now), " Fail");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(lightEventListener);
    }
}