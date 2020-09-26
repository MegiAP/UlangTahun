package com.ulangtahun.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model {
    @SerializedName("text1")
    @Expose
    private String text1;

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public Model(String text1) {
        this.text1 = text1;
    }
}
