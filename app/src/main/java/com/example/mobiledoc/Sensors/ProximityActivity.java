package com.example.mobiledoc.Sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobiledoc.DBHelper;
import com.example.mobiledoc.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*******
 * <p> Title: CompassActivity Class</p>
 *
 * <p> Description: A Java supporting class which checks the working of proximity sensor
 *                  when the user place it's hand over the screen of the device.</p>
 *
 * @author Keshav Sharma
 *
 * @version 1.00	2019-10-10
 *
 */

public class ProximityActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager SensorManager;
    private Sensor Sensor;
    ImageView img_prox;
    TextView txt_prox;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);

        mydb = new DBHelper(this);

        SensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor = SensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        img_prox = (ImageView) findViewById(R.id.imgProximity);
        txt_prox = findViewById(R.id.text_prox);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onResume() {
        super.onResume();
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        final LocalDateTime now = LocalDateTime.now();
        SensorManager.registerListener(this, Sensor,SensorManager.SENSOR_DELAY_NORMAL);
        mydb.insert("Proximity Sensor Test ", dtf.format(now), " Pass");
    }
    protected void onPause() {
        super.onPause();
        SensorManager.unregisterListener(this);

    }
    public void onAccuracyChanged(Sensor sensor, int accuracy) {    }

    public void onSensorChanged(SensorEvent event) {
        if (event.values[0] == 0) {
            img_prox.setImageResource(R.drawable.bulb_off);
            txt_prox.setText("     CLOSE");
        } else {
            img_prox.setImageResource(R.drawable.bulb_on);
            txt_prox.setText("     FAR");
        }
    }
}