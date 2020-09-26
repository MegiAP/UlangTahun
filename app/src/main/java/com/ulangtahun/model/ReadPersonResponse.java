package com.ulangtahun.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReadPersonResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("records")
    @Expose
    private List<ReadPersonRecord> readPersonRecordList = null;

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

    public List<ReadPersonRecord> getReadPersonRecordList() {
        return readPersonRecordList;
    }

    public void setRecords(List<ReadPersonRecord> readPersonRecordList) {
        this.readPersonRecordList = readPersonRecordList;
    }

}
