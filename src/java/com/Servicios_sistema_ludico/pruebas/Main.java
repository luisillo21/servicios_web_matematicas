/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Servicios_sistema_ludico.pruebas;

import com.Servicios_sistema_ludico.DAO.Conexion;
import com.Servicios_sistema_ludico.dominio.Profesor;
import com.Servicios_sistema_ludico.dominio.Resultados_Alumnos;
import com.Servicios_sistema_ludico.dominioDAO.EstudianteDAO;
import com.Servicios_sistema_ludico.dominioDAO.ProfesorDAO;
import com.Servicios_sistema_ludico.dominioDAO.SistemaDAO;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class Main {
    
    public static void main(String[] args) throws Exception{
        Conexion on = new Conexion();
        ProfesorDAO obj2 = new ProfesorDAO();
      
//        System.out.println(obj2.Insertar_profesor("Mario","Castañeda","0950588888",
//                "mario3412","09568","luis123"));
        
//          ArrayList<Resultados_Alumnos> obj3 = obj2.Listar_alumnos("luisillo");
//            for (int i = 0; i < obj3.size(); i++) {
//                System.out.println("Nombre:" +obj3.get(i).getNombre() + " Apellido: "+obj3.get(i).getApellido()+" Curso: "+obj3.get(i).getCurso()
//                +"nombre juego:"+obj3.get(i).getNombre_juego());
//        }
         
        
        SistemaDAO obj = new SistemaDAO();
       //System.out.println(       
       //obj.Agregar_pregunta("sumas rapidas","cuanto es 4+4", "8", "luisillo"));
    
//       obj.Insertar_resultado("kevin23", "sumas rapidas", "cuanto es 4+3", "8");
//        if (mensaje == null) {
//            mensaje = "No existe usuario";
//        }

        Profesor profesor = obj2.profesor_info("0950596353");
        System.out.println("nombre"+ profesor.getNombre());
        
    }
    
}
