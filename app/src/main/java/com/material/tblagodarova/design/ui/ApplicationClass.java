package com.material.tblagodarova.design.ui;

import com.crashlytics.android.Crashlytics;
import com.material.tblagodarova.design.BuildConfig;

import android.app.Application;
import android.util.Log;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

/**
 * Created by tblagodarova on 7/10/15.
 */
public class ApplicationClass extends Application  {
    @Override public void onCreate() {
        super.onCreate();
//        Fabric.with(this, new Crashlytics());

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

    /** A tree which logs important information for crash reporting. */
    private static class CrashReportingTree extends Timber.Tree {
        @Override protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

//            FakeCrashLibrary.log(priority, tag, message);
//
//            if (t != null) {
//                if (priority == Log.ERROR) {
//                    FakeCrashLibrary.logError(t);
//                } else if (priority == Log.WARN) {
//                    FakeCrashLibrary.logWarning(t);
//                }
//            }
        }
    }
}