package com.siu.android.twok.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.siu.android.andutils.Application;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 29/06/12
 * Time: 17:43
 * To change this template use File | Settings | File Templates.
 */
public final class NetworkUtils {

    public static boolean isOnline() {
        NetworkInfo networkInfo = ((ConnectivityManager) Application.getContext().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (null == networkInfo || !networkInfo.isConnectedOrConnecting()) {
            Log.d(NetworkUtils.class.getName(), "No network connection");
            return false;
        }

        return true;
    }
}