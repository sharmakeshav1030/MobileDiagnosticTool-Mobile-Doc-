package com.example.mobiledoc.Camera;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.mobiledoc.DBHelper;
import com.example.mobiledoc.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*******
 * <p> Title: TestCamera.java</p>
 *
 * <p> Description: A Java supporting class which tests the Phone camera by
 *                  opening it and taking a picture.</p>
 *
 * @author Keshav Sharma
 *
 * @version 1.00	2019-09-25
 *
 */

public class TestCamera extends AppCompatActivity {

    Button btn_cam;
    DBHelper mydb;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testcamera);

        mydb = new DBHelper(this);

        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        final LocalDateTime now = LocalDateTime.now();

        btn_cam = (Button) findViewById(R.id.btn_open_cam);

        btn_cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1888);
                mydb.insert("Camera Test ", dtf.format(now), " Pass");
            }
        });
    }
}