package com.example.lesiogotuje;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetStatus {

    private static boolean offlineMode = false;
    private boolean activeInternetConnection;

    public Activity activity;

    public InternetStatus(Activity activity) {
        this.activity = activity;

        ConnectivityManager connectivityManager = (ConnectivityManager)
                activity.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) {
            activeInternetConnection = false;

        } else {
            activeInternetConnection = true;
        }
    }

    public static void setOfflineMode(boolean isOfflineMode) {
        offlineMode = isOfflineMode;
    }
    public static boolean isOfflineMode() {
        return offlineMode;
    }
    public boolean getActiveInternetConnection() {
        return activeInternetConnection;
    }
}
