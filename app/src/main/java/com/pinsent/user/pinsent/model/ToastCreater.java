package com.pinsent.user.pinsent.model;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 5*N on 2017/2/14.
 */

public class ToastCreater {
    private static Toast toast;

    public static void makeText(final Context context,
                                final String text,
                                final int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, text, duration);
        } else {
            toast.setText(text);
            toast.setDuration(duration);
        }
        toast.show();
    }

    public static void makeText(final Context context,
                                final int resId,
                                final int duration) {
        String text = context.getResources().getString(resId);

        if (toast == null) {
            toast = Toast.makeText(context, text, duration);
        } else {
            toast.setText(text);
            toast.setDuration(duration);
        }
        toast.show();
    }
}
