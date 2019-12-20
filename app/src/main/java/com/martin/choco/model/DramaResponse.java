package com.martin.choco.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DramaResponse {

    @SerializedName("data")
    @Expose
    private List<Drama> data = null;

    public List<Drama> getData() {
        return data;
    }

    public void setData(List<Drama> data) {
        this.data = data;
    }

}
