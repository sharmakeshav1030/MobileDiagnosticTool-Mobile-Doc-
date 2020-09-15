package com.example.mobiledoc.Hardware;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.BatteryManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mobiledoc.R;

/*******
 * <p> Title: BatteryReciever.java</p>
 *
 * <p> Description: A Java supporting class which extends from Broadcast Reciever
 *                  and make the class to peform various functions.</p>
 *
 * @author Keshav Sharma
 *
 * @version 1.00	2019-09-26
 *
 */

public class BatteryReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        TextView statusLabel = ((Battery)context).findViewById(R.id.statusLabel);
        TextView percentageLabel = ((Battery)context).findViewById(R.id.percentageLabel);
        ImageView batteryImage = ((Battery)context).findViewById(R.id.batteryImage);

        String action = intent.getAction();

        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

        if (action != null && action.equals(Intent.ACTION_BATTERY_CHANGED)) {
            String message = "";
            switch (status) {
                case BatteryManager.BATTERY_STATUS_FULL:
                    message = "Full";
                    break;
                case BatteryManager.BATTERY_STATUS_CHARGING:
                    message = "Charging";
                    break;
                case BatteryManager.BATTERY_STATUS_DISCHARGING:
                    message = "Discharging";
                    break;
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    message = "Not charging";
                    break;
                case BatteryManager.BATTERY_STATUS_UNKNOWN:
                    message = "Unknown";
                    break;
            }
            statusLabel.setText(message);

            // Percentage
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int percentage = level * 100 / scale;
            percentageLabel.setText("Battery Level : " + percentage + "%");

            // Image
            Resources res = context.getResources();

            if (percentage >= 90) {
                batteryImage.setImageDrawable(res.getDrawable(R.drawable.b100));
            } else if (90 > percentage && percentage >= 65) {
                batteryImage.setImageDrawable(res.getDrawable(R.drawable.b75));
            } else if (65 > percentage && percentage >= 40) {
                batteryImage.setImageDrawable(res.getDrawable(R.drawable.b50));
            } else if (40 > percentage && percentage >= 15) {
                batteryImage.setImageDrawable(res.getDrawable(R.drawable.b25));
            } else {
                batteryImage.setImageDrawable(res.getDrawable(R.drawable.b0));
            }
        }
    }
}
