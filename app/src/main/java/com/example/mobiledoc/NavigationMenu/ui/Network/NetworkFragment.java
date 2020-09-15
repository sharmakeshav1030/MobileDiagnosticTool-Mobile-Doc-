package com.example.mobiledoc.NavigationMenu.ui.Network;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.mobiledoc.R;

/*******
 * <p> Title: NetworkFragment.java</p>
 *
 * <p> Description: A Java class which extends from fragments and
 *                  displays the Network Info.</p>
 *
 * @author Keshav Sharma
 *
 * @version 1.00	2019-08-22
 *
 */

public class NetworkFragment extends Fragment {

    ListView list_network_info;
    TelephonyManager tel;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_mobile_info,container,false);

        tel = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);

        String[] info_type = {"\n\n","Network Operator Code : " + "\n" +tel.getNetworkOperator().toString()
                , "Network Operator Name : " + "\n" + tel.getNetworkOperatorName().toString()
                , "Network Type : " + "\n" +tel.getNetworkType()
                , "Country ISO : " + "\n" +tel.getNetworkCountryIso().toString()};

        list_network_info = (ListView) view.findViewById(R.id.list_info_view);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                info_type
        );
        list_network_info.setAdapter(listViewAdapter);
        return view;
    }
}

