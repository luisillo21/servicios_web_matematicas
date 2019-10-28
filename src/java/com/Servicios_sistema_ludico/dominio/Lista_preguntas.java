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
public class Lista_preguntas {
    
    private String nombre_juego;
    private String pregunta;
    private String respuesta;
    private boolean activo;
    
    public Lista_preguntas() {
    }

    public Lista_preguntas(String nombre_juego, String pregunta, String respuesta, boolean activo) {
        this.nombre_juego = nombre_juego;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.activo=activo;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getNombre_juego() {
        return nombre_juego;
    }

    public void setNombre_juego(String nombre_juego) {
        this.nombre_juego = nombre_juego;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
    
    
}
