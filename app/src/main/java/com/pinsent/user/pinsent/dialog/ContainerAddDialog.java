package com.pinsent.user.pinsent.dialog;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.pinsent.user.pinsent.R;

/**
 * Created by cheng on 2017/9/8.
 */

public class ContainerAddDialog extends DialogFragment {
    private Spinner brand;
    private Spinner containerPosition;
    private ArrayAdapter<CharSequence> brandList;
    private ArrayAdapter<CharSequence> containerList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_container_add, container);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        brand = (Spinner) view.findViewById(R.id.brand);
        containerPosition = (Spinner) view.findViewById(R.id.container_position);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        brandList = ArrayAdapter.createFromResource(getActivity(),
                R.array.brand,
                android.R.layout.simple_spinner_dropdown_item);
        containerList = ArrayAdapter.createFromResource(getActivity(),
                R.array.container,
                android.R.layout.simple_spinner_dropdown_item);
        brand.setAdapter(brandList);
        containerPosition.setAdapter(containerList);
    }
}
