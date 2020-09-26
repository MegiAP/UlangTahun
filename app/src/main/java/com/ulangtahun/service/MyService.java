package com.ulangtahun.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ulangtahun.adapter.DataAdapter;
import com.ulangtahun.model.ReadPersonResponse;
import com.ulangtahun.network.ApiService;
import com.ulangtahun.network.RetrofitClient;
import com.ulangtahun.receiver.AlarmReceiver;
import com.ulangtahun.ui.MainActivity;
import com.ulangtahun.util.Util;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ulangtahun.util.Util.getWaktu;

public class MyService  extends Service {
    AlarmReceiver alarmReceiver;
    private ApiService apiInterface = RetrofitClient.getClient().create(ApiService.class);
    private Object Service;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        Toast.makeText(this, "Halo", Toast.LENGTH_SHORT).show();
        alarmReceiver = new AlarmReceiver();
        Util util = new Util();
        String onceDate = util.getTanggal();
        String onceTime = "09:34";
        String onceMessage = "Test";
        int onceID = Integer.parseInt("20200926"); //karena positionnya integer, jadi yg di alarm manager tadi ganti int aja, biar gk usah di parsing" lgi
        String waktu = getWaktu();
        String setWaktu = "21:03";

        if(waktu.equals("09:34")){
            Toast.makeText(this, waktu + " " + setWaktu, Toast.LENGTH_SHORT).show();
            alarmReceiver.setOneTimeAlarm(getApplicationContext(), AlarmReceiver.TYPE_ONE_TIME,
                    onceDate,
                    onceTime,
                    onceMessage,
                    onceID);

        }
////            alarmReceiver.setOneTimeAlarm(getApplicationContext(), AlarmReceiver.TYPE_ONE_TIME,
//                    onceDate,
//                    onceTime,
//                    onceMessage,
//                    onceID);
//        }

    }

    //    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
////        String waktu = getWaktu();
////        String setWaktu = "20:18";
////        Toast.makeText(this, waktu + "Mulaiiiiii", Toast.LENGTH_LONG).show();
////
////        alarmReceiver = new AlarmReceiver();
////        Util util = new Util();
////        String onceDate = util.getTanggal();
////        String onceTime = "20:05";
////        String onceMessage = "Test";
////        int onceID = Integer.parseInt("20200925"); //karena positionnya integer, jadi yg di alarm manager tadi ganti int aja, biar gk usah di parsing" lgi
////        alarmReceiver.setOneTimeAlarm(getApplicationContext(), AlarmReceiver.TYPE_ONE_TIME,
////                onceDate,
////                onceTime,
////                onceMessage,
////                onceID);
//////
////        startForeground();
//        return START_NOT_STICKY;
//    }

    private String getWaktu(){
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
