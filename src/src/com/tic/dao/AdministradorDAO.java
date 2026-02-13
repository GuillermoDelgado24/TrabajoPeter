/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package src.com.tic.dao;

import java.util.ArrayList;
import src.com.tic.pojo.Dispositivo;
import src.com.tic.pojo.Espacio;
import src.com.tic.pojo.Usuario;
import src.com.tic.pojo.Incidencia;

/**
 *
 * @author alumno
 */
public interface AdministradorDAO {
    public ArrayList<Dispositivo> getDispositivos() throws Exception;
    public void updateDispositivos(Dispositivo disp) throws Exception;
    public void deleteDispositivos(int IdDispositivo) throws Exception;
    public void insertDispositivos(Dispositivo disp) throws Exception;
    public void insertEspacios(Espacio esp) throws Exception;
    public ArrayList<Espacio> getEspacios() throws Exception;
    public void updateEspacio(Espacio esp) throws Exception;
    public void deleteEspacio(int IdDEspacio) throws Exception;
    public void insertUsuarios(Usuario usu) throws Exception;
    public ArrayList<Usuario> getUsuarios() throws Exception;
    public void updateUsuario(Usuario usu) throws Exception;
    public void deleteUsuario(int IdDUsuario) throws Exception;
    public ArrayList<Usuario> getTipoUsuarioBasico() throws Exception;
    public ArrayList<Usuario> getTipoUsuarioTecnico() throws Exception;
    public ArrayList<Usuario> getTipoUsuarioGestor() throws Exception;
    public ArrayList<Incidencia> getAllIncidencias() throws Exception;
    
    
    
    
    
    
}
