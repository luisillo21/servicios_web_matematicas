/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Servicios_sistema_ludico.dominio;

/**
 *
 * @author hp
 */
public class Alumno {
    private String nombre;
    private String apellido;
    private String cedula;
    private int curso;
    private String usuario;
    private String contrasenia;

    public Alumno() {
    }

    public Alumno(String nombre, String apellido, String cedula, int curso, String usuario, String contrasenia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.curso = curso;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
}
