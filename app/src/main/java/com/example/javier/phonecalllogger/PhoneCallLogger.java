package com.example.javier.phonecalllogger;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

public class PhoneCallLogger extends PhoneCallReceiver {

    @Override
    protected void onIncomingCallStarted(Context ctx, String number, Date start) {
    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start) {
    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end) {
        Log.v("xyzyx", "incoming call " + number + " " + start.toString() + " - " + end.toString());
        Intent intent = new Intent(ctx, PhoneCallLogAndroidService.class);
        intent.putExtra("type", "entrante");
        intent.putExtra("number", number);
        intent.putExtra("start", start.getTime());
        intent.putExtra("end", end.getTime());
        ctx.startService(intent);
    }

    @Override
    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end) {
        Log.v("xyzyx", "outgoing call " + number + " " + start.toString() + " - " + end.toString());
        Intent intent = new Intent(ctx, PhoneCallLogAndroidService.class);
        intent.putExtra("type", "saliente");
        intent.putExtra("number", number);
        intent.putExtra("start", start.getTime());
        intent.putExtra("end", end.getTime());
        ctx.startService(intent);
    }

    @Override
    protected void onMissedCall(Context ctx, String number, Date start) {
        Log.v("xyzyx", "Llamada perdida " + number + " " + start.toString());
        Intent intent = new Intent(ctx, PhoneCallLogAndroidService.class);
        intent.putExtra("type", "perdida");
        intent.putExtra("number", number);
        intent.putExtra("start", start.getTime());
        intent.putExtra("end", start.getTime());
        ctx.startService(intent);
    }
}
