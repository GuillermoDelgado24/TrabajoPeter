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
    private String nombreYApellidos;
    private String usuario;
    private String correo;
    private String nTelefono;
    private String contrasena;

    public Usuario(String nombreYApellidos, String usuario, String correo, String nTelefono, String contrasena) {
        this.nombreYApellidos = nombreYApellidos;
        this.usuario = usuario;
        this.correo = correo;
        this.nTelefono = nTelefono;
        this.contrasena = contrasena;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreYApellidos() {
        return nombreYApellidos;
    }

    public void setNombreYApellidos(String nombreYApellidos) {
        this.nombreYApellidos = nombreYApellidos;
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

    public String getnTelefono() {
        return nTelefono;
    }

    public void setnTelefono(String nTelefono) {
        this.nTelefono = nTelefono;
    }
    
    
}
