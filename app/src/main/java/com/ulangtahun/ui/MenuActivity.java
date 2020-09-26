package com.ulangtahun.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.ulangtahun.R;
import com.ulangtahun.service.MyService;
import com.ulangtahun.util.SharedPrefManager;

public class MenuActivity extends AppCompatActivity {
    CardView cv_pamen, cv_pama, cv_brigadir, cv_pns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Polda Lampung");
        setSupportActionBar(toolbar);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cv_pamen:
                Intent pamen = new Intent(this, SubMenuActivity.class);
                pamen.putExtra("key", "Pamen");
                startActivity(pamen);
                break;

            case R.id.cv_pama:
                Intent pama = new Intent(this, SubMenuActivity.class);
                pama.putExtra("key", "Pama");
                startActivity(pama);
                break;

            case R.id.cv_bintara:
                Intent bintara = new Intent(this, SubMenuActivity.class);
                bintara.putExtra("key", "Bintara");
                startActivity(bintara);
                break;

            case R.id.cv_pns:
                Intent pns = new Intent(this, SubMenuActivity.class);
                pns.putExtra("key", "PNS");
                startActivity(pns);
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