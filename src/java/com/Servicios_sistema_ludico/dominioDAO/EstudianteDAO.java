package com.Servicios_sistema_ludico.dominioDAO;
import com.Servicios_sistema_ludico.DAO.Conexion;
import com.Servicios_sistema_ludico.dominio.Alumno;
import com.Servicios_sistema_ludico.dominio.Lista_alumnos;
import com.Servicios_sistema_ludico.dominio.Profesor;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EstudianteDAO  {

    public EstudianteDAO() {
    }
    
    
    Conexion con = new Conexion();
    Connection conect;
    PreparedStatement ps;
    ResultSet rs;
    CallableStatement prc;
    
public String Insertar_estudiante_curso(String nombre,String apellido,
                                        String cedula,String usuario,
                                        String pass, int curso,
                                        String usuario_ing) throws Exception {
        String mensaje = null;
            conect = con.Conexion("base_escuela");
            conect.setAutoCommit(false);
            prc = conect.prepareCall
                        ("{ call PA_MANTENIMIENTO_REGISTRAR_ALUMNO(?,?,?,?,?,?,?,?) }");
            prc.setString(1, nombre);
            prc.setString(2, apellido);
            prc.setString(3,cedula);
            prc.setString(4,usuario);
            prc.setString(5,pass);
            prc.setInt(6, curso);
            prc.setString(7, usuario_ing);
            prc.registerOutParameter("msg", Types.VARCHAR);
            prc.execute();
            mensaje = prc.getString("msg");
            conect.commit();
        return mensaje;
    }
    
public String Eliminar_alumno(String cedula,String usuario_ing) throws Exception {
        String mensaje;
            conect = con.Conexion("base_escuela");
            conect.setAutoCommit(false);
            prc = conect.prepareCall
                        ("{ call PA_MANTENIMIENTO_ELIMINAR_ALUMNO(?,?,?)}");
            prc.setString(1,cedula);
            prc.setString(2, usuario_ing);
            prc.registerOutParameter("msg", Types.VARCHAR);
            prc.execute();
            mensaje = prc.getString("msg");
            conect.commit();
        return mensaje;
    }

     public ArrayList Informacion_alumnos() throws Exception {
        ArrayList<Lista_alumnos> datos = new ArrayList();
        conect = con.Conexion("base_escuela");
        ps = conect.prepareStatement("select a.curso, p.nombre,p.apellido,p.cedula,u.usuario,j.nombre_juego,rs.total from base_escuela.mant_persona p\n"
                + "join base_escuela.mant_usuario u on p.idpersona = u.id_persona\n"
                + "join base_escuela.mant_alumno a on a.id_usuario = u.idusuario\n"
                + "left join base_escuela.sist_resultados rs on a.idalumno= rs.id_alumno\n"
                + "left join sist_juegos j on rs.id_juego=j.idjuego;");
        rs = ps.executeQuery();
        while (rs.next()) {
            Lista_alumnos obj = new Lista_alumnos();
            obj.setCurso(rs.getInt(1));
            obj.setNombre(rs.getString(2));
            obj.setApellido(rs.getString(3));
            obj.setCedula(rs.getString(4));
            obj.setUsuario(rs.getString(5));
            obj.setNombre_juego(rs.getString(6));
            obj.setTotal(rs.getInt(7));
            datos.add(obj);
        }
        ps.execute();
        return datos;
    }   
    
     public Alumno alumno_info(String usuario) throws Exception {
        Alumno datos = new Alumno();
        conect = con.Conexion("base_escuela");
        ps = conect.prepareStatement("select p.nombre,p.apellido,p.cedula,a.curso,u.usuario,u.contrasenia from base_escuela.mant_persona p\n"
                + "join base_escuela.mant_usuario u on p.idpersona = u.id_persona\n"
                + "join base_escuela.mant_alumno a on a.id_usuario = u.idusuario\n"
                + "where u.usuario = ?\n"
                + "group by p.nombre,p.apellido,p.cedula,a.curso,u.usuario,u.contrasenia;");
        ps.setString(1, usuario);
        rs = ps.executeQuery();
        while (rs.next()) {
            datos.setNombre(rs.getString(1));
            datos.setApellido(rs.getString(2));
            datos.setCedula(rs.getString(3));
            datos.setCurso(rs.getInt(4));
            datos.setUsuario(rs.getString(5));
            datos.setContrasenia(rs.getString(6));
        }
        ps.execute();
        return datos;
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
     public String Actualizar_alumno(String nombre, String apellido, String cedula,int curso, String usuario, String contra) throws Exception {
        String mensaje = null;
        conect = con.Conexion("base_escuela");
        conect.setAutoCommit(false);
        prc = conect.prepareCall("{ call PA_ACTUALIZAR_ALUMNO(?,?,?,?,?,?,?) }");
        prc.setString(1, nombre);
        prc.setString(2, apellido);
        prc.setString(3, cedula);
        prc.setInt(4, curso);
        prc.setString(5, usuario);
        prc.setString(6, contra);
        prc.registerOutParameter("msg", Types.VARCHAR);
        prc.execute();
        mensaje = prc.getString("msg");
        conect.commit();
        return mensaje;
    }
    }
    

