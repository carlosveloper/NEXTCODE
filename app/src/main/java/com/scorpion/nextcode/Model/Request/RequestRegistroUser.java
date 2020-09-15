package com.scorpion.nextcode.Model.Request;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class RequestRegistroUser {

    @SerializedName("apellidos")
    @Expose
    private String apellidos;
    @SerializedName("cedula")
    @Expose
    private String cedula;
    @SerializedName("contrasenia")
    @Expose
    private String contrasenia;
    @SerializedName("correo")
    @Expose
    private String correo;
    @SerializedName("nombres")
    @Expose
    private String nombres;
    @SerializedName("status")
    @Expose
    private String status;

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
