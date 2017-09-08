package com.pinsent.user.pinsent.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.pinsent.user.pinsent.R;
import com.pinsent.user.pinsent.core.ActivityLauncher;
import com.pinsent.user.pinsent.model.LoginPreferences;
import com.pinsent.user.pinsent.model.ToastCreater;
import com.pinsent.user.pinsent.model.network.Api;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.pinsent.user.pinsent.activity.QRCodeScannerActivity.INTENT_QR_CONTENT;

/**
 * Created by hong on 2017/8/25.
 */

public class AddDeviceActivity extends AppCompatActivity {
    private Activity activity;
    private Api mApi;
    private LoginPreferences mLoginPreferences;
    private Button mQrCodeButton;
    private EditText mDeviceId;
    private EditText mDeviceNote;
    private Button mAddButton;
    private static final int ASK_PERMISSIONS_REQUEST = 1;
    private String[] permissions = {Manifest.permission.CAMERA};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        findView();
        init();
    }

    private void findView() {
        mQrCodeButton = (Button) findViewById(R.id.qr_code_button);
        mDeviceId = (EditText) findViewById(R.id.device_id);
        mDeviceNote = (EditText) findViewById(R.id.device_note);
        mAddButton = (Button) findViewById(R.id.add_button);
    }

    private void init() {
        activity = this;
        mLoginPreferences = new LoginPreferences(activity);
        mApi = new Api(activity);
        mApi.setOnCreateDevice(onCreateDevice);
        mAddButton.setOnClickListener(addDevice);
        mQrCodeButton.setOnClickListener(qrcodeScanner);
    }

    private View.OnClickListener qrcodeScanner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissions, ASK_PERMISSIONS_REQUEST);
            } else {
                Intent intent = new Intent(activity, QRCodeScannerActivity.class);
                startActivityForResult(intent, 0);
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ASK_PERMISSIONS_REQUEST) {
            int grantResult = grantResults[0];
            if (grantResult == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(activity, QRCodeScannerActivity.class);
                startActivityForResult(intent, 0);
            } else {
                ToastCreater.makeText(activity, "請至設定開啟權限", Toast.LENGTH_SHORT);
            }
        }
    }

    private View.OnClickListener addDevice = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!mDeviceId.getText().toString().trim().isEmpty() &&
                    !mDeviceNote.getText().toString().trim().isEmpty()) {
                HashMap<String, String> data = new HashMap<>();
                data.put("userID", mLoginPreferences.getUserId());
                data.put("deviceID", mDeviceId.getText().toString());
                data.put("deviceName", mDeviceNote.getText().toString());
                mApi.createDevice(data);
            } else {
                ToastCreater.makeText(activity, "請輸入完整資料", Toast.LENGTH_SHORT);
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            mDeviceId.setText(bundle.getString(INTENT_QR_CONTENT));
        }
    }


    private Api.OnCreateDevice onCreateDevice = new Api.OnCreateDevice() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String message = jsonObject.getString("message");
                if (message.equals("成功")) {
                    ToastCreater.makeText(activity, "裝置註冊成功", Toast.LENGTH_SHORT);
                    finish();
                } else if (message.equals("此裝置已註冊")) {
                    ToastCreater.makeText(activity, "此裝置已註冊", Toast.LENGTH_SHORT);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onError(VolleyError error) {
        }
    };
}