package com.ulangtahun.model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("records")
    @Expose
    private List<LoginRecord> loginRecordList = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public List<LoginRecord> getLoginRecordList() {
        return loginRecordList;
    }

    public void setRecords(List<LoginRecord> loginRecordList) {
        this.loginRecordList = loginRecordList;
    }

}