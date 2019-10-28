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
public class Resultados_Alumnos {
    private String nombre;
    private String apellido;
    private int curso;
    private String nombre_juego;
    private int total;

    public Resultados_Alumnos() {
    }

    

    public Resultados_Alumnos(String nombre, String apellido, int curso,String nombre_juego,int total) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.curso = curso;
        this.nombre_juego = nombre_juego;
        this.total = total;
    }

    

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public String getNombre_juego() {
        return nombre_juego;
    }

    public void setNombre_juego(String nombre_juego) {
        this.nombre_juego = nombre_juego;
    }
    
    
    
    
}
