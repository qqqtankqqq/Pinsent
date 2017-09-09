package com.pinsent.user.pinsent.dialog;

import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.pinsent.user.pinsent.R;
import com.pinsent.user.pinsent.activity.MenuActivity;
import com.pinsent.user.pinsent.activity.MenuContent;
import com.pinsent.user.pinsent.core.StringNormalization;
import com.pinsent.user.pinsent.model.DataStruct;
import com.pinsent.user.pinsent.model.LoginPreferences;
import com.pinsent.user.pinsent.model.ToastCreater;
import com.pinsent.user.pinsent.model.network.Api;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.jar.JarOutputStream;

/**
 * Created by cheng on 2017/9/8.
 */

public class ContainerAddDialog extends DialogFragment {
    private TextView title;
    private Spinner brand;
    private Spinner containerPosition;
    private EditText content;
    private Button submit;
    private String brandList[]={"品牌A","品牌B","品牌C","品牌D"};
    private String containerList[]={"1","2","3","4"};
    private ArrayAdapter<String> brandAdapter;
    private ArrayAdapter<String> containerAdapter;
    private Bundle bundle;
    private MenuContent callback;
    private Api api;
    private LoginPreferences preferences;
    private Activity activity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_container_add, container);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        title= (TextView) view.findViewById(R.id.title);
        brand = (Spinner) view.findViewById(R.id.brand);
        containerPosition = (Spinner) view.findViewById(R.id.container_position);
        content= (EditText) view.findViewById(R.id.content);
        submit=(Button)view.findViewById(R.id.submit);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        api=new Api(getActivity());
        bundle=getArguments();
        callback=(MenuActivity)getActivity();
        activity=getActivity();
        preferences=new LoginPreferences(getActivity());
        brandAdapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_dropdown_item,brandList);
        containerAdapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_dropdown_item,containerList);
        brand.setAdapter(brandAdapter);
        containerPosition.setAdapter(containerAdapter);
        api.setOnSensorCreate(sensorCreate);
        title.setText(((ArrayList<HashMap<String,String>>)bundle.get("data")).get(bundle.getInt("group")).get("name"));
        submit.setOnClickListener(submitClick);
    }
    View.OnClickListener submitClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(content.getText().toString().isEmpty()){
                ToastCreater.makeText(getActivity(),"請填寫內容物", Toast.LENGTH_SHORT);
            }else{
                HashMap map=new HashMap();
                map.put("userID",preferences.getUserId());
                map.put("deviceID",((ArrayList<HashMap<String,String>>)bundle.get("data")).get(bundle.getInt("group")).get("id"));
                map.put("containerPosition",containerList[containerPosition.getSelectedItemPosition()]);
                map.put("containerName",content.getText().toString());
                map.put("brand",brandList[brand.getSelectedItemPosition()]);
                api.createSensor(map);
                ContainerAddDialog.this.dismiss();
            }

        }
    };
    Api.OnCreateSensor sensorCreate=new Api.OnCreateSensor() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonObject=new JSONObject(response);
                ToastCreater.makeText(activity,jsonObject.getString("message"), Toast.LENGTH_SHORT);
                callback.onGroupDialogCallBack();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(VolleyError error) {

        }
    };
}
