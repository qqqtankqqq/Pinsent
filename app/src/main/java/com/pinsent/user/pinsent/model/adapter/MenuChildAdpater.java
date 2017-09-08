package com.pinsent.user.pinsent.model.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pinsent.user.pinsent.R;
import com.pinsent.user.pinsent.activity.MenuActivity;
import com.pinsent.user.pinsent.activity.MenuContent;
import com.pinsent.user.pinsent.model.DataStruct;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hong on 2017/8/29.
 */

public class MenuChildAdpater extends RecyclerView.Adapter<MenuChildAdpater.ItemView> {
    private MenuContent itemClick;
    private LayoutInflater layoutInflater;
    private ArrayList<DataStruct> dataList;
    private int groupPosition;

    public MenuChildAdpater(MenuActivity activity, ArrayList dataList, int groupPosition){
        layoutInflater=activity.getLayoutInflater();
        this.itemClick=activity;
        this.dataList=dataList;
        this.groupPosition=groupPosition;
    }
    @Override
    public ItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_child_item, parent, false);
        ItemView itemView = new ItemView(view);
        return itemView;
    }

    @Override
    public void onBindViewHolder(ItemView holder, int position) {
        ((TextView)holder.itemView.findViewById(R.id.list_id)).setText(dataList.get(position).getContainerName());
        HashMap hashMap=new HashMap();
        hashMap.put("group",groupPosition);
        hashMap.put("child",position);
        holder.itemView.setTag(hashMap);
        holder.itemView.setOnClickListener(onClick);
        holder.itemView.setOnLongClickListener(onLongClick);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    protected class ItemView extends RecyclerView.ViewHolder {
        public ItemView(View itemView) {
            super(itemView);
       
        }
    }
    private View.OnClickListener onClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            itemClick.onChildClick(view);
        }
    };
    private View.OnLongClickListener onLongClick=new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            itemClick.onChildLongClick(view);
            return true;
        }
    };
}
