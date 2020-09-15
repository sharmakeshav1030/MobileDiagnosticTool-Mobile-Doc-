package com.example.mobiledoc.Sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobiledoc.DBHelper;
import com.example.mobiledoc.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*******
 * <p> Title: CompassActivity Class</p>
 *
 * <p> Description: A Java supporting class which checks the magnetometer sensor
 *                  and determine the degrees of poles and locates poles as per
 *                  the earth's magnetic field for the device.</p>
 *
 * @author Keshav Sharma
 *
 * @version 1.00	2019-10-10
 *
 */

public class CompassActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager SensorManage;
    private ImageView compassimage;
    private float DegreeStart = 0f;
    TextView DegreeTV;
    Sensor compass;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);
        mydb = new DBHelper(this);



        compassimage = (ImageView) findViewById(R.id.compass_image);

        // TextView that will display the degree
        DegreeTV = (TextView) findViewById(R.id.DegreeTV);

        // initialize your android device sensor capabilities
        SensorManage = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // to stop the listener and save battery
        SensorManage.unregisterListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();

        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        final LocalDateTime now = LocalDateTime.now();

        // code for system's orientation sensor registered listeners
        compass = SensorManage.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        if(compass != null) {
            SensorManage.registerListener(this, compass, SensorManager.SENSOR_DELAY_GAME);
            mydb.insert("Magnetometer Test ", dtf.format(now), " Pass");
        }
        else{
                DegreeTV.setText("Sensor Not Supported!!");
                compassimage.setVisibility(View.INVISIBLE);
            mydb.insert("Magnetometer Test ", dtf.format(now), " Fail");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        // get angle around the z-axis rotated
        float degree = Math.round(event.values[0]);

        DegreeTV.setText("Heading: " + Float.toString(degree) + " degrees");

        // rotation animation - reverse turn degree degrees
        RotateAnimation ra = new RotateAnimation(
                DegreeStart,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        // set the compass animation after the end of the reservation status
        ra.setFillAfter(true);

        // set how long the animation for the compass image will take place
        ra.setDuration(210);

        // Start animation of compass image
        compassimage.startAnimation(ra);
        DegreeStart = -degree;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
