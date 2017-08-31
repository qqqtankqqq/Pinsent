package com.pinsent.user.pinsent.temporarydevelop;

import android.os.Bundle;

import com.pinsent.user.pinsent.activity.AddBottleActivity;
import com.pinsent.user.pinsent.activity.BottleListActivity;
import com.pinsent.user.pinsent.activity.LoginActivity;
import com.pinsent.user.pinsent.activity.SignUpActivity;
import com.pinsent.user.pinsent.core.SeparateDeveloperActivity;

/**
 * Created by Ameng on 2015/12/14.
 */

public class TemporaryDevelopActivity extends SeparateDeveloperActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Activity
        addActivityButton(LoginActivity.class, null);
        addActivityButton(SignUpActivity.class, null);
        addActivityButton(AddBottleActivity.class, null);
        addActivityButton(BottleListActivity.class, null);
    }
}
