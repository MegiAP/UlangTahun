package com.ulangtahun.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.ulangtahun.R;
import com.ulangtahun.util.SharedPrefManager;
import com.ulangtahun.util.Util;

public class SubMenuActivity extends AppCompatActivity {
    String pangkat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);


        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        pangkat = bundle.getString("key");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Ulang Tahun Personel " + pangkat);
        setSupportActionBar(toolbar);

    }

    public void onClick(View view) {
        Util util = new Util();
        switch (view.getId()){
            case R.id.cv_kemarin:
                String tgl_kemarin = util.tglKemarin();
                Intent kemarin = new Intent(this, MainActivity.class);
                kemarin.putExtra("key_sub_menu", "Kemarin");
                kemarin.putExtra("key_status", pangkat);
                kemarin.putExtra("key_tgl", tgl_kemarin);
                startActivity(kemarin);
                break;

            case R.id.cv_hari_ini:
                String tgl_hari_ini = util.getTanggal().substring(5,10);
                Intent hari_ini = new Intent(this, MainActivity.class);
                hari_ini.putExtra("key_sub_menu", "Hari Ini");
                hari_ini.putExtra("key_status", pangkat);
                hari_ini.putExtra("key_tgl", tgl_hari_ini);
                startActivity(hari_ini);
                break;

            case R.id.cv_besok:
                String tgl_besok= util.tglBesok();
                Intent besok = new Intent(this, MainActivity.class);
                besok.putExtra("key_sub_menu", "Besok");
                besok.putExtra("key_status", pangkat);
                besok.putExtra("key_tgl", tgl_besok);
                startActivity(besok);
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

//    public void startService(){
//        startService(new Intent(this, MyService.class));
//    }

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
}