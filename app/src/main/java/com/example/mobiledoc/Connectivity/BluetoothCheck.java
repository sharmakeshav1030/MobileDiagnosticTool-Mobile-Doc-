package com.example.mobiledoc.Connectivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobiledoc.DBHelper;
import com.example.mobiledoc.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*******
 * <p> Title: BluetoothCheck.java</p>
 *
 * <p> Description: A Java supporting class which will check the bluetooth
 *                  connectivity of Phone by taking permissions from user.</p>
 *
 * @author Keshav Sharma
 *
 * @version 1.00	2019-09-24
 *
 */

public class BluetoothCheck extends AppCompatActivity {
    Button bluetooth_check;
    TextView txt_bluetooth_check;
    BluetoothAdapter bluetoothAdapter;

    final static int REQUEST_ENABLE_BT = 1;
    DBHelper mydb;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_check);
        mydb = new DBHelper(this);

        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        final LocalDateTime now = LocalDateTime.now();

        txt_bluetooth_check = (TextView)findViewById(R.id.text_checkbluetooth);
        bluetooth_check = (Button)findViewById(R.id.btn_checkbluetooth);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            txt_bluetooth_check.setText("BlueTooth not supported in this device");
            bluetooth_check.setEnabled(false);
        }else{
            if (bluetoothAdapter.isEnabled()) {
                bluetooth_check.setEnabled(false);
                txt_bluetooth_check.setText("BlueTooth enabled");
            }else{
                bluetooth_check.setEnabled(true);
                txt_bluetooth_check.setText("BlueTooth disabled, click button to turn on BlueTooth.");
            }

        }

        bluetooth_check.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                mydb.insert("Bluetooth Test ", dtf.format(now), " Pass");
            }});

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_ENABLE_BT){
            if(resultCode==RESULT_OK){
                Toast.makeText(BluetoothCheck.this, "BlueTooth Turned On", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(BluetoothCheck.this, "Cancelled", Toast.LENGTH_LONG).show();
            }
        }

        if (bluetoothAdapter.isEnabled()) {
            bluetooth_check.setEnabled(false);
            txt_bluetooth_check.setText("BlueTooth enabled");
        }else{
            bluetooth_check.setEnabled(true);
            txt_bluetooth_check.setText("BlueTooth disabled, click button to turn on BlueTooth.");
        }

    }

}