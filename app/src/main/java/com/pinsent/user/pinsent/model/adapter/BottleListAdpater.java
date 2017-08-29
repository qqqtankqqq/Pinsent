package com.pinsent.user.pinsent.model.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pinsent.user.pinsent.R;

/**
 * Created by hong on 2017/8/29.
 */

public class BottleListAdpater extends RecyclerView.Adapter<BottleListAdpater.ItemView> {
    private Context context;
    private LayoutInflater layoutInflater;
    
    
    public BottleListAdpater(Context context){
        this.context=context;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public ItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        ItemView itemView = new ItemView(view);
        return itemView;
    }

    @Override
    public void onBindViewHolder(ItemView holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    protected class ItemView extends RecyclerView.ViewHolder {
 
        
        public ItemView(View itemView) {
            super(itemView);
       
        }
    }

}
