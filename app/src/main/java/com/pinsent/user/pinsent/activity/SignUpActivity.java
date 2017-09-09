package com.pinsent.user.pinsent.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.pinsent.user.pinsent.R;
import com.pinsent.user.pinsent.model.LoginPreferences;
import com.pinsent.user.pinsent.model.ToastCreater;
import com.pinsent.user.pinsent.model.network.Api;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.R.attr.data;
import static android.R.attr.password;

/**
 * Created by hong on 2017/8/25.
 */

public class SignUpActivity extends AppCompatActivity {
    private Activity activity;
    private Api mApi;
    private EditText mSignUpName;
    private EditText mSignUpAccount;
    private EditText mSignUpPassword;
    private Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        findView();
        init();
    }

    private void init() {
        activity = this;
        mSignUpButton.setOnClickListener(register);
        mApi = new Api(activity);
        mApi.setOnRegister(onRegister);

    }

    private void findView() {
        mSignUpName = (EditText) findViewById(R.id.sign_up_name);
        mSignUpAccount = (EditText) findViewById(R.id.sign_up_account);
        mSignUpPassword = (EditText) findViewById(R.id.sign_up_password);
        mSignUpButton = (Button) findViewById(R.id.sign_up_button);
        mSignUpPassword.setTransformationMethod(new PasswordTransformationMethod());
    }

    private View.OnClickListener register = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!mSignUpName.getText().toString().trim().isEmpty() &&
                    !mSignUpAccount.getText().toString().trim().isEmpty() &&
                    !mSignUpPassword.getText().toString().trim().isEmpty()) {
                if (isEmailValid(mSignUpAccount.getText().toString())) {
                    HashMap<String, String> data = new HashMap<>();
                    data.put("userName", mSignUpName.getText().toString());
                    data.put("account", mSignUpAccount.getText().toString());
                    data.put("password", mSignUpPassword.getText().toString());
                    mApi.register(data);
                } else {
                    ToastCreater.makeText(activity, "請輸入Email格式", Toast.LENGTH_SHORT);
                }

            } else {
                ToastCreater.makeText(activity, "請輸入完整資料", Toast.LENGTH_SHORT);
            }

        }
    };

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    private Api.OnRegister onRegister = new Api.OnRegister() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String message = jsonObject.getString("message");
                if (message.equals("成功")) {
                    ToastCreater.makeText(activity, "註冊成功", Toast.LENGTH_SHORT);
                    finish();
                } else if (message.equals("此信箱已註冊")) {
                    ToastCreater.makeText(activity, "此信箱已註冊", Toast.LENGTH_SHORT);
                } else if (message.equals("輸入格式錯誤")) {
                    ToastCreater.makeText(activity, "輸入格式錯誤", Toast.LENGTH_SHORT);
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
