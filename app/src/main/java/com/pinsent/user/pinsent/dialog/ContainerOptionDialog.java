package com.pinsent.user.pinsent.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.pinsent.user.pinsent.R;
import com.pinsent.user.pinsent.activity.MenuActivity;
import com.pinsent.user.pinsent.activity.MenuContent;
import com.pinsent.user.pinsent.model.DataStruct;
import com.pinsent.user.pinsent.model.LoginPreferences;
import com.pinsent.user.pinsent.model.network.Api;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cheng on 2017/9/7.
 */

public class ContainerOptionDialog extends DialogFragment{
    private TextView update;
    private TextView delete;
    private Bundle bundle;
    private Api api;
    private MenuContent callBack;
    private LoginPreferences preferences;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialog_container_option,container);
        update= (TextView) view.findViewById(R.id.update);
        delete= (TextView) view.findViewById(R.id.delete);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        api=new Api(getActivity());
        bundle=getArguments();
        callBack=(MenuActivity)getActivity();
        preferences=new LoginPreferences(getActivity());
        update.setOnClickListener(updateClick);
        delete.setOnClickListener(deleteClick);

    }
    View.OnClickListener updateClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ContainerOptionDialog.this.dismiss();
        }
    };
    View.OnClickListener deleteClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            ContainerOptionDialog.this.dismiss();
        }
    };

}
