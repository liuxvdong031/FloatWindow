package com.yhao.floatwindow;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by yhao on 2017/12/30.
 * https://github.com/yhaolpz
 */

class Rom {
    public static final String ROM_OPPO = "OPPO";
    private static final String KEY_VERSION_VIVO = "ro.vivo.os.version";

    static boolean isOppo() {
        LogUtil.d(" oppo  : " + getProp(KEY_VERSION_VIVO));
        return Build.MANUFACTURER.equalsIgnoreCase(ROM_OPPO);
    }

    static boolean isIntentAvailable(Intent intent, Context context) {
        return intent != null && context.getPackageManager().queryIntentActivities(
                intent, PackageManager.MATCH_DEFAULT_ONLY).size() > 0;
    }


    static String getProp(String name) {
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + name);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            String line = input.readLine();
            input.close();
            return line;
        } catch (IOException ex) {
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
