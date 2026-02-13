/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.com.tic.dao;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import src.com.tic.utils.Configuration;
import java.sql.*;
import java.util.ArrayList;
import src.com.tic.pojo.Incidencia;

/**
 *
 * @author alumno
 */
public class LoginDAOimpl implements AutoCloseable {
    static {
        try {
            Class.forName(Configuration.DRIVER);
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
            System.exit(1);
        }
    }
    
    Connection con = null;
    private static final String ENCODING_TYPE = "UTF-8";

    public int compararContraseña(String hash, String nombreUsuario) throws SQLException, NoSuchAlgorithmException, IOException {
        int idUsuario = -1;
        String hash_almacenado = "";
        String SQL = "SELECT contrasena, ID_Usuario FROM Usuarios WHERE nombre_usuario = ?";
        try {
            byte[] resumen = HASHManager.getDigest(hash.getBytes(ENCODING_TYPE));

            // Lee el archivo almacenado
            try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(SQL);) {
                pstm.setString(1, nombreUsuario);
                try (ResultSet resul = pstm.executeQuery()) {
                    if (resul.next()) {
                        hash_almacenado = resul.getString("contrasena");
                        //admin123
                        idUsuario = resul.getInt("ID_Usuario");

                    }
                }
            } catch (SQLException e) {
                throw e;
            }
            if (HASHManager.compararResumenes(resumen, HASHManager.hexStringToByteArray(hash_almacenado))) {
                return idUsuario;
            } else {
                return -1;
            }

        } catch (NoSuchAlgorithmException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }

    public boolean esTecnico(int idUsuario) throws SQLException {
        String SQL = "SELECT COUNT(*) as repeticiones FROM Tecnicos WHERE ID_Usuario = ? GROUP BY ID_Usuario";
        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(SQL);) {
            pstm.setInt(1, idUsuario);
            try (ResultSet resul = pstm.executeQuery()) {
                if (resul.next()) {
                    if (resul.getInt("repeticiones") > 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            throw e;
        }
        return false;
    }

    public boolean esGestor(int idUsuario) throws SQLException {
        String SQL = "SELECT COUNT(*) as repeticiones FROM Gestores WHERE ID_Usuario = ? GROUP BY ID_Usuario";
        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(SQL);) {
            pstm.setInt(1, idUsuario);
            try (ResultSet resul = pstm.executeQuery()) {
                if (resul.next()) {
                    if (resul.getInt("repeticiones") > 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            throw e;
        }
        return false;
    }

    public boolean esAdministrador(int idUsuario) throws SQLException {
        String SQL = "SELECT COUNT(*) as repeticiones FROM Gestores WHERE ID_Usuario = ? AND es_administrador = true GROUP BY ID_Usuario";
        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(SQL);) {
            pstm.setInt(1, idUsuario);
            try (ResultSet resul = pstm.executeQuery()) {
                if (resul.next()) {
                    if (resul.getInt("repeticiones") > 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            throw e;
        }
        return false;
    }

    @Override
    public void close() throws Exception {
        con.close();
    }

}
