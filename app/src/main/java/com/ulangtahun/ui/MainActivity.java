package com.ulangtahun.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ulangtahun.R;
import com.ulangtahun.adapter.DataAdapter;
import com.ulangtahun.model.ReadPersonResponse;
import com.ulangtahun.network.ApiService;
import com.ulangtahun.network.RetrofitClient;
import com.ulangtahun.receiver.AlarmReceiver;
import com.ulangtahun.service.MyService;
import com.ulangtahun.util.SharedPrefManager;
import com.ulangtahun.util.Util;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerview;
    TextView tv_nama, tv_umur, tv_kosong;
    ImageView img_person, img_kosong;
    ProgressBar progressBar;
    DataAdapter dataAdapter;
    RecyclerView recyclerView;
    private ApiService apiInterface = RetrofitClient.getClient().create(ApiService.class);
    AlarmReceiver alarmReceiver;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String pangkat = bundle.getString("key_status");
        String tgl = bundle.getString("key_tgl");
        String toolbar_title = bundle.getString("key_sub_menu");
        assert pangkat != null;
        String pangkat_post = pangkat.toLowerCase();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(pangkat+ " Yang Berulang Tahun " + toolbar_title);
        setSupportActionBar(toolbar);

        tv_kosong = findViewById(R.id.tv_data_kosong);
        img_kosong = findViewById(R.id.img_kosong);
        progressBar = findViewById(R.id.pb);
        recyclerView = findViewById(R.id.rv_list_data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        getData(tgl, pangkat_post);
        startService();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String pangkat = bundle.getString("key_status");
        assert pangkat != null;
        String pangkat_post = pangkat.toLowerCase();
        String tgl = bundle.getString("key_tgl");

        getData(tgl, pangkat_post);
        startService();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void startService(){
        startService(new Intent(this, MyService.class));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.template:
                Intent intent = new Intent(this, Template.class);
                startActivity(intent);
//                startService(MyService.class);
                break;

            case R.id.logout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;

        }
        return true;

    }

    public void getData(String tgl_post, String status){
        Call<ReadPersonResponse> readPersonResponseCall = apiInterface.listPerson(tgl_post, status);
        readPersonResponseCall.enqueue(new Callback<ReadPersonResponse>() {
            @Override
            public void onResponse(@NotNull Call<ReadPersonResponse> call, @NotNull Response<ReadPersonResponse> response) {
                assert response.body() != null;
                if (response.body().getReadPersonRecordList() != null) {
                    progressBar.setVisibility(View.GONE);
                    dataAdapter = new DataAdapter(MainActivity.this, response.body().getReadPersonRecordList());
                    dataAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(dataAdapter);
                    alarmReceiver = new AlarmReceiver();

//                    Util util = new Util();
//                    String onceDate = util.getTanggal();
//                    String onceTime = "20:05:00";
//                    String onceMessage = "Test";
//                    int onceID = Integer.parseInt("20200924"); //karena positionnya integer, jadi yg di alarm manager tadi ganti int aja, biar gk usah di parsing" lgi
////
//                    alarmReceiver.setOneTimeAlarm(MainActivity.this, AlarmReceiver.TYPE_ONE_TIME,
//                            onceDate,
//                            onceTime,
//                            onceMessage,
//                            onceID);
                }

                else {
                    progressBar.setVisibility(View.GONE);
                    img_kosong.setVisibility(View.VISIBLE);
                    tv_kosong.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ReadPersonResponse> call, @NotNull Throwable t) {

            }
        });
    }


}