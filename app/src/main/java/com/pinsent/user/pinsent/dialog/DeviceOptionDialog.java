package com.pinsent.user.pinsent.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.pinsent.user.pinsent.R;
import com.pinsent.user.pinsent.activity.MenuActivity;
import com.pinsent.user.pinsent.activity.MenuContent;
import com.pinsent.user.pinsent.model.LoginPreferences;
import com.pinsent.user.pinsent.model.network.Api;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cheng on 2017/9/5.
 */

public class DeviceOptionDialog extends DialogFragment {
    private TextView addTextView;
    private TextView deleteTextView;
    private Bundle bundle;
    private MenuContent callback;
    private Api api;
    private LoginPreferences preferences;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialog_device_option,container);
        addTextView= (TextView) view.findViewById(R.id.add);
        deleteTextView=(TextView)view.findViewById(R.id.delete);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        api=new Api(getActivity());
        bundle=getArguments();
        callback=(MenuActivity)getActivity();
        preferences=new LoginPreferences(getActivity());
        addTextView.setOnClickListener(addClick);
        deleteTextView.setOnClickListener(deleteClick);
        api.setOnDeleteDevice(deleteDevice);
    }
    View.OnClickListener addClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ContainerAddDialog containerAddDialog=new ContainerAddDialog();
            containerAddDialog.setArguments(bundle);
            containerAddDialog.show(getFragmentManager(),ContainerAddDialog.class.getSimpleName());
            DeviceOptionDialog.this.dismiss();
        }
    };
    View.OnClickListener deleteClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            HashMap map=new HashMap();
            map.put("userID",preferences.getUserId());
            map.put("deviceID",((ArrayList<HashMap<String,String>>)bundle.get("data")).get(bundle.getInt("group")).get("id"));
            api.deleteDevice(map);
            DeviceOptionDialog.this.dismiss();
        }
    };
    Api.OnDeleteDevice deleteDevice=new Api.OnDeleteDevice() {
        @Override
        public void onResponse(String response) {
            callback.onGroupDialogCallBack();
        }

        @Override
        public void onError(VolleyError error) {

        }
    };
}
