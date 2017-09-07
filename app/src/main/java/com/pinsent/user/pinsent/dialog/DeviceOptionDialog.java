package com.pinsent.user.pinsent.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pinsent.user.pinsent.R;
import com.pinsent.user.pinsent.activity.BottleDoubleListActivity;
import com.pinsent.user.pinsent.activity.BottleDoubleListContent;

/**
 * Created by cheng on 2017/9/5.
 */

public class DeviceOptionDialog extends DialogFragment {
    private TextView addTextView;
    private TextView deleteTextView;
    private Bundle bundle;
    private BottleDoubleListContent callback;
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
        bundle=getArguments();
        callback=(BottleDoubleListActivity)getActivity();
        addTextView.setOnClickListener(addClick);
        deleteTextView.setOnClickListener(deleteClick);
    }
    View.OnClickListener addClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(),"新增",Toast.LENGTH_SHORT).show();
            DeviceOptionDialog.this.dismiss();
        }
    };
    View.OnClickListener deleteClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(),"刪除",Toast.LENGTH_SHORT).show();
            DeviceOptionDialog.this.dismiss();
        }
    };
}
