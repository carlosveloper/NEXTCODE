package com.scorpion.nextcode.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseFactura {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cedula")
    @Expose
    private String cedula;
    @SerializedName("contrasenia")
    @Expose
    private String contrasenia;
    @SerializedName("nombres")
    @Expose
    private String nombres;
    @SerializedName("apellidos")
    @Expose
    private String apellidos;
    @SerializedName("correo")
    @Expose
    private String correo;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("facturas")
    @Expose
    private List<DatosFactura> facturas = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DatosFactura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<DatosFactura> facturas) {
        this.facturas = facturas;
    }

}
