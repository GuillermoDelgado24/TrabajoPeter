/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package src.com.tic.dao;

import java.util.ArrayList;
import src.com.tic.pojo.Dispositivo;
import src.com.tic.pojo.Incidencia;

/**
 *
 * @author alumno
 */
public interface UsuarioDAO {

    public ArrayList<Incidencia> getIncidenciasUsuario(int idUsuario) throws Exception;

    public Incidencia getIncidenciaPorId(int idIncidencia, int idUsuario) throws Exception;

    public void crearIncidencia(Incidencia incidencia, int idEspacio) throws Exception;

    public boolean solicitarReapertura(int idIncidencia) throws Exception;
    

    
}
