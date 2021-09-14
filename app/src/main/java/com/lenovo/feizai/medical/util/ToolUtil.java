package com.lenovo.feizai.medical.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.UUID;

/**
 * @author feizai
 * @date 2021/6/13 0013 PM 4:48:12
 */
public class ToolUtil {
    public static String getUserName(Activity activity) {
        SharedPreferences userdata = activity.getSharedPreferences("userdata", Context.MODE_PRIVATE);
        return userdata.getString("user", "");
    }

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        String replace = uuid.replace("-", "");
        return replace;
    }
}
