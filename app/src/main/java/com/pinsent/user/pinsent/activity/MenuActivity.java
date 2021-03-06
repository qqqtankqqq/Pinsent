package com.pinsent.user.pinsent.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pinsent.user.pinsent.R;
import com.pinsent.user.pinsent.dialog.ContainerOptionDialog;
import com.pinsent.user.pinsent.dialog.DeviceOptionDialog;
import com.pinsent.user.pinsent.model.DataStruct;
import com.pinsent.user.pinsent.model.adapter.MenuGroupAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cheng on 2017/8/31.
 */

public class MenuActivity extends AppCompatActivity implements MenuContent {
    private ExpandableListView listView;
    private ImageView mListSetting;
    private MenuGroupAdapter mListAdapter;
    private ArrayList<HashMap<String,ArrayList<DataStruct>>> dataList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        simulation();
        findview();
        setAdapter();
    }
    private void simulation(){
        dataList=new ArrayList();
        for(int i=0;i<2;i++){
            HashMap hashMap=new HashMap();
            ArrayList arrayList=new ArrayList();
            for (int j=0;j<4;j++){
                DataStruct dataStruct=new DataStruct();
                dataStruct.setContainerPosition(String.valueOf(j));
                if(i==0){
                    dataStruct.setContainerName("沐浴乳"+j+"號");
                }else{
                    dataStruct.setContainerName("洗髮精"+j+"號");
                }
                dataStruct.setPercent(76);
                dataStruct.setBrand("apple");
                arrayList.add(dataStruct);
            }
            hashMap.put("title","title"+i+"號");
            hashMap.put("data",arrayList);
            dataList.add(hashMap);
        }

    }
    private void findview(){
        listView= (ExpandableListView) findViewById(R.id.list_listView);
        mListSetting = (ImageView) findViewById(R.id.list_setting);
    }
    private void setAdapter(){
        mListAdapter=new MenuGroupAdapter(this,dataList);
        listView.setAdapter(mListAdapter);
        listView.setGroupIndicator(null);
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
//                showToast("群組長壓");
            }
            DeviceOptionDialog addDeviceDialog=new DeviceOptionDialog();
            Bundle bundle=new Bundle();
            bundle.putSerializable("data",dataList);
            bundle.putInt("group",groupPositionCount);
            addDeviceDialog.setArguments(bundle);
            addDeviceDialog.show(getFragmentManager(),DeviceOptionDialog.class.getSimpleName());
            return true;
        }
    };
    @Override
    public void onChildClick(View v) {
        ((TextView)v.findViewById(R.id.list_id)).setText("群組："+((HashMap)v.getTag()).get("group")+" 容器："+((HashMap)v.getTag()).get("child")+"click");
    }

    @Override
    public void onChildLongClick(View v) {
        ContainerOptionDialog containerOptionDialog=new ContainerOptionDialog();
        Bundle bundle=new Bundle();
        bundle.putSerializable("data",dataList);
        bundle.putInt("group",(int)((HashMap)v.getTag()).get("group"));
        bundle.putInt("child",(int)((HashMap)v.getTag()).get("child"));
        containerOptionDialog.setArguments(bundle);
        containerOptionDialog.show(getFragmentManager(),ContainerOptionDialog.class.getSimpleName());
    }

    @Override
    public void onGroupDialogCallBack(String type) {
        showToast(type);
    }


    private void showToast(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }
}
