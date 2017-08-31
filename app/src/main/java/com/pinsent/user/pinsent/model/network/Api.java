package com.pinsent.user.pinsent.model.network;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pinsent.user.pinsent.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hong on 2017/8/31.
 */

public class Api {
    private Context context;
    private RequestQueue queue;
    private String apiUrl;
    private HashMap<String, String> mHeaderHashMap;
    private OnRegister onRegister;
    private OnLogin onLogin;
    private OnCreateDevice onCreateDevice;
    private OnCreateSensor onCreateSensor;
    private OnSensorStatus onSensorStatus;
    private OnUpdateSensor onUpdateSensor;
    private OnDeleteDevice onDeleteDevice;
    private OnDeleteSensor onDeleteSensor;

    public Api(Context context) {
        this.context = context;
        queue = Volley.newRequestQueue(context);
        apiUrl = context.getResources().getString(R.string.api_url);

        mHeaderHashMap = new HashMap<>();
        mHeaderHashMap.put("Content-Type", "application/json");
        mHeaderHashMap.put("Accept", "application/json");
    }

    public interface OnRegister {
        void onResponse(String response);

        void onError(VolleyError error);
    }

    public interface OnLogin {
        void onResponse(String response);

        void onError(VolleyError error);
    }

    public interface OnCreateDevice {
        void onResponse(String response);

        void onError(VolleyError error);
    }

    public interface OnCreateSensor {
        void onResponse(String response);

        void onError(VolleyError error);
    }

    public interface OnSensorStatus {
        void onResponse(String response);

        void onError(VolleyError error);
    }

    public interface OnUpdateSensor {
        void onResponse(String response);

        void onError(VolleyError error);
    }

    public interface OnDeleteSensor {
        void onResponse(String response);

        void onError(VolleyError error);
    }

    public interface OnDeleteDevice {
        void onResponse(String response);

        void onError(VolleyError error);
    }

    public void setOnRegister(OnRegister onRegister) {
        this.onRegister = onRegister;
    }

    public void setOnLogin(OnLogin onLogin) {
        this.onLogin = onLogin;
    }

    public void setOnCreateDevice(OnCreateDevice onCreateDevice) {
        this.onCreateDevice = onCreateDevice;
    }

    public void setOnSensorCreate(OnCreateSensor onCreateSensor) {
        this.onCreateSensor = onCreateSensor;
    }

    public void setOnUpdateSensor(OnUpdateSensor onUpdateSensor) {
        this.onUpdateSensor = onUpdateSensor;
    }

    public void setOnSensorStatus(OnSensorStatus onSensorStatus) {
        this.onSensorStatus = onSensorStatus;
    }

    public void setOnDeleteDevice(OnDeleteDevice onDeleteDevice) {
        this.onDeleteDevice = onDeleteDevice;
    }

    public void setOnDeleteSensor(OnDeleteSensor onDeleteSensor) {
        this.onDeleteSensor = onDeleteSensor;
    }

