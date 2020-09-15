package com.example.mobiledoc.Camera;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.mobiledoc.DBHelper;
import com.example.mobiledoc.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*******
 * <p> Title: Flash.java</p>
 *
 * <p> Description: A Java supporting class which displays a button which tests
 *                  the working of flash in Phone .</p>
 *
 * @author Keshav Sharma
 *
 * @version 1.00	2019-09-25
 *
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class Flash extends AppCompatActivity {

    ToggleButton tbtn_flash;
    CameraManager camManager;
    String cameraId;
    TextView flash_txt;
    DBHelper mydb;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        mydb = new DBHelper(this);

        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        final LocalDateTime now = LocalDateTime.now();

        tbtn_flash = (ToggleButton) findViewById(R.id.toggle_btn);
        camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        cameraId = null;
        flash_txt = (TextView) findViewById(R.id.txt_flash);
        flash_txt.setText("Please select to turn on/off Flash Light.");

        tbtn_flash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        try {
                            cameraId = camManager.getCameraIdList()[0];
                            camManager.setTorchMode(cameraId, true);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                mydb.insert("Flash Test", dtf.format(now), " Pass");
                            }
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else {
                    try {
                        camManager.setTorchMode(cameraId, false);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}