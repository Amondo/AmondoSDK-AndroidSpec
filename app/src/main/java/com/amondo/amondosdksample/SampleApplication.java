package com.amondo.amondosdksample;

import android.app.Application;

import com.amondo.sdk.AmondoSDK;

/**
 * Created by Milos Stankovic.
 */
public class SampleApplication extends Application{

    private static final String YOUR_APP_ID = "APP_ID";
    private static final String YOUR_SECRET_KEY = "SECRET_KEY";

    @Override
    public void onCreate() {
        super.onCreate();
        AmondoSDK.init(this, YOUR_APP_ID, YOUR_SECRET_KEY);
    }
}
