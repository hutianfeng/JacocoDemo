package com.peak.jacocodemo.test;

import android.util.Log;

import com.peak.jacocodemo.MainActivity;

/**
 * @Desc
 * @Author hanson.hu
 * @Date 2022/8/29 17:39
*/
public class InstrumentedActivity extends MainActivity {
    public static String TAG = "InstrumentedActivity";

    private FinishListener mListener;

    public void setFinishListener(FinishListener listener) {
        mListener = listener;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
        super.finish();
        if (mListener != null) {
            mListener.onActivityFinished();
        }
    }


}
