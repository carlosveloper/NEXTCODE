package com.scorpion.nextcode.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatosFactura {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("serie")
    @Expose
    private String serie;
    @SerializedName("secuencial")
    @Expose
    private Integer secuencial;
    @SerializedName("fecha_emision")
    @Expose
    private String fechaEmision;
    @SerializedName("subtotal")
    @Expose
    private String subtotal;
    @SerializedName("iva")
    @Expose
    private String iva;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("estado_pago")
    @Expose
    private String estadoPago;
    @SerializedName("usuario_id")
    @Expose
    private Integer usuarioId;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Integer getSecuencial() {
        return secuencial;
    }

    public void setSecuencial(Integer secuencial) {
        this.secuencial = secuencial;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
