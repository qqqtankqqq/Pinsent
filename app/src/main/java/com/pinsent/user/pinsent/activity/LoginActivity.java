package com.pinsent.user.pinsent.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
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

public class LoginActivity extends AppCompatActivity {
    private Activity activity;
    private LoginPreferences mLoginPreferences;
    private EditText mLoginAccount;
    private EditText mLoginPassword;
    private Button mLoginButton;
    private Button mLoginSignUp;
    private Api mApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();
        init();

    }

    private void init() {
        activity = this;
        mLoginButton.setOnClickListener(login);
        mLoginSignUp.setOnClickListener(singUp);
        mLoginPreferences = new LoginPreferences(activity);
        mApi = new Api(activity);
        mApi.setOnLogin(onLogin);

        if (!mLoginPreferences.getFirst().toString().trim().isEmpty()) {
            ActivityLauncher.go(activity, MenuActivity.class, null);
            finish();
        }
    }

    private void findView() {
        mLoginAccount = (EditText) findViewById(R.id.login_account);
        mLoginPassword = (EditText) findViewById(R.id.login_password);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mLoginSignUp = (Button) findViewById(R.id.login_sign_up);
    }

    private View.OnClickListener login = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!mLoginAccount.getText().toString().trim().isEmpty() &&
                    !mLoginPassword.getText().toString().trim().isEmpty()) {
                HashMap<String, String> data = new HashMap<>();
                data.put("account", mLoginAccount.getText().toString());
                data.put("password", mLoginPassword.getText().toString());
                mApi.login(data);
            } else {
                ToastCreater.makeText(activity, "請輸入完整資料", Toast.LENGTH_SHORT);
            }

        }
    };

    private View.OnClickListener singUp = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ActivityLauncher.go(activity, SignUpActivity.class, null);
        }
    };

    private Api.OnLogin onLogin = new Api.OnLogin() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String message = jsonObject.getString("message");
                if (message.equals("成功")) {
                    mLoginPreferences.saveFirst("true");
                    mLoginPreferences.saveUserId(jsonObject.getString("userID"));
                    ActivityLauncher.go(activity, MenuActivity.class, null);
                    finish();
                } else if (message.equals("查無此用戶")) {
                    ToastCreater.makeText(activity, "查無此用戶", Toast.LENGTH_SHORT);
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
