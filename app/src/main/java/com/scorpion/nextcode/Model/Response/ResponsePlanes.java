package com.scorpion.nextcode.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponsePlanes {
    @SerializedName("data")
    @Expose
    private List<DatosPlanes> data = new ArrayList<>();

    public List<DatosPlanes> getData() {
        return data;
    }

    public void setData(List<DatosPlanes> data) {
        this.data = data;
    }

}
