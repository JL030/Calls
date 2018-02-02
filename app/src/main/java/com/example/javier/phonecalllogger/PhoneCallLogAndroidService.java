package com.example.javier.phonecalllogger;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Date;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhoneCallLogAndroidService extends Service {
    public PhoneCallLogAndroidService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String type = intent.getStringExtra("type");
        String number = intent.getStringExtra("number");
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+1"));
        Date start = new Date();
        start.setTime(intent.getLongExtra("start", 0));
        Date end = new Date();
        end.setTime(intent.getLongExtra("end", 0));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PhoneCallLogOnlineService onlineService = retrofit.create(PhoneCallLogOnlineService.class);
        Call<Void> call = onlineService.add(new PhoneCall(type, number, start, end));

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.v("xyzyx", "phone call added to log");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.v("xyzyx", "failed to add phone call to log");
            }
        });

        return START_REDELIVER_INTENT;
    }
}
