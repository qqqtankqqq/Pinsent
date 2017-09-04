package com.pinsent.user.pinsent.model.adapter;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.pinsent.user.pinsent.R;
import com.pinsent.user.pinsent.activity.BottleDoubleListActivity;
import com.pinsent.user.pinsent.core.DpToPx;

/**
 * Created by cheng on 2017/8/31.
 */

public class BottleDoubleListAdapter extends BaseExpandableListAdapter {
    private BottleDoubleListActivity activity;
    private RecyclerView mListRecycleListView;
    private DpToPx dpToPx;
    private BottleListAdpater adpater;
    private ImageView imageView;
    private boolean expansion[]={false,false};
    public BottleDoubleListAdapter(BottleDoubleListActivity activity, DpToPx dpToPx, BottleListAdpater adpater) {
        this.activity=activity;
        this.dpToPx=dpToPx;
        this.adpater=adpater;
    }

    @Override
    public int getGroupCount() {
        return 2;
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        view=activity.getLayoutInflater().inflate(R.layout.list_group_item,viewGroup,false);
        imageView=(ImageView)view.findViewById(R.id.item_group_expansion);
        if (expansion[i]){
            imageView.setImageResource(R.mipmap.ic_right);
        }else{
            imageView.setImageResource(R.mipmap.ic_down);
        }
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        view=activity.getLayoutInflater().inflate(R.layout.list_list_child,viewGroup,false);
        ((RecyclerView)view.findViewById(R.id.list)).setAdapter(adpater);
        ((RecyclerView)view.findViewById(R.id.list)).setLayoutManager(new LinearLayoutManager(activity));
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
    public void setExpansion(int position){
        expansion[position]=!expansion[position];
    }
}
