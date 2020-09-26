package com.ulangtahun.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ulangtahun.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
//        vector = findViewById(R.id.img);

        int timeLoad = 3000;
        new Handler().postDelayed(() -> {
            Intent Dashboard = new Intent(this, LoginActivity.class);
            startActivity(Dashboard);
            finish();
        }, timeLoad);
    }

//    private void getSplash() {
//        ApiService apiInterface = RetrofitClient.getClient().create(ApiService.class);
//
//        Call<SplashModel> call = apiInterface.getSplash("1");
//        call.enqueue(new Callback<SplashModel>() {
//            @Override
//            public void onResponse(@androidx.annotation.NonNull Call<SplashModel> call, @androidx.annotation.NonNull Response<SplashModel> response) {
//                if (response.body() != null) {
//                    List<SplashRecord> result = response.body().getRecords();
//
//                    String path = "https://fmipa.unila.ac.id/" + result.get(0).getVector();
//                    Log.d("Image", path);
//
//                    Glide.with(getApplicationContext())
//                            .load(path)
//                            .into(vector);
//                }
//            }
//
//            @Override
//            public void onFailure(@NotNull Call<SplashModel> call, @NotNull Throwable t) {
//
//            }
//        });
//    }
}