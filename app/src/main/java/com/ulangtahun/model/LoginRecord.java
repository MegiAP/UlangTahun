package com.ulangtahun.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRecord implements Parcelable {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("tgl_lahir")
    @Expose
    private String tglLahir;
    @SerializedName("token")
    @Expose
    private String token;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginRecord(String email, String nama, String tglLahir, String token) {
        this.email = email;
        this.nama = nama;
        this.tglLahir = tglLahir;
        this.token = token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.email);
        dest.writeString(this.nama);
        dest.writeString(this.tglLahir);
        dest.writeString(this.token);
    }

    protected LoginRecord(Parcel in) {
        this.email = in.readString();
        this.nama = in.readString();
        this.tglLahir = in.readString();
        this.token = in.readString();
    }

    public static final Creator<LoginRecord> CREATOR = new Creator<LoginRecord>() {
        @Override
        public LoginRecord createFromParcel(Parcel source) {
            return new LoginRecord(source);
        }

        @Override
        public LoginRecord[] newArray(int size) {
            return new LoginRecord[size];
        }
    };
}
