/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.com.tic.pojo;

/**
 *
 * @author tarde
 */
public class Gestor {
    private int idGestor;
    private int idUsuario;
    private boolean esAdministrador;

    public Gestor(int idGestor, int idUsuario, boolean esAdministrador) {
        this.idGestor = idGestor;
        this.idUsuario = idUsuario;
        this.esAdministrador = esAdministrador;
    }

    public Gestor(int idUsuario, boolean esAdministrador) {
        this.idUsuario = idUsuario;
        this.esAdministrador = esAdministrador;
    }

    public boolean isEsAdministrador() {
        return esAdministrador;
    }

    public void setEsAdministrador(boolean esAdministrador) {
        this.esAdministrador = esAdministrador;
    }

    public int getIdGestor() {
        return idGestor;
    }

    public void setIdGestor(int idGestor) {
        this.idGestor = idGestor;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
}
