package com.pinsent.user.pinsent.model.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pinsent.user.pinsent.R;
import com.pinsent.user.pinsent.activity.MenuActivity;
import com.pinsent.user.pinsent.core.DpToPx;
import com.pinsent.user.pinsent.model.DataStruct;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cheng on 2017/8/31.
 */

public class MenuGroupAdapter extends BaseExpandableListAdapter {
    private MenuActivity activity;
    private ImageView imageView;
    private boolean expansion[];
    private ArrayList<HashMap<String,ArrayList<DataStruct>>> dataList;
    public MenuGroupAdapter(MenuActivity activity, ArrayList dataList) {
        this.activity=activity;
        this.dataList=dataList;
        init();
    }
    public void init(){
        if(expansion==null){
            expansion=new boolean[dataList.size()];
        }else if(dataList.size()>expansion.length){
            boolean tmp[]=expansion;
            expansion=new boolean[dataList.size()];
            for (int i=0;i<tmp.length;i++){
                expansion[i]=tmp[i];
            }
            for (int i=tmp.length;i<dataList.size();i++){
                expansion[i]=false;
            }
        }else if(dataList.size()<expansion.length){
            boolean tmp[]=expansion;
            expansion=new boolean[dataList.size()];
            for (int i=0;i<expansion.length;i++){
                expansion[i]=tmp[i];
            }
        }
    }
    @Override
    public int getGroupCount() {
        return dataList.size();
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

        ((TextView)view.findViewById(R.id.item_group_title)).setText(String.valueOf(dataList.get(i).get("name")));
        imageView=(ImageView)view.findViewById(R.id.item_group_expansion);
        if (expansion[i]){
            view.findViewById(R.id.layout).setBackgroundResource(R.drawable.list_group_expend);
            imageView.setImageResource(R.mipmap.ic_right);
        }else{
            view.findViewById(R.id.layout).setBackgroundResource(R.drawable.list_group_normal);
            imageView.setImageResource(R.mipmap.ic_down);
        }

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        MenuChildAdpater adpater= new MenuChildAdpater(activity,dataList.get(i).get("data"),i);
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
