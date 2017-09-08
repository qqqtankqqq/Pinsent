package com.pinsent.user.pinsent.model;

import android.content.Context;

import com.pinsent.user.pinsent.core.PreferencesHelper;

/**
 * Created by Lucas on 2017/5/8.
 */

public class LoginPreferences extends PreferencesHelper {
    private final String SP_FiLE_NAME = LoginPreferences.class.getName();
    private final String SP_FIRST = "SP_FIRST";
    private final String SP_USER_ID = "SP_USER_ID";


    public LoginPreferences(Context context) {
        super(context);
    }

    public void saveFirst(String first) {
        save(Type.STRING, SP_FIRST, first);
    }

    public void saveUserId(String userId) {
        save(Type.STRING, SP_USER_ID, userId);
    }

    public String getUserId() {
        return (String) get(Type.STRING, SP_USER_ID);
    }

    public String getFirst() {
        return (String) get(Type.STRING, SP_FIRST);
    }

    @Override
    public String getClassName() {
        return SP_FiLE_NAME;
    }
}
