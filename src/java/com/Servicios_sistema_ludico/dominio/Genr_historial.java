/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Servicios_sistema_ludico.dominio;


import java.sql.Timestamp;

/**
 *
 * @author hp
 */
public class Genr_historial {
    
    private String usuario;
    private String accion;
    private String fecha;
    private String descripcion;

    public Genr_historial() {
    }

    public Genr_historial(String usuario, String accion, String fecha, String descripcion) {
        this.usuario = usuario;
        this.accion = accion;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
