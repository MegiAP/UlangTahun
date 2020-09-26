package com.ulangtahun.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReadPersonRecord implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("tgl_lahir")
    @Expose
    private String tglLahir;
    @SerializedName("tahun")
    @Expose
    private String tahun;
    @SerializedName("no_hp")
    @Expose
    private String noHp;
    @SerializedName("statusr")
    @Expose
    private String statusr;
    @SerializedName("jabatan")
    @Expose
    private String jabatan;
    @SerializedName("pangkat")
    @Expose
    private String pangkat;
    @SerializedName("nrp")
    @Expose
    private String nrp;

    public String getNrp() {
        return nrp;
    }

    public void setNrp(String nrp) {
        this.nrp = nrp;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getPangkat() {
        return pangkat;
    }

    public void setPangkat(String pangkat) {
        this.pangkat = pangkat;
    }

    public String getStatusr() {
        return statusr;
    }

    public void setStatusr(String statusr) {
        this.statusr = statusr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public ReadPersonRecord() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.nama);
        dest.writeString(this.tglLahir);
        dest.writeString(this.tahun);
        dest.writeString(this.noHp);
        dest.writeString(this.statusr);
        dest.writeString(this.jabatan);
        dest.writeString(this.pangkat);
        dest.writeString(this.nrp);
    }

    protected ReadPersonRecord(Parcel in) {
        this.id = in.readString();
        this.nama = in.readString();
        this.tglLahir = in.readString();
        this.tahun = in.readString();
        this.noHp = in.readString();
        this.statusr = in.readString();
        this.jabatan = in.readString();
        this.pangkat = in.readString();
        this.nrp = in.readString();
    }

    public static final Creator<ReadPersonRecord> CREATOR = new Creator<ReadPersonRecord>() {
        @Override
        public ReadPersonRecord createFromParcel(Parcel source) {
            return new ReadPersonRecord(source);
        }

        @Override
        public ReadPersonRecord[] newArray(int size) {
            return new ReadPersonRecord[size];
        }
    };
}
