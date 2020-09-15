package com.scorpion.nextcode.Model.Response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseLogin {

    @SerializedName("data")
    @Expose
    private List<DatosLogin> data = new ArrayList<>();

    public List<DatosLogin> getData() {
        return data;
    }

    public void setData(List<DatosLogin> data) {
        this.data = data;
    }
}
