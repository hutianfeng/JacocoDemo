package com.peak.jacocodemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.peak.jacocodemo.utils.JacocoUtils;

public class App extends Application {
    String TAG = "BaseApplication";
    public boolean isBackground = false;//标记是否在后台

    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Log.d(TAG, "onCreate");
    }



    //判断是否是后台
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            isBackground = true;
            notifyBackground();
        }

    }

    private void notifyBackground() {
        // This is where you can notify listeners, handle session tracking, etc
        Log.d(TAG, "切到后台");
        JacocoUtils.generateEcFile(true);
    }


}
