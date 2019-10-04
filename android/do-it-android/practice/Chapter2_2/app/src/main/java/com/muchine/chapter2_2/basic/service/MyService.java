package com.muchine.chapter2_2.basic.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by sejoonlim on 8/6/16.
 */
public class MyService extends Service implements Runnable {

    public static final String TAG = "MyService";
    private boolean running = false;

    @Override
    public void onCreate() {
        super.onCreate();

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void onDestroy() {
        running = false;
        super.onDestroy();
    }

    @Override
    public void run() {
        running = true;
        int count = 0;

        while(running) {
            try {
                Log.i(TAG, "my service called #" + count++);
                Thread.sleep(5000);
            } catch(Exception e) {
                Log.e(TAG, e.toString());
            }
        }

        Log.i("MyService", "stop the thread...");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
