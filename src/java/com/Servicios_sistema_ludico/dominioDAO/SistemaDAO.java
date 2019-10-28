/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Servicios_sistema_ludico.dominioDAO;

import com.Servicios_sistema_ludico.DAO.Conexion;
import com.Servicios_sistema_ludico.dominio.Genr_historial;
import com.Servicios_sistema_ludico.dominio.Lista_juegos;
import com.Servicios_sistema_ludico.dominio.Lista_preguntas;
import com.Servicios_sistema_ludico.dominio.Lista_profesor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class SistemaDAO {

    Conexion con = new Conexion();
    Connection conect;
    PreparedStatement ps;
    ResultSet rs;
    CallableStatement prc;

    public String borrar_historial() throws Exception {

        String mensaje;

        conect = con.Conexion("base_escuela");
        ps = conect.prepareStatement(" delete from base_escuela.genr_historial where idhistorial > 0;");
        int res = ps.executeUpdate();
        if (res > 0) {
            mensaje = "historial borrado";
        } else {
            mensaje = "historial ya se encuentra vacio";
        }
        return mensaje;
    }

    public String Validar_usuario(String usuario, String pass) throws Exception {
        String mensaje = null;
        conect = con.Conexion("base_escuela");
        conect.setAutoCommit(false);
        prc = conect.prepareCall("{ call PA_MANTENIMIENTO_LOGIN(?,?,?) }");
        prc.setString(1, usuario);
        prc.setString(2, pass);
        prc.registerOutParameter("p_salida", Types.VARCHAR);
        prc.execute();
        mensaje = prc.getString("p_salida");
        conect.commit();
        return mensaje;
    }

    public String Agregar_pregunta(String nombre_juego, String pregunta, String respuesta, String usuario_ing) throws Exception {

        String mensaje = null;
        conect = con.Conexion("base_escuela");
        conect.setAutoCommit(false);
        prc = conect.prepareCall("{ call PA_SISTEMA_AGREGAR_PREGUNTA(?,?,?,?,?) }");
        prc.setString(1, nombre_juego);
        prc.setString(2, pregunta);
        prc.setString(3, respuesta);
        prc.setString(4, usuario_ing);
        prc.registerOutParameter("p_salida", Types.VARCHAR);
        prc.execute();
        mensaje = prc.getString("p_salida");
        conect.commit();

        return mensaje;
    }

    public String Actualizar_datos(String nombre, String apellido, String cedula, String usuario, String pass) throws Exception {

        String mensaje = null;

        conect = con.Conexion("base_escuela");
        conect.setAutoCommit(false);
        prc = conect.prepareCall("{ call PA_SISTEMA_ACTUALIZAR_DATOS(?,?,?,?,?,?) }");
        prc.setString(1, nombre);
        prc.setString(2, apellido);
        prc.setString(3, cedula);
        prc.setString(4, usuario);
        prc.setString(5, pass);
        prc.registerOutParameter("msg", Types.VARCHAR);
        prc.execute();
        mensaje = prc.getString("msg");
        conect.commit();

        return mensaje;
    }

    public String Calcular_resultados(String nombre, String nombre_juego) throws Exception {
        String mensaje = null;
        conect = con.Conexion("base_escuela");
        conect.setAutoCommit(false);
        prc = conect.prepareCall("{ call PA_SISTEMA_RESULTADOS_TOTAL(?,?,?) }");
        prc.setString(1, nombre);
        prc.setString(2, nombre_juego);
        prc.registerOutParameter("msg", Types.VARCHAR);
        prc.execute();
        mensaje = prc.getString("msg");
        conect.commit();
        return mensaje;
    }

    public String eliminar_pregunta(String pregunta, String usuario_ing) throws Exception {
        String mensaje = null;
        conect = con.Conexion("base_escuela");
        conect.setAutoCommit(false);
        prc = conect.prepareCall("{ call PA_ELIMINAR_PREGUNTA(?,?) }");
        prc.setString(1, pregunta);
        prc.setString(2, usuario_ing);
        boolean res = prc.execute();
        conect.commit();
        if (res) {
            mensaje = "Se elimmino esta pregunta";
        } else {
            mensaje = "Error";
        }

        return mensaje;
    }

    public String Insertar_resultado(String usuario, String nombre_juego, String pregunta, String respuesta) throws Exception {
        String bandera = null;
        conect = con.Conexion("base_escuela");
        conect.setAutoCommit(false);
        prc = conect.prepareCall("{ call PA_MANTENIMIENTO_INSERTAR_NOTAS(?,?,?,?,?) }");
        prc.setString(1, usuario);
        prc.setString(2, nombre_juego);
        prc.setString(3, pregunta);
        prc.setString(4, respuesta);
        prc.execute();
        bandera = prc.getString("msg");
        conect.commit();
        return bandera;
    }

    public String Insertar_pregunta(String nombre_juego, String pregunta, String respuesta, String usuario_ing) throws Exception {
        String bandera = null;
        conect = con.Conexion("base_escuela");
        conect.setAutoCommit(false);
        prc = conect.prepareCall("{ call PA_SISTEMA_AGREGAR_PREGUNTA(?,?,?,?,?) }");
        prc.setString(1, nombre_juego);
        prc.setString(2, pregunta);
        prc.setString(3, respuesta);
        prc.setString(4, usuario_ing);
        prc.registerOutParameter("p_salida", Types.VARCHAR);
        prc.execute();
        bandera = prc.getString("p_salida");
        conect.commit();
        return bandera;
    }

    public String Actualizar_pregunta(String pregunta, String respuesta, boolean estado) throws Exception {
        String bandera = "Pregunta editada";
        conect = con.Conexion("base_escuela");
        conect.setAutoCommit(false);
        prc = conect.prepareCall("{ call PA_ACTUALIZAR_PREGUNTA(?,?,?) }");
        prc.setString(1, pregunta);
        prc.setString(2, respuesta);
        prc.setBoolean(3, estado);
        prc.execute();
        conect.commit();
        return bandera;
    }

    public ArrayList Lista_de_juegos() throws Exception {
        ArrayList<Lista_juegos> datos = new ArrayList();
        conect = con.Conexion("base_escuela");
        ps = conect.prepareStatement("select j.nombre_juego, count(p.pregunta) from sist_juegos j\n"
                + "left join sist_banco_preguntas p on j.idjuego = p.id_juego\n"
                + "where p.activo = 1\n"
                + "group by j.nombre_juego;");
        rs = ps.executeQuery();
        while (rs.next()) {
            Lista_juegos obj = new Lista_juegos();
            obj.setNombre_juego(rs.getString(1));
            obj.setCantidad_de_preguntas(rs.getInt(2));
            datos.add(obj);
        }
        ps.execute();
        return datos;
    }

    public Lista_preguntas pregunta(String pregunta) throws Exception {
        Lista_preguntas obj = new Lista_preguntas();
        conect = con.Conexion("base_escuela");
        ps = conect.prepareStatement("select j.nombre_juego,p.pregunta,r.respuesta,p.activo from sist_juegos j\n"
                + "left join sist_banco_preguntas p on j.idjuego = p.id_juego\n"
                + "left join sist_banco_respuestas r on p.idbanco_preguntas = r.id_pregunta\n"
                + "where p.pregunta = ? \n"
                + "group by j.nombre_juego,p.pregunta,r.respuesta,p.activo");
        ps.setString(1, pregunta);
        rs = ps.executeQuery();
        while (rs.next()) {
            obj.setNombre_juego(rs.getString(1));
            obj.setPregunta(rs.getString(2));
            obj.setRespuesta(rs.getString(3));
            obj.setActivo(rs.getBoolean("activo"));
        }
        ps.execute();
        return obj;
    }

    public ArrayList Lista_preguntas_respuestas(String nombre_juego) throws Exception {
        ArrayList<Lista_preguntas> datos = new ArrayList();
        conect = con.Conexion("base_escuela");
        ps = conect.prepareStatement("select j.nombre_juego,p.pregunta,r.respuesta,p.activo from sist_juegos j\n"
                + "left join sist_banco_preguntas p on j.idjuego = p.id_juego\n"
                + "left join sist_banco_respuestas r on p.idbanco_preguntas = r.id_pregunta\n"
                + "where j.nombre_juego = ? and p.activo = 1 \n"
                + "group by j.nombre_juego,p.pregunta,r.respuesta,p.activo;");
        ps.setString(1, nombre_juego);
        rs = ps.executeQuery();
        while (rs.next()) {
            Lista_preguntas obj = new Lista_preguntas();
            obj.setNombre_juego(rs.getString(1));
            obj.setPregunta(rs.getString(2));
            obj.setRespuesta(rs.getString(3));
            obj.setActivo(rs.getBoolean("activo"));
            datos.add(obj);
        }
        ps.execute();
        return datos;
    }

    public ArrayList Historial() throws Exception {
        ArrayList<Genr_historial> datos = new ArrayList();
        conect = con.Conexion("base_escuela");
        ps = conect.prepareStatement("select * from genr_historial");
        rs = ps.executeQuery();
        while (rs.next()) {
            Genr_historial obj = new Genr_historial();
            obj.setUsuario(rs.getString("usuario"));
            obj.setAccion(rs.getString("accion"));
            obj.setFecha(rs.getTimestamp("fecha").toString());
            obj.setDescripcion(rs.getString("descripcion"));

            datos.add(obj);
        }
        ps.execute();
        return datos;
    }

    public ArrayList<Lista_preguntas> listado_sumas_basicas() throws Exception {

        ArrayList<Lista_preguntas> datos = new ArrayList();
        conect = con.Conexion("base_escuela");
        ps = conect.prepareStatement("select p.pregunta,r.respuesta from base_escuela.sist_banco_preguntas p\n"
                + "join base_escuela.sist_banco_respuestas r on p.idbanco_preguntas = r.idbanco_respuestas\n"
                + "join base_escuela.sist_juegos j\n"
                + "where j.nombre_juego ='sumas basicas';");
        rs = ps.executeQuery();
        while (rs.next()) {
            Lista_preguntas preguntas = new Lista_preguntas();
            preguntas.setPregunta(rs.getString("p.pregunta"));
            preguntas.setRespuesta(rs.getString("r.respuesta"));
            datos.add(preguntas);
        }

        return datos;

    }

}
