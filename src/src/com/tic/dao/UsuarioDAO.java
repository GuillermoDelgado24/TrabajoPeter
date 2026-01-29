/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package src.com.tic.dao;

import java.awt.List;
import java.util.ArrayList;
import src.com.tic.pojo.Incidencia;
import src.com.tic.pojo.Usuario;

/**
 *
 * @author alumno
 */
public interface UsuarioDAO {

    public ArrayList<Incidencia> getIncidenciasUsuario();

    public Incidencia getIncidenciaPorId(int id);

    public void crearIncidencia(Incidencia incidencia);

    public boolean solicitarReapertura(int idIncidencia);

}
