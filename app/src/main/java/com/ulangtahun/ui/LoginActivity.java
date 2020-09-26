package com.ulangtahun.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ulangtahun.R;
import com.ulangtahun.util.SharedPrefManager;
import com.ulangtahun.model.LoginResponse;
import com.ulangtahun.network.ApiService;
import com.ulangtahun.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btn_login;
    EditText  edt_nama, edt_sandi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.btn_login);
        edt_nama = findViewById(R.id.edt_nama_pengguna);
        edt_sandi = findViewById(R.id.edt_sandi);


        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MenuActivity.class));
        }


        btn_login.setOnClickListener(view -> {
            String username = edt_nama.getText().toString().trim();
            String password = edt_sandi.getText().toString().trim();


            if (TextUtils.isEmpty(username)){
                edt_nama.setError("Email harus diisi");
            }else if (TextUtils.isEmpty(password)){
                edt_sandi.setError("Sandi harus diisi");
            }else {
                userLogin(username, password);
            }
        });
    }

    private void userLogin(String nama, String sandi){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        ApiService apiInterface = RetrofitClient.getClient().create(ApiService.class);

        Call<LoginResponse> call = apiInterface.loginRequest(nama, sandi);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@androidx.annotation.NonNull Call<LoginResponse> call, @androidx.annotation.NonNull Response<LoginResponse> response) {
                progressDialog.dismiss();
                if (response.body() != null){
                    if(response.body().getLoginRecordList() != null){
                        finish();
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(response.body().getLoginRecordList().get(0));
                        startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Email atau Sandi salah", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Email atau Sandi salah", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(@androidx.annotation.NonNull Call<LoginResponse> call, @androidx.annotation.NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}