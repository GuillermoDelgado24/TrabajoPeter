/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.com.tic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import src.com.tic.pojo.Dispositivo;
import src.com.tic.pojo.Espacio;
import src.com.tic.pojo.Usuario;
import src.com.tic.utils.Configuration;

/**
 *
 * @author alumno
 */
public class AdministradorDAOimpl implements AdministradorDAO, AutoCloseable {

    static {
        try {
            Class.forName(Configuration.DRIVER);
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
            System.exit(1);
        }
    }
    Connection con = null;

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

    @Override
    public ArrayList<Dispositivo> getDispositivos() throws Exception {
        ArrayList<Dispositivo> dispositivos = new ArrayList<Dispositivo>();

        String sql = "SELECT ID_Dispositivo, ID_Espacio, tipo, descripcion, marca, modelo FROM Dispositivos;";

        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(sql);) {
            try (ResultSet resul = pstm.executeQuery();) {
                while (resul.next()) {
                    dispositivos.add(new Dispositivo(resul.getInt("ID_Dispositivo"), resul.getInt("ID_Espacio"), resul.getString("descripcion"), resul.getString("tipo"), resul.getString("marca"), resul.getString("modelo")));

                }
            }
        } catch (Exception e) {
            throw e;
        }
        return dispositivos;
    }

    @Override
    public void updateDispositivos(Dispositivo disp) throws Exception {
        String sql = "Update Dispositivos set tipo = ?, descripcion = ?, marca = ?, modelo = ? where ID_Dispositivo = ?;";
        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setString(1, disp.getTipo());
            pstm.setString(2, disp.getDescripcion());
            pstm.setString(3, disp.getMarca());
            pstm.setString(4, disp.getModelo());
            pstm.setInt(5, disp.getIdDispositivo());
            pstm.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void deleteDispositivos(int IdDispositivo) throws Exception {
        String sql = "delete from Dispositivos where ID_Dispositivo = ?";
        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setInt(1, IdDispositivo);
            pstm.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void insertDispositivos(Dispositivo disp) throws Exception {
        String sql = "Insert into Dispositivos (ID_Espacio, tipo, descripcion, marca, modelo) values(?,?,?,?,?);";
        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setInt(1, disp.getIdEspacio());
            pstm.setString(2, disp.getTipo());
            pstm.setString(3, disp.getDescripcion());
            pstm.setString(4, disp.getMarca());
            pstm.setString(5, disp.getModelo());
            pstm.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ArrayList<Espacio> getEspacios() throws Exception {
        ArrayList<Espacio> espacios = new ArrayList<>();
        String sql = "select ID_Espacio, Descripcion from Espacios;";
        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(sql);) {
            try (ResultSet resul = pstm.executeQuery();) {
                while (resul.next()) {
                    espacios.add(new Espacio(resul.getInt("ID_Espacio"), resul.getString("Descripcion")));

                }
            }
        } catch (Exception e) {
            throw e;
        }
        return espacios;

    }

    @Override
    public void updateEspacio(Espacio esp) throws Exception {
        String sql = "update Espacios set Descripcion = ? where ID_Espacio = ?;";
        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setString(1, esp.getDescripcion());
            pstm.setInt(2, esp.getIdEspacio());
            pstm.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void deleteEspacio(int IdDEspacio) throws Exception {
        String sql = "delete from Espacios where ID_Espacio = ?";
        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setInt(1, IdDEspacio);
            pstm.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void insertEspacios(Espacio esp) throws Exception {
        String sql = "insert into Espacios(Descripcion) values (?);";
        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setString(1, esp.getDescripcion());
            pstm.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ArrayList<Usuario> getUsuarios() throws Exception {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = "select ID_Usuario, nombre_usuario, nombre_apellidos, correo_electronico, telefono from Usuarios;";
        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(sql);) {
            try (ResultSet resul = pstm.executeQuery()) {
                while (resul.next()) {
                    usuarios.add(new Usuario(resul.getInt("ID_Usuario"), resul.getString("nombre_usuario"), resul.getString("nombre_apellidos"), resul.getString("correo_electronico"), resul.getString("telefono"), ""));
                }

            }
        } catch (Exception e) {
            throw e;

        }
        return usuarios;

    }

    @Override
    public void updateUsuario(Usuario usu) throws Exception {
        String sql = "update Usuarios set nombre_usuario = ?, nombre_apellidos = ?, correo_electronico = ?,telefono = ? where ID_Usuario = ?";
        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setString(1, usu.getNombreDeUsuario());
            pstm.setString(2, usu.getNombreApellidos());
            pstm.setString(3, usu.getCorreo());
            pstm.setString(4, usu.getNumeroTelefono());
            pstm.setInt(5, usu.getIdUsuario());
            pstm.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void deleteUsuario(int IdDUsuario) throws Exception {
        String sql = "delete from Usuarios where ID_Usuario = ?";
        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setInt(1, IdDUsuario);
            pstm.executeUpdate();
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public void insertUsuarios(Usuario usu) throws Exception {
        
    }

    @Override
    public ArrayList<Usuario> getTipoUsuarioBasico() throws Exception {
        ArrayList<Usuario> usuarios = new ArrayList();
        String sql = "select u.ID_Usuario, u.nombre_usuario,u.nombre_apellidos,u.correo_electronico,u.telefono from Usuarios as u left join Tecnicos as t on u.ID_Usuario = t.ID_Usuario left join Gestores as g on u.ID_Usuario = g.ID_Usuario where t.ID_Tecnico is null and ID_Gestor is null;";
        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(sql);) {
            try (ResultSet resul = pstm.executeQuery()) {
                while (resul.next()) {
                    usuarios.add(new Usuario(resul.getInt("ID_Usuario"), resul.getString("nombre_usuario"), resul.getString("nombre_apellidos"), resul.getString("correo_electronico"), resul.getString("telefono"), ""));
                }

            }
        } catch (Exception e) {
            throw e;

        }
        return usuarios;
    }

    @Override
    public ArrayList<Usuario> getTipoUsuarioTecnico() throws Exception {
        ArrayList<Usuario> usuarios = new ArrayList();
        String sql = "select u.ID_Usuario, u.nombre_usuario,u.nombre_apellidos,u.correo_electronico,u.telefono from Usuarios as u right join Tecnicos as t on u.ID_Usuario = t.ID_Usuario;";
        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(sql);) {
            try (ResultSet resul = pstm.executeQuery()) {
                while (resul.next()) {
                    usuarios.add(new Usuario(resul.getInt("ID_Usuario"), resul.getString("nombre_usuario"), resul.getString("nombre_apellidos"), resul.getString("correo_electronico"), resul.getString("telefono"), ""));
                }

            }
        } catch (Exception e) {
            throw e;

        }
        return usuarios;
    }

    @Override
    public ArrayList<Usuario> getTipoUsuarioGestor() throws Exception {
        ArrayList<Usuario> usuarios = new ArrayList();
        String sql = "select u.ID_Usuario, u.nombre_usuario,u.nombre_apellidos,u.correo_electronico,u.telefono from Usuarios as u right join Gestores as t on u.ID_Usuario = t.ID_Usuario;";
        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(sql);) {
            try (ResultSet resul = pstm.executeQuery()) {
                while (resul.next()) {
                    usuarios.add(new Usuario(resul.getInt("ID_Usuario"), resul.getString("nombre_usuario"), resul.getString("nombre_apellidos"), resul.getString("correo_electronico"), resul.getString("telefono"), ""));
                }

            }
        } catch (Exception e) {
            throw e;

        }
        return usuarios;
    }

    @Override
    public void close() throws Exception {
        con.close();
    }

}
