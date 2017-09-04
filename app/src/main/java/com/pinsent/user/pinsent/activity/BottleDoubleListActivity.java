package com.pinsent.user.pinsent.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pinsent.user.pinsent.R;
import com.pinsent.user.pinsent.core.DpToPx;
import com.pinsent.user.pinsent.model.adapter.BottleDoubleListAdapter;
import com.pinsent.user.pinsent.model.adapter.BottleListAdpater;

/**
 * Created by cheng on 2017/8/31.
 */

public class BottleDoubleListActivity extends AppCompatActivity implements BottleDoubleListContent{
    private ExpandableListView listView;
    private ImageView mListSetting;
    private BottleDoubleListAdapter mListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottle_double_list);
        findview();
        setAdapter();
    }

    private void findview(){
        listView= (ExpandableListView) findViewById(R.id.list_listView);
        mListSetting = (ImageView) findViewById(R.id.list_setting);
    }
    private void setAdapter(){
        mListAdapter=new BottleDoubleListAdapter(this,new DpToPx(this),new BottleListAdpater(this,getLayoutInflater()));
        listView.setAdapter(mListAdapter);
        listView.setOnGroupClickListener(onGroupClick);
        listView.setOnItemLongClickListener(onGroupLongClick);
    }

    private ExpandableListView.OnGroupClickListener onGroupClick=new ExpandableListView.OnGroupClickListener() {
        @Override
        public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
            long listViewPosition = expandableListView.getExpandableListPosition(i);
            int groupPositionCount = ExpandableListView.getPackedPositionGroup(listViewPosition);
            mListAdapter.setExpansion(groupPositionCount);
            mListAdapter.notifyDataSetChanged();
            return false;
        }
    };
    private AdapterView.OnItemLongClickListener onGroupLongClick=new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            long listViewPosition = listView.getExpandableListPosition(i);
            int itemType = ExpandableListView.getPackedPositionType(listViewPosition);
            int groupPositionCount = ExpandableListView.getPackedPositionGroup(listViewPosition);
            if(itemType==ExpandableListView.PACKED_POSITION_TYPE_GROUP){
                showToast("群組長壓");
            }
            return true;
        }
    };
    @Override
    public void onChildClick(View v) {
        ((TextView)v.findViewById(R.id.list_id)).setText(v.getTag()+"click");
    }

    @Override
    public void onChildLongClick(View v) {
        ((TextView)v.findViewById(R.id.list_id)).setText(v.getTag()+"long");
    }
    private void showToast(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }
}
