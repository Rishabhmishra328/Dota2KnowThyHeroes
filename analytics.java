package com.echo.primestudio.dota2knowthyheroes;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import java.util.HashMap;

/**
 * Created by Rishabh Mishra on 2/21/2016.
 */
public class analytics extends Application {

    private Tracker mTracker;

    /**
     * Gets the default {@link Tracker} for this {@link}.
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.analytics_values);
        }
        return mTracker;
    }

}
