/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.com.tic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import src.com.tic.pojo.Incidencia;
import src.com.tic.utils.Configuration;
import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author alumno
 */
public class UsuarioDAOimpl implements UsuarioDAO, AutoCloseable {

    static {
        try {
            Class.forName(Configuration.DRIVER);
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
            System.exit(1);
        }
    }

    Connection con = null;

    @Override
    public ArrayList<Incidencia> getIncidenciasUsuario(int idUsuario) throws Exception {
        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
        int idIncidencia;
        String estado;
        String resultado_cierre;
        LocalDate fechaCierre;
        LocalDate fechaEntrada;
        String tipoIncidencia;
        String descripcionIncidencia;
        String descripcionSolucion;
        int idTecnico;
        String sql = "SELECT ID_Incidencia, estado, resultado_cierre, f_cierre, f_entrada, tipo_incidencia, ID_Usuario, ID_Tecnico, descripcion_incidencia, descripcion_solucion FROM Incidencias WHERE ID_Usuario = ?";

        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.User, Configuration.Password); PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setInt(1, idUsuario);
            try (ResultSet resul = pstm.executeQuery();) {
                while (resul.next()) {
                    idIncidencia = resul.getInt("ID_Incidencia");
                    estado = resul.getString("estado");
                    resultado_cierre = resul.getString("resultado_cierre");
                    fechaCierre = resul.getDate("f_cierre").toLocalDate();
                    fechaEntrada = resul.getDate("f_entrada").toLocalDate();
                    tipoIncidencia = resul.getString("tipo_incidencia");
                    descripcionIncidencia = resul.getString("descripcion_incidencia");
                    descripcionSolucion = resul.getString("descripcion_solucion");
                    idTecnico = resul.getInt("ID_Tecnico");
                    incidencias.add(new Incidencia(idIncidencia, estado, resultado_cierre, fechaCierre, fechaEntrada, tipoIncidencia, descripcionIncidencia, descripcionSolucion, idUsuario, idTecnico));

                }
            }
        } catch (Exception e) {
            throw e;
        }
        return incidencias;
    }

    @Override
    public Incidencia getIncidenciaPorId(int idIncidencia, int idUsuario) throws Exception {
        Incidencia incidencia = null;
        String estado;
        String resultado_cierre;
        LocalDate fechaCierre;
        LocalDate fechaEntrada;
        String tipoIncidencia;
        String descripcionIncidencia;
        String descripcionSolucion;
        int idTecnico;
        String sql = "SELECT ID_Incidencia, estado, resultado_cierre, f_cierre, f_entrada, tipo_incidencia, ID_Usuario, ID_Tecnico, descripcion_incidencia, descripcion_solucion FROM Incidencias WHERE ID_Incidencia = ? AND ID_Usuario = ?";

        try (Connection con = DriverManager.getConnection(Configuration.URL,Configuration.User, Configuration.Password); PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setInt(1, idIncidencia);
            pstm.setInt(2, idUsuario);
            try (ResultSet resul = pstm.executeQuery();) {
                estado = resul.getString("estado");
                resultado_cierre = resul.getString("resultado_cierre");
                fechaCierre = resul.getDate("f_cierre").toLocalDate();
                fechaEntrada = resul.getDate("f_entrada").toLocalDate();
                tipoIncidencia = resul.getString("tipo_incidencia");
                descripcionIncidencia = resul.getString("descripcion_incidencia");
                descripcionSolucion = resul.getString("descripcion_solucion");
                idUsuario = resul.getInt("ID_Usuario");
                idTecnico = resul.getInt("ID_Tecnico");
                incidencia = new Incidencia(idIncidencia, estado, resultado_cierre, fechaCierre, fechaEntrada, tipoIncidencia, descripcionIncidencia, descripcionSolucion, idUsuario, idTecnico);
            }
        } catch (Exception e) {
            throw e;
        }
        return incidencia;
    }

    @Override
    public boolean solicitarReapertura(int idIncidencia) throws SQLException {
        int r = 0;
        String SQL = "UPDATE Incidencias "
                + "SET estado = NULL, "
                + "resultado_cierre = NULL "
                + "WHERE ID_Incidencia = ?;";

        try (Connection con = DriverManager.getConnection(Configuration.URL); PreparedStatement pstm = con.prepareStatement(SQL);) {
            pstm.setInt(1, idIncidencia);
            r = pstm.executeUpdate();
            if (r > 0) {
                System.out.println("Registros afectados: " + r);
            }
        } catch (SQLException e) {
            throw e;
        }
        if (r == 0) {
            return false;
        }
        return true;
    }

    public LinkedHashMap<Integer, String> obtenerEspacios() throws Exception {
        LinkedHashMap<Integer,String> map = new LinkedHashMap();
        String SQL = "SELECT ID_Espacio, Descripcion FROM Espacios;";
        try(Connection con = DriverManager.getConnection(Configuration.URL); PreparedStatement pstm = con.prepareStatement(SQL);ResultSet resul = pstm.executeQuery();) {
            while (resul.next()) {                
                 map.put(resul.getInt("ID_Espacio"), resul.getString("Descripcion"));
            }
        } catch (Exception e) {
            throw e;
        }
        return map;
    }

    @Override
    public void crearIncidencia(Incidencia incidencia, int idEspacio) throws Exception {
        String SQL = "INSERT INTO Incidencias (f_entrada, tipo_incidencia, ID_Usuario, descripcion_incidencia) VALUES (CURDATE(), ?, ?, ?);";
        String SQL2 = "INSERT INTO Incidencias_Espacios (ID_Espacio,ID_Incidencia) VALUES (?, ?);";

        try (Connection con = DriverManager.getConnection(Configuration.URL); PreparedStatement pstm = con.prepareStatement(SQL); PreparedStatement pstm2 = con.prepareStatement(SQL2)) {
            pstm.setString(1, incidencia.getTipoIncidencia());
            pstm.setInt(2, incidencia.getIdUsuario());
            pstm.setString(3, incidencia.getDescripcionIncidencia());

            pstm.executeUpdate();

            pstm2.setInt(1, idEspacio);
            pstm2.setInt(2, incidencia.getIdIncidencia());

            pstm2.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void close() throws Exception {
        con.close();
    }

}
