package com.example.mobiledoc.Sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mobiledoc.DBHelper;
import com.example.mobiledoc.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*******
 * <p> Title: MotionSensorActivity Class</p>
 *
 * <p> Description: A Java supporting class which determines various motion sensors
 *                  like Accelerometer, Gyroscope, Gravity and Rotation Vector. This
 *                  class evaluates their values and displys on the device's screen.</p>
 *
 * @author Keshav Sharma
 *
 * @version 1.00	2019-10-10
 *
 */

public class MotionSensorActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    Sensor accelerometer, gyro, gravity, rotation;
    TextView acc_x, acc_y, acc_z, gry_x, gry_y, gry_z, gra_x, gra_y, gra_z, rot_x, rot_y, rot_z,acc,gry,gra,rot;
    DBHelper mydb;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_sensor);
        acc = findViewById(R.id.txt_acc);
        acc_x = findViewById(R.id.txt_acc_x);
        acc_y = findViewById(R.id.txt_acc_y);
        acc_z = findViewById(R.id.txt_acc_z);
        gry = findViewById(R.id.txt_gry);
        gry_x = findViewById(R.id.txt_gry_x);
        gry_y = findViewById(R.id.txt_gry_y);
        gry_z = findViewById(R.id.txt_gry_z);
        gra = findViewById(R.id.txt_grav);
        gra_x = findViewById(R.id.txt_grav_x);
        gra_y = findViewById(R.id.txt_grav_y);
        gra_z = findViewById(R.id.txt_grav_z);
        rot = findViewById(R.id.txt_rot);
        rot_x = findViewById(R.id.txt_rot_x);
        rot_y = findViewById(R.id.txt_rot_y);
        rot_z = findViewById(R.id.txt_rot_z);

        mydb = new DBHelper(this);

        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        final LocalDateTime now = LocalDateTime.now();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(MotionSensorActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            mydb.insert("Accelerometer Test ", dtf.format(now), " Pass");
        }else{
            acc.setText("Accelerometer Not Supported!!");
            acc_x.setText("x = 0");
            acc_y.setText("y = 0");
            acc_z.setText("z = 0");
            mydb.insert("Accelerometer Test ", dtf.format(now), " Fail");
        }

        gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (gyro != null) {
            sensorManager.registerListener(MotionSensorActivity.this, gyro, SensorManager.SENSOR_DELAY_NORMAL);
            mydb.insert("Gyroscope Test ", dtf.format(now), " Pass");
        }else{
            gry.setText("Gyroscope Not Supported!!");
            gry_x.setText("x = 0");
            gry_y.setText("y = 0");
            gry_z.setText("z = 0");
            mydb.insert("Gyroscope Test ", dtf.format(now), " Fail");
        }

        gravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        if (gravity != null) {
            sensorManager.registerListener(MotionSensorActivity.this, gravity, SensorManager.SENSOR_DELAY_NORMAL);
            mydb.insert("Gravity Sensor Test ", dtf.format(now), " Pass");
        }else{
            gra.setText("Gravity Sensor Not Supported!!");
            gra_x.setText("x = 0");
            gra_y.setText("y = 0");
            gra_z.setText("z = 0");
            mydb.insert("Gravity Sensor Test ", dtf.format(now), " Fail");
        }

        rotation = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        if (rotation != null) {
            sensorManager.registerListener(MotionSensorActivity.this, rotation, SensorManager.SENSOR_DELAY_NORMAL);
            mydb.insert("Rotation Vector Sensor Test ", dtf.format(now), " Pass");
        }else{
            rot.setText("Rotation Vector Not Supported!!");
            rot_x.setText("x = 0");
            rot_y.setText("y = 0");
            rot_z.setText("z = 0");
            mydb.insert("Rotation Vector Sensor Test ", dtf.format(now), " Fail");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            acc_x.setText("x = " + event.values[0]);
            acc_y.setText("y = " + event.values[1]);
            acc_z.setText("z = " + event.values[2]);
        }else if (sensor.getType() == Sensor.TYPE_GYROSCOPE){
            gry_x.setText("x = " + event.values[0]);
            gry_y.setText("y = " + event.values[1]);
            gry_z.setText("z = " + event.values[2]);
        }
        else if (sensor.getType() == Sensor.TYPE_GRAVITY){
            gra_x.setText("x = " + event.values[0]);
            gra_y.setText("y = " + event.values[1]);
            gra_z.setText("z = " + event.values[2]);
        }
        else if (sensor.getType() == Sensor.TYPE_ROTATION_VECTOR){
            rot_x.setText("x = " + event.values[0]);
            rot_y.setText("y = " + event.values[1]);
            rot_z.setText("z = " + event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
