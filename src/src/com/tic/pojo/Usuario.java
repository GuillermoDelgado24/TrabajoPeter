/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.com.tic.pojo;

/**
 *
 * @author tarde
 */
public class Usuario {
    private int idUsuario;
    private String nombreDeUsuario;
    private String nombreApellidos;
    private String usuario;
    private String correo;
    private String numeroTelefono;
    private String contrasena;

    public Usuario(String nombreDeUsuario, String nombreApellidos, String usuario, String correo, String numeroTelefono, String contrasena) {
        this.nombreDeUsuario = nombreDeUsuario;
        this.nombreApellidos = nombreApellidos;
        this.usuario = usuario;
        this.correo = correo;
        this.numeroTelefono = numeroTelefono;
        this.contrasena = contrasena;
    }

    public Usuario(int idUsuario, String nombreDeUsuario, String nombreApellidos, String usuario, String correo, String numeroTelefono, String contrasena) {
        this.idUsuario = idUsuario;
        this.nombreDeUsuario = nombreDeUsuario;
        this.nombreApellidos = nombreApellidos;
        this.usuario = usuario;
        this.correo = correo;
        this.numeroTelefono = numeroTelefono;
        this.contrasena = contrasena;
    }


    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreYApellidos() {
        return nombreApellidos;
    }

    public void setNombreYApellidos(String nombreApellidos) {
        this.nombreApellidos = nombreApellidos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getNombreUsuario() {
        return idUsuario;
    }

    public void setNombreUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nombreDeUsuario=" + nombreDeUsuario + ", nombreApellidos=" + nombreApellidos + ", usuario=" + usuario + ", correo=" + correo + ", numeroTelefono=" + numeroTelefono + ", contrasena=" + contrasena + '}';
    }

    
}
