package com.pinsent.user.pinsent.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.android.volley.VolleyError;
import com.pinsent.user.pinsent.model.network.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static android.R.id.message;
import static com.google.zxing.qrcode.decoder.ErrorCorrectionLevel.H;

/**
 * Created by user_2 on 2017/9/9.
 */

public class NotificationService extends Service {
    private Api api;
    private String userId;


    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getExtras() != null) {
            userId = intent.getStringExtra("userID");
        }
        final HashMap<String, String> data = new HashMap<>();
        data.put("userID", userId);
        api.setOnSensorStatus(onSensorStatus);
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                api.getSensorStatus(data);
                handler.postDelayed(this, 5000);
            }
        };

        handler.post(runnable);
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        api = new Api(getApplicationContext());
    }

    private Api.OnSensorStatus onSensorStatus = new Api.OnSensorStatus() {
        @Override
        public void onResponse(String response) {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    JSONArray data = jsonObject.getJSONArray("data");
                    for (int j = 0; j < data.length(); j++) {
                        JSONObject dataJsonObject = data.getJSONObject(i);
                        int percent = dataJsonObject.getInt("percent");
                        if (percent < 30) {
                            sendMessageToActivity("less");
                            Log.e("TAG", "onResponse:" + percent);
                            break;
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(VolleyError error) {

        }
    };

    private void sendMessageToActivity(String msg) {
        Intent intent = new Intent("notify");
        intent.putExtra("notify", msg);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }
}
