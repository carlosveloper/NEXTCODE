package com.scorpion.nextcode.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatosPlanUsuario {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("usuario_id")
    @Expose
    private Integer usuarioId;
    @SerializedName("plan_id")
    @Expose
    private Integer planId;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
