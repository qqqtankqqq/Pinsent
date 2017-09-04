package com.pinsent.user.pinsent.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.pinsent.user.pinsent.R;
import com.pinsent.user.pinsent.model.adapter.BottleListAdpater;

/**
 * Created by hong on 2017/8/25.
 */

public class BottleListActivity extends AppCompatActivity {
    private Activity activity;
    private ImageView mListSetting;
    private RecyclerView mListRecycleListView;
    private BottleListAdpater mListAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottle_list);
        activity = this;

        mListSetting = (ImageView) findViewById(R.id.list_setting);
        mListRecycleListView = (RecyclerView) findViewById(R.id.list_recycleListView);

        mListAdapter = new BottleListAdpater(null,getLayoutInflater());

        mListRecycleListView.setLayoutManager(new LinearLayoutManager(activity));
        mListRecycleListView.setAdapter(mListAdapter);

    }
}
