package com.ulangtahun.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.ulangtahun.R;
import com.ulangtahun.model.ReadPersonRecord;
import com.ulangtahun.util.SharedPrefManager;
import com.ulangtahun.util.Util;

import java.util.Objects;

public class UcapanActivity extends AppCompatActivity{
    public static String EXTRA_INTENT = "extra_intent";
    TextView tv_nama, tv_umur, tv_line1;
    Button btn_sms, btn_wa;
    ImageView img_person;
    EditText edt_ucapan;
    SignaturePad signaturePad;
    SharedPreferences sharedPreferences;
    private static final String KEY_1 = "key_1";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ucapan);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Kartu Ucapan");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tv_nama = findViewById(R.id.tv_nama);
        tv_umur = findViewById(R.id.tv_umur);
        btn_sms = findViewById(R.id.btn_sms);
        btn_wa = findViewById(R.id.btn_wa);
        edt_ucapan = findViewById(R.id.edt_ucapan);
        img_person = findViewById(R.id.img_person);
        tv_line1 = findViewById(R.id.tv_line1);


        ReadPersonRecord readPersonRecord = getIntent().getParcelableExtra(EXTRA_INTENT);
        assert readPersonRecord != null;
        String nama = readPersonRecord.getNama();

        tv_nama.setText(readPersonRecord.getNama());

        String PREF_NAME_LOGIN = "pref_name_login";
        sharedPreferences = getSharedPreferences(PREF_NAME_LOGIN, Context.MODE_PRIVATE);

        String text = sharedPreferences.getString(KEY_1, null);
        edt_ucapan.setText(text);
        String tgl = readPersonRecord.getTglLahir();
        int tgl_lahir = Integer.parseInt(tgl.substring(8,10));
        int bln_lahir = Integer.parseInt(tgl.substring(5,7));
        int thn_lahir = Integer.parseInt(tgl.substring(0,4));


        Util util = new Util();
        String tgl_hari_ini = util.getTanggal();
        int tgl_ini = Integer.parseInt(tgl_hari_ini.substring(8,10));
        int bln_ini = Integer.parseInt(tgl_hari_ini.substring(5,7));
        int thn_ini = Integer.parseInt(tgl_hari_ini.substring(0,4));

        String umur = String.valueOf(thn_ini - thn_lahir);
        tv_umur.setText(umur + " Tahun");


        btn_sms.setOnClickListener(view -> {
            String no = readPersonRecord.getNoHp();
            openSms("+62" + no , edt_ucapan.getText().toString(), nama.trim(), tv_line1.getText().toString(),  tv_line1.getText().toString(), SharedPrefManager.getUser().getNama());
        });

        btn_wa.setOnClickListener(view -> {

            String no = readPersonRecord.getNoHp();
            openWa("+62" + no , edt_ucapan.getText().toString(), nama.trim(), SharedPrefManager.getUser().getNama());
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
            case android.R.id.home:
                this.finish();
                break;
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

//    @Override
//    public void onBackPressed() {
//        Intent a = new Intent(this, MainActivity.class);
//        startActivity(a);
//    }

    private void openWa(String no_hp, String ucapan, String nama_ke, String nama_dari) {
        String url = "https://api.whatsapp.com/send?phone=" + no_hp + "&text=" + ucapan + " *" + nama_ke + "*" + "%0a%0aTertanda," + "%0a%0a" + "*" + nama_dari + "*";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void openSms(String no_hp, String ucapan,String nama_ke, String line1, String line2, String nama_dari){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + no_hp));
        intent.putExtra("sms_body", ucapan + " " + nama_ke + line1 + "Tertanda," + line2 +  nama_dari);
        startActivity(intent);
    }

//    @Override
//    public void finish() {
//        finish();
//    }
}