/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package src.com.tic.dao;

import java.util.ArrayList;
import src.com.tic.pojo.Incidencia;
import src.com.tic.pojo.Tecnico;

/**
 *
 * @author alumno
 */
public interface GestorDAO {

    //El técnico seleccionado en la tabla será asignado a la incidencia seleccionada.
    //Si el count del ID_Tecnico en incidencias es mayor 5, no se le asignará y se devolverá un false.
    public void asignarTecnico(int idTecnico, int idIncidencia) throws Exception;

    //El estado pasará a 'alta' en las Incidencias con estado NULL y 'cerrada'
    public void altaIncidencias(Incidencia incidencia) throws Exception;

    //Lista Incidencias en espera
    public ArrayList<Incidencia> getIncidenciasByEspera() throws Exception;

    //Listar todas las incidencias
    public ArrayList<Incidencia> getAllIncidencias() throws Exception;

    //Listar incidencias por tipo
    public ArrayList<Incidencia> getIncidenciasByTipo(String tipoIncidencia) throws Exception;

    //Listar incidencias por tecnico
    public ArrayList<Incidencia> getIncidenciasByTecnico(int idTecnico) throws Exception;
    
    public ArrayList<Integer> getTecnicoDisp() throws Exception;

}
