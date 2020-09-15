package com.example.mobiledoc.Sensors;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobiledoc.DBHelper;
import com.example.mobiledoc.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*******
 * <p> Title: OrientationActivity Class</p>
 *
 * <p> Description: A Java supporting class which checks the orientation of the device
 *                  which will get from the check status button whether it is portrait
 *                  or landscape.</p>
 *
 * @author Keshav Sharma
 *
 * @version 1.00	2019-10-10
 *
 */

public class OrientationActivity extends AppCompatActivity {

    Button btn_orient;
    int rotation;
    TextView text;
    ImageView img_ori;
    DBHelper mydb;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation);
        mydb = new DBHelper(this);

        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        final LocalDateTime now = LocalDateTime.now();

        rotation = getWindowManager().getDefaultDisplay().getRotation();

        img_ori = findViewById(R.id.img_orient);
        text = findViewById(R.id.text_orient_info);
        btn_orient = findViewById(R.id.btn_check_orient);
        btn_orient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (rotation) {
                    case Surface.ROTATION_0:
                        text.setText("PORTRAIT MODE");
                        img_ori.setImageResource(R.drawable.portrait);
                        break;
                    case Surface.ROTATION_90:
                        text.setText("LANDSCAPE MODE");
                        img_ori.setImageResource(R.drawable.landscape);
                        break;
                    case Surface.ROTATION_180:
                        text.setText("PORTRAIT MODE");
                        img_ori.setImageResource(R.drawable.portrait);
                        break;
                    case Surface.ROTATION_270:
                        text.setText("LANDSCAPE MODE");
                        img_ori.setImageResource(R.drawable.landscape);
                        break;
                    default:
                        text.setText("PORTRAIT MODE");
                        img_ori.setImageResource(R.drawable.portrait);
                }
                mydb.insert("Orientation Sensor Test ", dtf.format(now), " Pass");
            }
        });
    }
}
