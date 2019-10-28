
package Servicios;

import com.Servicios_sistema_ludico.dominio.Lista_preguntas;
import com.Servicios_sistema_ludico.dominio.Resultados_Alumnos;
import com.Servicios_sistema_ludico.dominioDAO.EstudianteDAO;
import com.Servicios_sistema_ludico.dominioDAO.ProfesorDAO;
import com.Servicios_sistema_ludico.dominioDAO.SistemaDAO;
import java.util.ArrayList;
import javax.jws.Oneway;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author hp
 */
@WebService(serviceName = "Servicios_web")
public class Servicios_web {
    EstudianteDAO estudiante = new EstudianteDAO();
    ProfesorDAO profesor= new ProfesorDAO();
    SistemaDAO sistema = new SistemaDAO();
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Login_Sistema")
    public String Login_Sisteam(@WebParam(name = "usuario") String usuario, @WebParam(name = "pass") String pass)
    throws Exception{
       String mensaje = sistema.Validar_usuario(usuario, pass);
        if (mensaje == null) {
            mensaje = "No existe usuario";
          }
        return mensaje;
}

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Registro_usuario_alumno")
    public String Registro_usuario_alumno(@WebParam(name = "nombre") String nombre,
            @WebParam(name = "apellido") String apellido,
            @WebParam(name = "cedula") String cedula,
            @WebParam(name = "usuario") String usuario, @WebParam(name = "pass")String pass,
            @WebParam(name = "curso") int curso,
            @WebParam(name = "usuario_ing") String usuario_ing) throws Exception {
        String mensaje = estudiante.Insertar_estudiante_curso(nombre, apellido, cedula, usuario, pass, curso, usuario_ing);
        return mensaje;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Registro_usuario_profesor")
    public String Registro_usuario_profesor(@WebParam(name = "nombre") String nombre,
            @WebParam(name = "apellido") String apellido, @WebParam(name = "cedula") String cedula, 
            @WebParam(name = "usuario") String usuario, @WebParam(name = "pass") String pass, 
            @WebParam(name = "usuario_ing")  String usuario_ing) throws Exception {
        String mensaje = profesor.Insertar_profesor(nombre, apellido, cedula, usuario, pass, usuario_ing);
        return mensaje;
    }
    
      @WebMethod(operationName = "Registro_usuario_administrador")
    public String Registro_usuario_administrador(@WebParam(name = "nombre") String nombre,
            @WebParam(name = "apellido") String apellido, @WebParam(name = "cedula") String cedula, 
            @WebParam(name = "usuario") String usuario, @WebParam(name = "pass") String pass, 
            @WebParam(name = "usuario_ing")  String usuario_ing) throws Exception {
        String mensaje = profesor.Insertar_administrador(nombre, apellido, cedula, usuario, pass, usuario_ing);
        return mensaje;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Eliminar_alumno")
    public String eliminar_alumno(@WebParam(name = "cedula") String cedula, @WebParam(name = "usuario_ing") String usuario_ing) throws Exception {
       String mensaje = estudiante.Eliminar_alumno(cedula, usuario_ing);
       return mensaje;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "Actualizar_datos")
    public String Actualizar_datos(@WebParam(name = "nombre") String nombre, @WebParam(name = "apellido") String apellido, @WebParam(name = "cedula") String cedula, @WebParam(name = "usuario") String usuario, @WebParam(name = "pass") String pass)throws Exception {
        String mensaje = sistema.Actualizar_datos(nombre, apellido, cedula, usuario, pass);
        return mensaje;
    }


    /**
     * Web service operation
     */
    @WebMethod(operationName = "Calcular_resultado")
    public String Calcular_resultado(@WebParam(name = "usuario") String usuario, @WebParam(name = "nombre_juego") String nombre_juego) throws Exception{
      String mensaje = sistema.Calcular_resultados(usuario, nombre_juego);
        return mensaje;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "guardar_notas")
    public String guardar_notas(@WebParam(name = "nombre_usuario") String nombre_usuario, @WebParam(name = "nombre_juego") String nombre_juego, @WebParam(name = "pregunta") String pregunta, @WebParam(name = "respuesta") String respuesta) throws Exception {
        String mensaje = sistema.Insertar_resultado(nombre_usuario, nombre_juego, pregunta, respuesta);
        return mensaje;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "listar_alumnos")
    public ArrayList<Resultados_Alumnos> listar_alumnos(@WebParam(name = "usuario") String usuario) throws Exception {
        ArrayList<Resultados_Alumnos> datos = profesor.Listar_alumnos(usuario);
        return datos;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Agregar_pregunta")
    public String Agregar_pregunta(@WebParam(name = "nombre_juego") String nombre_juego, @WebParam(name = "pregunta") String pregunta, @WebParam(name = "resouesta") String resouesta, @WebParam(name = "usuario_ing") String usuario_ing) throws Exception {
        String mensaje = sistema.Agregar_pregunta(nombre_juego, pregunta, resouesta, usuario_ing);
        return mensaje;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "actualizar_alumno")
    public String actualizar_alumno(@WebParam(name = "nombre") String nombre, @WebParam(name = "apellido") String apellido, @WebParam(name = "cedula") String cedula, @WebParam(name = "curso") int curso, @WebParam(name = "usuario") String usuario, @WebParam(name = "contrasenia") String contrasenia) throws Exception {
    String mensaje = estudiante.Actualizar_alumno(nombre, apellido, cedula, curso, usuario, contrasenia);
        return mensaje;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "traer_pregunta")
    public Lista_preguntas traer_pregunta(@WebParam(name = "pregunta") String pregunta) throws Exception {
        Lista_preguntas obj = sistema.pregunta(pregunta);
        return obj;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "eliminar_pregunta")
    public String eliminar_pregunta(@WebParam(name = "pregunta") String pregunta, @WebParam(name = "usuario_ing") String usuario_ing) throws Exception {
        String mensaje = sistema.eliminar_pregunta(pregunta, usuario_ing);
        return mensaje;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Actualizar")
    public String Actualizar(@WebParam(name = "pregunta") String pregunta, @WebParam(name = "respuesta") String respuesta, @WebParam(name = "estado") boolean estado) throws Exception {
       String mensaje = sistema.Actualizar_pregunta(pregunta, respuesta, estado);
        return mensaje;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "borrar_historial")
    public String borrar_historial() throws Exception {
        String mensaje = sistema.borrar_historial();
        return mensaje;
    }

   
  

}
