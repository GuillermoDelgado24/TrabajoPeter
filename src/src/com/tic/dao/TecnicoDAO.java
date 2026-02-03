/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package src.com.tic.dao;

import java.sql.Date;
import java.util.ArrayList;
import src.com.tic.pojo.Incidencia;

/**
 *
 * @author alumno
 */
public interface TecnicoDAO {

    //Poner incidencia en curso:
    public void atenderIncidencia(int idIncidencia) throws Exception;

    //Pone el estado de la incidencia en 'cerrado', establece el resultado de éxito.
    //Se tiene que haber abierto con anterioridad una pantalla
    //para introducir la descripción de resultado y el tipo de Incidencia
    //en caso de que no esté puesto.
    public void cerrarIncidencia(Incidencia incidencia) throws Exception;

    //Añade a la tabla Tipos_Incidencias un tipo.
    public void agregarTipoIncidencia(String tipoIncidencia) throws Exception;

    //Incidencias cuyo estado sea 'en curso' o 'asignada' y que correspondan al técnico
    public ArrayList<Incidencia> getIncidenciasAsignadas(int idTecnico) throws Exception;

    //Ver incidencias por tipo
    public ArrayList<Incidencia> getIncidenciasByTipo(String tipoIncidencia) throws Exception;

    //Ver incidencias cerradas en un periodo de tiempo 
    public ArrayList<Incidencia> getIncidenciasBetweenFechas(Date fechaInicio, Date fechaFin) throws Exception;

}
