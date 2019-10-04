package com.muchine.chapter2_2.basic.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.muchine.chapter2_2.MainActivity;

/**
 * Created by sejoonlim on 8/6/16.
 */
public class MySMSBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MySMSBroadcastReceiver", "start onReceive...");
        if (!intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) return;

        Log.d("MySMSBroadcastReceiver", "start activity...");
        Intent myIntent= new Intent(context, MainActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        context.startActivity(myIntent);
    }

}
