package com.projects.deus_ex_machina.clustereducation;

import android.app.Application;
import android.content.res.Configuration;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Nikita Kiselov on 03-Dec-17.
 * ClusterEducation
 */

public class CustomApplication extends Application {
    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    //Using it to config cashing for Firebase
    @Override
    public void onCreate() {
        super.onCreate();
        //Initialize cashing
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
