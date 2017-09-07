package com.pinsent.user.pinsent.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pinsent.user.pinsent.R;

/**
 * Created by cheng on 2017/9/7.
 */

public class ContainerOptionDialog extends DialogFragment{
    private TextView update;
    private TextView delete;
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
