/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Servicios_sistema_ludico.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class Conexion {
    
    private Connection con;
    
     public Connection getConnection(){
        return con;
    }
    public Connection Conexion(String base) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+base+
                    "?serverTimezone=UTC&useSSL=false","root", "root12345");
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+base+"?serverTimezone=UTC","root", "root12345");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        
        return con;
        
        
    }
    
//    public static void main(String[] args){
//    
//        Conexion con = new Conexion();
//        System.out.println(con.Conexion("prueba"));
//        
//    }
}
