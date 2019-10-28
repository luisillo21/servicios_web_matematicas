package com.Servicios_sistema_ludico.dominioDAO;

import com.Servicios_sistema_ludico.DAO.Conexion;
import com.Servicios_sistema_ludico.dominio.Lista_alumnos;
import com.Servicios_sistema_ludico.dominio.Lista_juegos;
import com.Servicios_sistema_ludico.dominio.Lista_profesor;
import com.Servicios_sistema_ludico.dominio.Profesor;
import com.Servicios_sistema_ludico.dominio.Resultados_Alumnos;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfesorDAO {

    public ProfesorDAO() {
    }

    Conexion con = new Conexion();
    Connection conect;
    PreparedStatement ps;
    ResultSet rs;
    CallableStatement prc;

    public String Insertar_profesor(String nombre, String apellido,
            String cedula, String usuario,
            String pass,
            String usuario_ing) throws Exception {
        String mensaje = null;
        conect = con.Conexion("base_escuela");
        conect.setAutoCommit(false);
        CallableStatement prc = conect.prepareCall("{ call PA_MANTENIMIENTO_REGISTRAR_PROFESOR(?,?,?,?,?,?,?)}");
        prc.setString(1, nombre);
        prc.setString(2, apellido);
        prc.setString(3, cedula);
        prc.setString(4, usuario);
        prc.setString(5, pass);
        prc.setString(6, usuario_ing);
        prc.registerOutParameter("msg", Types.VARCHAR);
        prc.execute();
        mensaje = prc.getString("msg");
        conect.commit();
        return mensaje;
    }

    public String Insertar_administrador(String nombre, String apellido,
            String cedula, String usuario,
            String pass,
            String usuario_ing) throws Exception {
        String mensaje = null;
        conect = con.Conexion("base_escuela");
        conect.setAutoCommit(false);
        CallableStatement prc = conect.prepareCall("{ call PA_PRINCIPAL_REGISTRAR_ADMINISTRADOR(?,?,?,?,?,?,?)}");
        prc.setString(1, nombre);
        prc.setString(2, apellido);
        prc.setString(3, cedula);
        prc.setString(4, usuario);
        prc.setString(5, pass);
        prc.setString(6, usuario_ing);
        prc.registerOutParameter("msg", Types.VARCHAR);
        prc.execute();
        mensaje = prc.getString("msg");
        conect.commit();
        return mensaje;
    }

    public ArrayList Listar_alumnos(String usuario) throws Exception {
        ArrayList<Resultados_Alumnos> datos = new ArrayList();
        conect = con.Conexion("base_escuela");
        conect.setAutoCommit(false);
        prc = conect.prepareCall("{ call PA_MANTENIMIENTO_LISTAR_ALUMNOS(?) }");
        prc.setString(1, usuario);
        rs = prc.executeQuery();
        while (rs.next()) {
            Resultados_Alumnos obj = new Resultados_Alumnos();
            obj.setNombre(rs.getString(1));
            obj.setApellido(rs.getString(2));
            obj.setCurso(rs.getInt(3));
            obj.setNombre_juego(rs.getString(4));
            obj.setTotal(rs.getInt(5));
            datos.add(obj);
        }
        prc.execute();
        return datos;
    }

    public ArrayList Informacion_profesor() throws Exception {
        ArrayList<Lista_profesor> datos = new ArrayList();
        conect = con.Conexion("base_escuela");
        ps = conect.prepareStatement("select s.curso ,p.nombre,p.apellido,p.cedula,u.usuario from base_escuela.mant_persona p\n"
                + "join base_escuela.mant_usuario u on p.idpersona = u.id_persona\n"
                + "join base_escuela.mant_profesor a on a.id_usuario = u.idusuario\n"
                + "left join base_escuela.mant_alumno s on s.id_profesor = a.idprofesor\n"
                + "group by s.curso,p.nombre,p.apellido,p.cedula,u.usuario;");
        rs = ps.executeQuery();
        while (rs.next()) {
            Lista_profesor obj = new Lista_profesor();
            obj.setCurso(rs.getInt(1));
            obj.setNombre(rs.getString(2));
            obj.setApellido(rs.getString(3));
            obj.setCedula(rs.getString(4));
            obj.setUsuario(rs.getString(5));
            datos.add(obj);
        }
        ps.execute();
        return datos;
    }
    
    public Profesor profesor_info(String usuario) throws Exception {
        Profesor datos = new Profesor();
        conect = con.Conexion("base_escuela");
        ps = conect.prepareStatement("select p.nombre,p.apellido,p.cedula,u.usuario,u.contrasenia from base_escuela.mant_persona p\n"
                + "join base_escuela.mant_usuario u on p.idpersona = u.id_persona\n"
                + "join base_escuela.mant_profesor a on a.id_usuario = u.idusuario\n"
                + "where u.usuario = ?\n"
                + "group by p.nombre,p.apellido,p.cedula,u.usuario,u.contrasenia;");
        ps.setString(1, usuario);
        rs = ps.executeQuery();
        while (rs.next()) {
            datos.setNombre(rs.getString(1));
            datos.setApellido(rs.getString(2));
            datos.setCedula(rs.getString(3));
            datos.setUsuario(rs.getString(4));
            datos.setContrasenia(rs.getString(5));
        }
        ps.execute();
        return datos;
    }
}
