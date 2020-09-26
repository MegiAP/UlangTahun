package com.ulangtahun.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ulangtahun.R;
import com.ulangtahun.util.SharedPrefManager;

public class Template extends AppCompatActivity implements View.OnClickListener {
    EditText edt_template;
    Button btn_simpan;
    Button btntemplate1, btntemplate2, btntemplate3, btntemplate4; 
    SharedPreferences sharedPreferences;
    private static final String KEY_1 = "key_1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Template Kartu Ucapan");
        setSupportActionBar(toolbar);
        btn_simpan = findViewById(R.id.btn_simpan);
        edt_template= findViewById(R.id.edt_ucapan);
        btntemplate1 = findViewById(R.id.btn_1);
        btntemplate2= findViewById(R.id.btn_2);
        btntemplate3 = findViewById(R.id.btn_3);
        btntemplate4= findViewById(R.id.btn_4);

        String PREF_NAME_LOGIN = "pref_name_login";
        sharedPreferences = getSharedPreferences(PREF_NAME_LOGIN, Context.MODE_PRIVATE);


        
        btn_simpan.setOnClickListener(view -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_1, edt_template.getText().toString());
            editor.apply();

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.template:
                Intent intent = new Intent(this, Template.class);
                startActivity(intent);
                break;

            case R.id.logout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;

        }
        return true;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_1:
                edt_template.setText(R.string.template1);
                break;
            case R.id.btn_2:
                edt_template.setText(R.string.template2);
                break;
            case R.id.btn_3:
                edt_template.setText(R.string.template3);
                break;
            case R.id.btn_4:
                edt_template.setText(R.string.template4);
                break;
        }
    }
}