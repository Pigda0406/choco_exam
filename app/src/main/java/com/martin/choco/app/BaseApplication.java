package com.martin.choco.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;



public class BaseApplication extends Application {

    public static Context appContext;
    public static ArrayList<Activity> allActivities = new ArrayList<Activity>();
    public static BaseApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        app = this;
    }

    public static Context getConText(){
        return appContext;
    }

    public static BaseApplication getApp(){
        return app;
    }

    public static void addActivity(Activity activity) {
        allActivities.add(activity);
    }

    public static void delActivity(Activity activity) {
        allActivities.remove(activity);
    }

}
