package com.pinsent.user.pinsent.model;

import android.os.Handler;
import android.os.Message;

import com.android.volley.VolleyError;
import com.pinsent.user.pinsent.model.network.Api;

import java.util.HashMap;
/**
 * Created by cheng on 2017/9/9.
 */

public class GetListThread extends Thread{
    private Handler handler;
    private Api api;
    private String userID;
    public GetListThread(Handler handler, Api api,String userID){
        this.handler=handler;
        this.api=api;
        this.userID=userID;
    }
    @Override
    public void run() {
        super.run();
        api.setOnSensorStatus(sensorStatus);
        while(true){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            HashMap data = new HashMap();
            data.put("userID",userID);
            api.getSensorStatus(data);
        }

    }
    Api.OnSensorStatus sensorStatus=new Api.OnSensorStatus() {
        @Override
        public void onResponse(String response) {
            Message message=handler.obtainMessage(1,response);
            handler.sendMessage(message);
        }

        @Override
        public void onError(VolleyError error) {

        }
    };
}
