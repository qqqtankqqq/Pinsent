package com.pinsent.user.pinsent.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.pinsent.user.pinsent.R;
import com.pinsent.user.pinsent.core.ActivityLauncher;
import com.pinsent.user.pinsent.dialog.ContainerOptionDialog;
import com.pinsent.user.pinsent.dialog.DeviceOptionDialog;
import com.pinsent.user.pinsent.model.DataStruct;
import com.pinsent.user.pinsent.model.GetListThread;
import com.pinsent.user.pinsent.model.LoginPreferences;
import com.pinsent.user.pinsent.model.adapter.MenuGroupAdapter;
import com.pinsent.user.pinsent.model.network.Api;
import com.pinsent.user.pinsent.service.NotificationService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cheng on 2017/8/31.
 */

public class MenuActivity extends AppCompatActivity implements MenuContent {
    private ExpandableListView listView;
    private ImageView mListSetting;
    private ImageView mListAddDevice;
    private MenuGroupAdapter mListAdapter;
    private LoginPreferences mLoginPreferences;
    private ArrayList<HashMap<String, ArrayList<DataStruct>>> dataList;
    private Api api;
    private GetListThread getListThread;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                try {
                    dataList.clear();
                    JSONArray jsonArray = new JSONArray(String.valueOf(msg.obj));
                    for (int i = 0; i < jsonArray.length(); i++) {
                        HashMap hashMap = new HashMap();
                        ArrayList arrayList = new ArrayList();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        JSONArray jsonArray1 = jsonObject.getJSONArray("data");
                        for (int j = 0; j < jsonArray1.length(); j++) {
                            JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                            DataStruct dataStruct = new DataStruct();
                            dataStruct.setContainerName(jsonObject1.getString("containerName"));
                            dataStruct.setContainerPosition(jsonObject1.getString("containerPosition"));
                            dataStruct.setBrand(jsonObject1.getString("brand"));
                            dataStruct.setPercent(jsonObject1.getInt("percent"));
                            arrayList.add(dataStruct);
                        }
                        hashMap.put("name", jsonObject.getString("deviceName"));
                        hashMap.put("id", jsonObject.get("deviceID"));
                        hashMap.put("data", arrayList);
                        dataList.add(hashMap);
                    }
                    setAdapter();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();
        findview();
    }

    @Override
    protected void onStart() {
        super.onStart();
        HashMap data = new HashMap();
        data.put("userID", mLoginPreferences.getUserId());
        api.setOnSensorStatus(sensorStatus);
        api.getSensorStatus(data);
        getListThread = new GetListThread(handler, api, mLoginPreferences.getUserId());
        getListThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getListThread = null;
    }

    private void init() {
        dataList = new ArrayList<>();
        api = new Api(this);
        mLoginPreferences = new LoginPreferences(this);

        Intent intentService = new Intent(this, NotificationService.class);
        Bundle bundle = new Bundle();
        bundle.putString("userID", mLoginPreferences.getUserId());
        intentService.putExtras(bundle);
        startService(intentService);

        LocalBroadcastManager.getInstance(this).registerReceiver(
                mMessageReceiver, new IntentFilter("notify"));

    }

    private void findview() {
        listView = (ExpandableListView) findViewById(R.id.list_listView);
        mListSetting = (ImageView) findViewById(R.id.list_setting);
        mListAddDevice = (ImageView) findViewById(R.id.list_add_device);
        mListSetting.setOnClickListener(settingClick);
        mListAddDevice.setOnClickListener(addDeviceClick);
    }

    private void setAdapter() {
        if (mListAdapter == null) {
            mListAdapter = new MenuGroupAdapter(this, dataList);
            listView.setAdapter(mListAdapter);
            listView.setGroupIndicator(null);
            listView.setOnGroupClickListener(onGroupClick);
            listView.setOnItemLongClickListener(onGroupLongClick);
        } else {
            mListAdapter.init();
            mListAdapter.notifyDataSetChanged();
        }

    }

