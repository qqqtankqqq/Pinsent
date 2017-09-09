package com.pinsent.user.pinsent.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Created by hong on 2017/8/31.
 */

public class FeedBackActivity extends AppCompatActivity {
    private Activity activity;
    private LoginPreferences mLoginPreferences;
    private Api mApi;
    private EditText mProblemFeedbackEditText;
    private EditText mOptionFeedbackEditText;
    private Button mSentButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        findView();
        init();

    }

    private void findView() {
        mProblemFeedbackEditText = (EditText) findViewById(R.id.problem_feedback_edit_text);
        mOptionFeedbackEditText = (EditText) findViewById(R.id.option_feedback_edit_text);
        mSentButton = (Button) findViewById(R.id.sent_button);
    }


    private void init() {
        activity = this;
        mSentButton.setOnClickListener(sent);
        mLoginPreferences = new LoginPreferences(activity);
        mApi = new Api(activity);
        mApi.setOnFeedBack(onFeedBack);
    }

    private View.OnClickListener sent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!mProblemFeedbackEditText.getText().toString().trim().isEmpty() ||
                    !mOptionFeedbackEditText.getText().toString().trim().isEmpty()) {
                HashMap<String, String> data = new HashMap<>();
                data.put("userID", mLoginPreferences.getUserId());
                data.put("errMsg", mProblemFeedbackEditText.getText().toString());
                data.put("customerMsg", mOptionFeedbackEditText.getText().toString());
                mApi.feedback(data);
            } else {
                ToastCreater.makeText(activity, "至少輸入一個欄位", Toast.LENGTH_SHORT);
            }
        }
    };

    private Api.OnFeedBack onFeedBack = new Api.OnFeedBack() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String message = jsonObject.getString("message");
                if (message.equals("成功")) {
                    ToastCreater.makeText(activity, "您的意見，是我們進步的動力", Toast.LENGTH_SHORT);
                    finish();
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
