/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.com.tic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import src.com.tic.pojo.Usuario;
import src.com.tic.utils.Configuration;

/**
 *
 * @author alumno
 */
public class AdministradorDAOimpl {

    public static void crearUsuario(Usuario usuario) throws Exception {
        String SQL = "INSERT INTO Usuarios (nombre_usuario, nombre_apellidos, correo_electronico, telefono, contrasena) VALUES (?,?,?,?,SHA2(?,256));";
        try {
            // Escribe el archivo con el nombre del identificador + .credencial
            try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(SQL);) {
                pstm.setString(1, usuario.getNombreDeUsuario());
                pstm.setString(2, usuario.getNombreYApellidos());
                pstm.setString(3, usuario.getCorreo());
                pstm.setString(4, usuario.getNumeroTelefono());
                pstm.setString(5, usuario.getContrasena());
                int r = pstm.executeUpdate();
                System.out.println("Entradas insertadas: " + r);
            }

        } catch (Exception e) {
            throw e;
        }
    }
}