    Api.OnSensorStatus sensorStatus = new Api.OnSensorStatus() {
        @Override
        public void onResponse(String response) {
            try {
                dataList.clear();
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    HashMap hashMap = new HashMap();
                    ArrayList arrayList = new ArrayList();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    JSONArray jsonArray1 = jsonObject.getJSONArray("data");
                    for (int j = 0; j < jsonArray1.length(); j++) {
                        JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                        DataStruct dataStruct = new DataStruct();
                        dataStruct.setContainerName(jsonObject1.getString("containerName"));
                        dataStruct.setContainerPosition(jsonObject1.getString("containerPosition"));
                        dataStruct.setBrand(jsonObject1.getString("brand"));
                        dataStruct.setPercent(jsonObject1.getInt("percent"));
                        arrayList.add(dataStruct);
                    }
                    hashMap.put("name", jsonObject.getString("deviceName"));
                    hashMap.put("id", jsonObject.get("deviceID"));
                    hashMap.put("data", arrayList);
                    dataList.add(hashMap);
                }
                setAdapter();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(VolleyError error) {

        }
    };

    private View.OnClickListener addDeviceClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ActivityLauncher.go(MenuActivity.this, AddDeviceActivity.class, null);
        }
    };

    private View.OnClickListener settingClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ActivityLauncher.go(MenuActivity.this, FeedBackActivity.class, null);
        }
    };
    private ExpandableListView.OnGroupClickListener onGroupClick = new ExpandableListView.OnGroupClickListener() {
        @Override
        public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
            long listViewPosition = expandableListView.getExpandableListPosition(i);
            int groupPositionCount = ExpandableListView.getPackedPositionGroup(listViewPosition);
            mListAdapter.setExpansion(i);
            mListAdapter.notifyDataSetChanged();
            return false;
        }
    };
    private AdapterView.OnItemLongClickListener onGroupLongClick = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            long listViewPosition = listView.getExpandableListPosition(i);
            int itemType = ExpandableListView.getPackedPositionType(listViewPosition);
            int groupPositionCount = ExpandableListView.getPackedPositionGroup(listViewPosition);
            if (itemType == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
//                showToast("群組長壓");
            }
            DeviceOptionDialog addDeviceDialog = new DeviceOptionDialog();
            Bundle bundle = new Bundle();
            bundle.putSerializable("data", dataList);
            bundle.putInt("group", groupPositionCount);
            addDeviceDialog.setArguments(bundle);
            addDeviceDialog.show(getFragmentManager(), DeviceOptionDialog.class.getSimpleName());
            return true;
        }
    };

    @Override
    public void onChildClick(View v) {
//        ((TextView) v.findViewById(R.id.list_id)).setText("群組：" + ((HashMap) v.getTag()).get("group") + " 容器：" + ((HashMap) v.getTag()).get("child") + "click");
    }

    @Override
    public void onChildLongClick(View v) {
        ContainerOptionDialog containerOptionDialog = new ContainerOptionDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", dataList);
        bundle.putInt("group", (int) ((HashMap) v.getTag()).get("group"));
        bundle.putInt("child", (int) ((HashMap) v.getTag()).get("child"));
        containerOptionDialog.setArguments(bundle);
        containerOptionDialog.show(getFragmentManager(), ContainerOptionDialog.class.getSimpleName());
    }

    @Override
    public void onGroupDialogCallBack() {

        HashMap data = new HashMap();
        data.put("userID", mLoginPreferences.getUserId());
        api.getSensorStatus(data);

    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            NotificationManager manager = (NotificationManager)
                    getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(MenuActivity.this);

            builder.setContentText("有容器未滿30%，記得補充唷!")
                    .setContentTitle("通知")
                    .setSmallIcon(R.mipmap.ic_launcher_round);
            Notification notification = builder.build();
            manager.notify(1, notification);

        }
    };


    private void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
