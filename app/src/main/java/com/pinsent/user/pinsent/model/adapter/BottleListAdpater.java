package com.pinsent.user.pinsent.model.adapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pinsent.user.pinsent.R;
import com.pinsent.user.pinsent.activity.BottleDoubleListContent;

/**
 * Created by hong on 2017/8/29.
 */

public class BottleListAdpater extends RecyclerView.Adapter<BottleListAdpater.ItemView> {
    private BottleDoubleListContent itemClick;
    private LayoutInflater layoutInflater;
    
    
    public BottleListAdpater(BottleDoubleListContent itemClick,LayoutInflater layoutInflater){
        this.layoutInflater=layoutInflater;
        this.itemClick=itemClick;
    }
    @Override
    public ItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_child_item, parent, false);
        ItemView itemView = new ItemView(view);
        return itemView;
    }

    @Override
    public void onBindViewHolder(ItemView holder, int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(onClick);
        holder.itemView.setOnLongClickListener(onLongClick);
    }

    @Override
    public int getItemCount() {
        return 4;
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
