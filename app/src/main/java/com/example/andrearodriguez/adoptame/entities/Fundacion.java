package com.example.andrearodriguez.adoptame.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by andrearodriguez on 7/26/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Fundacion {

    @JsonIgnore
    private String id;


    private String email;
    private String personaContacto;
    private String nombreFundacion;
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

    public String getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
    }

    public String getNombreFundacion() {
        return nombreFundacion;
    }

    public void setNombreFundacion(String nombreFundacion) {
        this.nombreFundacion = nombreFundacion;
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
