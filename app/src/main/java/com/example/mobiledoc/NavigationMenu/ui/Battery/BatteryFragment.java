package com.example.mobiledoc.NavigationMenu.ui.Battery;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.mobiledoc.R;
import java.text.DecimalFormat;
import static android.os.BatteryManager.BATTERY_HEALTH_GOOD;
import static android.os.BatteryManager.EXTRA_HEALTH;

/*******
 * <p> Title: BatteryFragment.java</p>
 *
 * <p> Description: A Java class which extends from fragments and
 *                  displays the Battery Info..</p>
 *
 * @author Keshav Sharma
 *
 * @version 1.00	2019-08-22
 *
 */

public class BatteryFragment extends Fragment {

    private Bundle mExtras;
    ListView list_battery_info;
    double cap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_mobile_info,container,false);

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = getContext().registerReceiver(null, ifilter);

        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
        String finalStatus = null;
        if(status == BatteryManager.BATTERY_STATUS_CHARGING){
            finalStatus = "Charging";
        }
        if(status == BatteryManager.BATTERY_STATUS_DISCHARGING){
            finalStatus = "Discharging";
        }
        if (status == BatteryManager.BATTERY_STATUS_FULL){
            finalStatus = "Battery Full";
        }
        if(status == BatteryManager.BATTERY_STATUS_UNKNOWN){
            finalStatus = "Unknown";
        }
        if (status == BatteryManager.BATTERY_STATUS_NOT_CHARGING){
            finalStatus = "Not Charging";
        }

        int health = batteryStatus.getIntExtra(EXTRA_HEALTH,0);
        String finalHealth = null;
        if(health == BatteryManager.BATTERY_HEALTH_COLD){
            finalHealth = "Cold";
        }
        if (health == BatteryManager.BATTERY_HEALTH_DEAD){
            finalHealth = "Dead";;
        }
        if (health == BATTERY_HEALTH_GOOD){
            finalHealth = "Good";
        }
        if (health == BatteryManager.BATTERY_HEALTH_OVERHEAT){
            finalHealth = "Over Heat";
        }
        if (health == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE){
            finalHealth = "Over Voltage";
        }
        if(health == BatteryManager.BATTERY_HEALTH_UNKNOWN){
            finalHealth = "Unknown";
        }
        if(health == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE){
            finalHealth = "Failure";
        }
        int temp = batteryStatus.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0);
        String finaltemp = null;
        if (temp > 0) {
            float tempo = ((float) temp) / 10f;
          finaltemp = tempo + "°C";
        }
        String tech = batteryStatus.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);

        cap = getBatteryCapacity(getContext());

        int voltage = batteryStatus.getIntExtra(BatteryManager.EXTRA_VOLTAGE,0);
        double volt =  voltage * 0.001;
        // Initialize a new DecimalFormat instance
        DecimalFormat newFormat = new DecimalFormat("#.#");
        // Format the decimal value to one decimal position
        double oneDecimalVolt =  Double.valueOf(newFormat.format(volt));

        String[] info_type = {" ","\n"+"Status" +"\n"+ finalStatus,"Technology Used" +"\n"+ tech,"Capacity" +"\n"+ cap + " mAh",
                                "Health" +"\n"+ finalHealth,"Temprature" +"\n"+ finaltemp,"Voltage" +"\n"+ oneDecimalVolt +" V"};

        list_battery_info = (ListView) view.findViewById(R.id.list_info_view);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                info_type
        );
        list_battery_info.setAdapter(listViewAdapter);

        return view;
    }

    public double getBatteryCapacity(Context context) {
        Object mPowerProfile;
        double batteryCapacity = 0;
        final String POWER_PROFILE_CLASS = "com.android.internal.os.PowerProfile";

        try {
            mPowerProfile = Class.forName(POWER_PROFILE_CLASS)
                    .getConstructor(Context.class)
                    .newInstance(context);

            batteryCapacity = (double) Class
                    .forName(POWER_PROFILE_CLASS)
                    .getMethod("getBatteryCapacity")
                    .invoke(mPowerProfile);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return batteryCapacity;

    }
}