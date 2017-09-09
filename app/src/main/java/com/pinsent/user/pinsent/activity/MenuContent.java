package com.pinsent.user.pinsent.activity;

import android.view.View;

/**
 * Created by cheng on 2017/9/4.
 */

public interface MenuContent {
    void onChildClick(View v);
    void onChildLongClick(View v);
    void onGroupDialogCallBack();
}
