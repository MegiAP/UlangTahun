package com.ulangtahun.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ulangtahun.R;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

public class Util {

    public String getHari() {
        Date anotherCurDate = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
        return formatter.format(anotherCurDate);
    }

    public String getTanggal(){
        Date anotherCurDate = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(anotherCurDate);
    }

    public String getTanggal2(){
        Date anotherCurDate = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(anotherCurDate);
    }

    public String getTanggal1(){
        Date anotherCurDate = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(anotherCurDate);
    }

    public static String getWaktu() {
        DateFormat dateFormat = new SimpleDateFormat("HHmmss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String waktu(){
        Date anotherCurDate = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(anotherCurDate);

    }

    public String getTahun() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat waktusekarang = new SimpleDateFormat("dd-MM-yyyy");
        String tanggal = waktusekarang.format(new Date());
        String[] tanggals = tanggal.split("-");
        int bulan = Integer.parseInt(tanggals[1]);
        int tahun = Integer.parseInt(tanggals[2]);
        String hasilTahun = "";

        if (bulan >= 1 && bulan <= 6) {
            int tahunsekarang = tahun - 1;
            hasilTahun = tahunsekarang +"/"+ tahun;
        } else if (bulan >= 7) {
            int tahunselanjutnya = tahun + 1;
            hasilTahun = tahun +"/"+ tahunselanjutnya;
        } else {
            Log.d("error", "Error");
        }
        return hasilTahun;
    }

    public String getSemester() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat waktusekarang = new SimpleDateFormat("dd-MM-yyyy");
        String tanggal = waktusekarang.format(new Date());
        String[] tanggals = tanggal.split("-");
        int bulan = Integer.parseInt(tanggals[1]);
        String semester;

        if (bulan >= 2 && bulan <= 7) {
            semester = "Genap";
        } else {
            semester = "Ganjil";
        }
        return semester;

    }

    public static boolean containsIgnoreCase(String src, String charString) {
        final int length = charString.length();
        if (length == 0) {
            return true;
        }
        for (int i = src.length() - length; i >= 0; i--) {
            if (src.regionMatches(true, i, charString, 0, length)) {
                return true;
            }
        }
        return false;
    }

    public static void setImage(Context context, ImageView image, String uri) {
        Glide.with(context)
                .load(uri)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.person)
                        .error(R.drawable.person))
                .into(image);
    }

    public String convertWaktu(String tanggal) {
        String[] tanggals = tanggal.split("-");

        if (tanggals.length == 3) {
            String tahun = tanggals[0];
            String bulan = tanggals[1];
            String hari = tanggals[2];

            switch (bulan) {
                case "01":
                    bulan = "Januari";
                    break;
                case "02":
                    bulan = "Februari";
                    break;
                case "03":
                    bulan = "Maret";
                    break;
                case "04":
                    bulan = "April";
                    break;
                case "05":
                    bulan = "Mei";
                    break;
                case "06":
                    bulan = "Juni";
                    break;
                case "07":
                    bulan = "Juli";
                    break;
                case "08":
                    bulan = "Agustus";
                    break;
                case "09":
                    bulan = "September";
                    break;
                case "10":
                    bulan = "Oktober";
                    break;
                case "11":
                    bulan = "November";
                    break;
                case "12":
                    bulan = "Desember";
                    break;
            }
            return hari + " " + bulan + " " + tahun;
        }
        return tanggal;
    }

    public String base64(Bitmap validasi){
        String ttd = "";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        validasi.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte [] a = baos.toByteArray();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            ttd = Base64.getEncoder().encodeToString(a);
        }
        return ttd;
    }

    public String tglKemarin(){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return simpleDateFormat.format(calendar.getTime());


    }

    public String tglBesok(){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, +1);
        return simpleDateFormat.format(calendar.getTime());


    }

}
