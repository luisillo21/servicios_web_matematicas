
package Servicios;

import com.Servicios_sistema_ludico.dominio.Alumno;
import com.Servicios_sistema_ludico.dominio.Genr_historial;
import com.Servicios_sistema_ludico.dominio.Lista_alumnos;
import com.Servicios_sistema_ludico.dominio.Lista_juegos;
import com.Servicios_sistema_ludico.dominio.Lista_preguntas;
import com.Servicios_sistema_ludico.dominio.Lista_profesor;
import com.Servicios_sistema_ludico.dominio.Profesor;
import com.Servicios_sistema_ludico.dominioDAO.EstudianteDAO;
import com.Servicios_sistema_ludico.dominioDAO.ProfesorDAO;
import com.Servicios_sistema_ludico.dominioDAO.SistemaDAO;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


@WebService(serviceName = "Listas_sistema")
public class Listas_sistema {

    EstudianteDAO estudiante = new EstudianteDAO();
    ProfesorDAO profesor= new ProfesorDAO();
    SistemaDAO sistema = new SistemaDAO();
    

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Informacion_alumnos")
    public ArrayList<Lista_alumnos> Informacion_alumnos() throws Exception {
        ArrayList<Lista_alumnos> info = estudiante.Informacion_alumnos();
        return info;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Lista_profesores")
    public ArrayList<Lista_profesor> Lista_profesores() throws Exception{
        ArrayList<Lista_profesor> datos = profesor.Informacion_profesor();
        return datos;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Listar_juegos")
    public ArrayList<Lista_juegos> Listar_juegos() throws Exception{
        ArrayList<Lista_juegos> datos = sistema.Lista_de_juegos();
        return datos;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Listado_preguntas")
    public ArrayList<Lista_preguntas> Listado_preguntas(@WebParam(name = "nombre_juego") String nombre_juego) throws Exception {
        ArrayList<Lista_preguntas> datos = sistema.Lista_preguntas_respuestas(nombre_juego);
        return datos;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Historial")
    public ArrayList<Genr_historial> Historial() throws Exception {
        ArrayList<Genr_historial> datos3 = sistema.Historial();
        return datos3;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Profesor_info")
    public Profesor Profesor_info(@WebParam(name = "usuario") String usuario) throws Exception {
        Profesor obj = profesor.profesor_info(usuario);
        return obj;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "info_alumno")
    public Alumno info_alumno(@WebParam(name = "usuario") String usuario) throws Exception {
        Alumno datos = estudiante.alumno_info(usuario);
        return datos;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "listar_sumas_basicas_preguntas")
    public ArrayList<Lista_preguntas> listar_sumas_basicas_preguntas() throws Exception {
        ArrayList<Lista_preguntas> datos = sistema.listado_sumas_basicas();
        return datos;
    }




    
    
    
}
