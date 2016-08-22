package com.example.andrearodriguez.adoptame.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by andrearodriguez on 7/26/16.
 */
public class Fundacion {

    @JsonIgnore
    private String id;

    private String email;
    private String representante;
    private String nit;
    private String nfundacion;
    private String telefono;
    private String direccion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNfundacion() {
        return nfundacion;
    }

    public void setNfundacion(String nombrefundacion) {
        this.nfundacion = nombrefundacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