    public void register(final HashMap<String, String> params) {
        String function = context.getResources().getString(R.string.register_url);
        String url = apiUrl + function;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onRegister.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse mNetworkResponse = error.networkResponse;
                        if (mNetworkResponse != null && mNetworkResponse.data != null && mNetworkResponse.statusCode == 422) {
                            String response = new String(mNetworkResponse.data);
                            onRegister.onResponse(response);
                        } else {
                            onRegister.onError(error);
                        }
                    }
                }) {

            @Override
            public byte[] getBody() throws AuthFailureError {
                SplitArray mSplitArray = new SplitArray();
                String request = mSplitArray.getJsonString(params);
                return request.getBytes();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return mHeaderHashMap;
            }
        };
        queue.add(stringRequest);
    }

    public void login(final HashMap<String, String> params) {
        String function = context.getResources().getString(R.string.login_url);
        String url = apiUrl + function;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onLogin.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse mNetworkResponse = error.networkResponse;
                        if (mNetworkResponse != null && mNetworkResponse.data != null && mNetworkResponse.statusCode == 422) {
                            String response = new String(mNetworkResponse.data);
                            onLogin.onResponse(response);
                        } else {
                            onLogin.onError(error);
                        }
                    }
                }) {

            @Override
            public byte[] getBody() throws AuthFailureError {
                SplitArray mSplitArray = new SplitArray();
                String request = mSplitArray.getJsonString(params);
                return request.getBytes();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return mHeaderHashMap;
            }
        };
        queue.add(stringRequest);
    }

    public void createDevice(final HashMap<String, String> params) {
        String function = context.getResources().getString(R.string.create_device_url);
        String url = apiUrl + function;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onCreateDevice.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse mNetworkResponse = error.networkResponse;
                        if (mNetworkResponse != null && mNetworkResponse.data != null && mNetworkResponse.statusCode == 422) {
                            String response = new String(mNetworkResponse.data);
                            onCreateDevice.onResponse(response);
                        } else {
                            onCreateDevice.onError(error);
                        }
                    }
                }) {

            @Override
            public byte[] getBody() throws AuthFailureError {
                SplitArray mSplitArray = new SplitArray();
                String request = mSplitArray.getJsonString(params);
                return request.getBytes();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return mHeaderHashMap;
            }
        };
        queue.add(stringRequest);
    }

    public void createSensor(final HashMap<String, String> params) {
        String function = context.getResources().getString(R.string.create_device_url);
        String url = apiUrl + function;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onCreateSensor.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse mNetworkResponse = error.networkResponse;
                        if (mNetworkResponse != null && mNetworkResponse.data != null && mNetworkResponse.statusCode == 422) {
                            String response = new String(mNetworkResponse.data);
                            onCreateSensor.onResponse(response);
                        } else {
                            onCreateSensor.onError(error);
                        }
                    }
                }) {

            @Override
            public byte[] getBody() throws AuthFailureError {
                SplitArray mSplitArray = new SplitArray();
                String request = mSplitArray.getJsonString(params);
                return request.getBytes();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return mHeaderHashMap;
            }
        };
        queue.add(stringRequest);
    }

    public void getSensorStatus(final HashMap<String, String> params) {
        String function = context.getResources().getString(R.string.status_url);
        String url = apiUrl + function;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onSensorStatus.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse mNetworkResponse = error.networkResponse;
                        if (mNetworkResponse != null && mNetworkResponse.data != null && mNetworkResponse.statusCode == 422) {
                            String response = new String(mNetworkResponse.data);
                            onSensorStatus.onResponse(response);
                        } else {
                            onSensorStatus.onError(error);
                        }
                    }
                }) {

            @Override
            public byte[] getBody() throws AuthFailureError {
                SplitArray mSplitArray = new SplitArray();
                String request = mSplitArray.getJsonString(params);
                return request.getBytes();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return mHeaderHashMap;
            }
        };
        queue.add(stringRequest);
    }

    public void updateSensor(final HashMap<String, String> params) {
        String function = context.getResources().getString(R.string.update_url);
        String url = apiUrl + function;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onUpdateSensor.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse mNetworkResponse = error.networkResponse;
                        if (mNetworkResponse != null && mNetworkResponse.data != null && mNetworkResponse.statusCode == 422) {
                            String response = new String(mNetworkResponse.data);
                            onUpdateSensor.onResponse(response);
                        } else {
                            onUpdateSensor.onError(error);
                        }
                    }
                }) {

            @Override
            public byte[] getBody() throws AuthFailureError {
                SplitArray mSplitArray = new SplitArray();
                String request = mSplitArray.getJsonString(params);
                return request.getBytes();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return mHeaderHashMap;
            }
        };
        queue.add(stringRequest);
    }

    public void deleteDevice(final HashMap<String, String> params) {
        String function = context.getResources().getString(R.string.delete_device_url);
        String url = apiUrl + function;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onDeleteDevice.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse mNetworkResponse = error.networkResponse;
                        if (mNetworkResponse != null && mNetworkResponse.data != null && mNetworkResponse.statusCode == 422) {
                            String response = new String(mNetworkResponse.data);
                            onDeleteDevice.onResponse(response);
                        } else {
                            onDeleteDevice.onError(error);
                        }
                    }
                }) {

            @Override
            public byte[] getBody() throws AuthFailureError {
                SplitArray mSplitArray = new SplitArray();
                String request = mSplitArray.getJsonString(params);
                return request.getBytes();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return mHeaderHashMap;
            }
        };
        queue.add(stringRequest);
    }

    public void deleteSensor(final HashMap<String, String> params) {
        String function = context.getResources().getString(R.string.deletes_sensor_url);
        String url = apiUrl + function;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onDeleteSensor.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse mNetworkResponse = error.networkResponse;
                        if (mNetworkResponse != null && mNetworkResponse.data != null && mNetworkResponse.statusCode == 422) {
                            String response = new String(mNetworkResponse.data);
                            onDeleteSensor.onResponse(response);
                        } else {
                            onDeleteSensor.onError(error);
                        }
                    }
                }) {

            @Override
            public byte[] getBody() throws AuthFailureError {
                SplitArray mSplitArray = new SplitArray();
                String request = mSplitArray.getJsonString(params);
                return request.getBytes();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return mHeaderHashMap;
            }
        };
        queue.add(stringRequest);
    }
}
