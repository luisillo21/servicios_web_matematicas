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
public class Lista_juegos {

    private String nombre_juego;
    private int cantidad_de_preguntas;

    public Lista_juegos() {
    }

    public Lista_juegos(String nombre_juego, int cantidad_de_preguntas) {
        this.nombre_juego = nombre_juego;
        this.cantidad_de_preguntas = cantidad_de_preguntas;
    }

    public int getCantidad_de_preguntas() {
        return cantidad_de_preguntas;
    }

    public void setCantidad_de_preguntas(int cantidad_de_preguntas) {
        this.cantidad_de_preguntas = cantidad_de_preguntas;
    }

    public String getNombre_juego() {
        return nombre_juego;
    }

    public void setNombre_juego(String nombre_juego) {
        this.nombre_juego = nombre_juego;
    }
    
    
}
