/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.com.tic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import src.com.tic.pojo.Incidencia;
import src.com.tic.utils.Configuration;
import java.sql.*;

/**
 *
 * @author alumno
 */
public class TecnicoDAOimpl implements TecnicoDAO, AutoCloseable {

    static {
        try {
            Class.forName(Configuration.DRIVER);
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
            System.exit(1);
        }
    }

    Connection con = null;

    public int hallarIdTecnico(int idUsuario) throws Exception {
        int idTecnico = -1;
        String SQL = "SELECT ID_Tecnico FROM Tecnicos WHERE ID_Usuario = ?";
        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(SQL);) {
            pstm.setInt(1, idUsuario);
            try (ResultSet resul = pstm.executeQuery()){
                if (resul.next()) {
                    idTecnico = resul.getInt("ID_Tecnico");
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
            throw e;
        }
        return idTecnico;
    }

    @Override
    public void atenderIncidencia(int idIncidencia) throws Exception {
        int r = 0;
        String SQL = "UPDATE Incidencias "
                + "SET estado = 'en curso', "
                + "resultado_cierre = NULL "
                + "WHERE ID_Incidencia = ?;";

        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(SQL);) {
            pstm.setInt(1, idIncidencia);
            r = pstm.executeUpdate();
//            if (r > 0) {
//                System.out.println("Registros afectados: " + r);
//            }
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public void cerrarIncidencia(Incidencia incidencia) throws Exception {
        int r = 0;
        String SQL = "UPDATE Incidencias "
                + "SET estado = 'cerrada', "
                + "f_cierre = CURDATE(), "
                + "ID_Tecnico = NULL, "
                + "resultado_cierre = ?, "
                + "descripcion_solucion = ? "
                + "WHERE ID_Incidencia = ?;";

        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(SQL);) {
            pstm.setString(1, incidencia.getResultado_cierre());
            pstm.setString(2, incidencia.getDescripcionSolucion());
            pstm.setInt(3, incidencia.getIdIncidencia());
            r = pstm.executeUpdate();
//            if (r > 0) {
//                System.out.println("Registros afectados: " + r);
//            }
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public void agregarTipoIncidencia(String tipoIncidencia) throws Exception {
        String SQL_INSERT = "INSERT INTO Tipos_Incidencias (tipo_incidencia) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = conn.prepareStatement(SQL_INSERT)) {
            pstm.setString(1, tipoIncidencia);

            int insertados = pstm.executeUpdate();
//            System.out.println("Tipos_Incidencias insertados con éxito[" + insertados + "]");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ArrayList<Incidencia> getIncidenciasAsignadas(int idTecnico) throws Exception {
        ArrayList<Incidencia> al = new ArrayList<>();
        int idIncidencia;
        String estado;
        String resultado_cierre;
        Date fechaCierre;
        Date fechaEntrada;
        String tipoIncidencia;
        String descripcionIncidencia;
        String descripcionSolucion;
        int idUsuario;
        String SQL = "SELECT ID_Incidencia, estado, resultado_cierre, f_cierre, f_entrada, tipo_incidencia, ID_Usuario, ID_Tecnico, descripcion_incidencia, descripcion_solucion FROM Incidencias WHERE ID_Tecnico = ? AND (estado = 'asignada' OR estado = 'en curso')";
        try (Connection conn = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = conn.prepareStatement(SQL)) {
            pstm.setInt(1, idTecnico);
            try (ResultSet resul = pstm.executeQuery();) {
                while (resul.next()) {
                    idIncidencia = resul.getInt("ID_Incidencia");
                    estado = resul.getString("estado");
                    resultado_cierre = resul.getString("resultado_cierre");
                    fechaCierre = resul.getDate("f_cierre");
                    fechaEntrada = resul.getDate("f_entrada");
                    tipoIncidencia = resul.getString("tipo_incidencia");
                    descripcionIncidencia = resul.getString("descripcion_incidencia");
                    descripcionSolucion = resul.getString("descripcion_solucion");
                    idUsuario = resul.getInt("ID_Usuario");
                    al.add(new Incidencia(idIncidencia, estado, resultado_cierre, fechaCierre, fechaEntrada, tipoIncidencia, descripcionIncidencia, descripcionSolucion, idUsuario, idTecnico));

                }
            }
        } catch (Exception e) {
            throw e;
        }
        return al;
    }

    @Override
    public ArrayList<Incidencia> getIncidenciasByTipo(String tipoIncidencia) throws Exception {
        ArrayList<Incidencia> al = new ArrayList<>();
        int idIncidencia;
        String estado;
        String resultado_cierre;
        Date fechaCierre;
        Date fechaEntrada;
        String descripcionIncidencia;
        String descripcionSolucion;
        int idUsuario;
        int idTecnico;

        String SQL = "SELECT ID_Incidencia, estado, resultado_cierre, f_cierre, f_entrada, tipo_incidencia, ID_Usuario, ID_Tecnico, descripcion_incidencia, descripcion_solucion FROM Incidencias WHERE tipo_incidencia = ?";
        try (Connection conn = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = conn.prepareStatement(SQL)) {
            pstm.setString(1, tipoIncidencia);
            try (ResultSet resul = pstm.executeQuery();) {
                while (resul.next()) {
                    idIncidencia = resul.getInt("ID_Incidencia");
                    estado = resul.getString("estado");
                    resultado_cierre = resul.getString("resultado_cierre");
                    fechaCierre = resul.getDate("f_cierre");
                    fechaEntrada = resul.getDate("f_entrada");
                    tipoIncidencia = resul.getString("tipo_incidencia");
                    descripcionIncidencia = resul.getString("descripcion_incidencia");
                    descripcionSolucion = resul.getString("descripcion_solucion");
                    idUsuario = resul.getInt("ID_Usuario");
                    idTecnico = resul.getInt("ID_Tecnico");

                    al.add(new Incidencia(idIncidencia, estado, resultado_cierre, fechaCierre, fechaEntrada, tipoIncidencia, descripcionIncidencia, descripcionSolucion, idUsuario, idTecnico));
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return al;
    }
    
    @Override
    public ArrayList<Incidencia> getIncidencias() throws Exception {
        ArrayList<Incidencia> al = new ArrayList<>();
        int idIncidencia;
        String estado;
        String resultado_cierre;
        Date fechaCierre;
        Date fechaEntrada;
        String descripcionIncidencia;
        String descripcionSolucion;
        int idUsuario;
        int idTecnico;
        String tipoIncidencia;

        String SQL = "SELECT ID_Incidencia, estado, resultado_cierre, f_cierre, f_entrada, tipo_incidencia, ID_Usuario, ID_Tecnico, descripcion_incidencia, descripcion_solucion FROM Incidencias;";
        try (Connection conn = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = conn.prepareStatement(SQL)) {
            try (ResultSet resul = pstm.executeQuery();) {
                while (resul.next()) {
                    idIncidencia = resul.getInt("ID_Incidencia");
                    estado = resul.getString("estado");
                    resultado_cierre = resul.getString("resultado_cierre");
                    fechaCierre = resul.getDate("f_cierre");
                    fechaEntrada = resul.getDate("f_entrada");
                    tipoIncidencia = resul.getString("tipo_incidencia");
                    descripcionIncidencia = resul.getString("descripcion_incidencia");
                    descripcionSolucion = resul.getString("descripcion_solucion");
                    idUsuario = resul.getInt("ID_Usuario");
                    idTecnico = resul.getInt("ID_Tecnico");

                    al.add(new Incidencia(idIncidencia, estado, resultado_cierre, fechaCierre, fechaEntrada, tipoIncidencia, descripcionIncidencia, descripcionSolucion, idUsuario, idTecnico));
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return al;
    }

    @Override
    public ArrayList<Incidencia> getIncidenciasBetweenFechas(int Dias) throws Exception {
        ArrayList<Incidencia> al = new ArrayList<>();
        int idIncidencia;
        String estado;
        String resultado_cierre;
        Date fechaCierre;
        Date fechaEntrada;
        String descripcionIncidencia;
        String descripcionSolucion;
        String tipoIncidencia;
        int idUsuario;
        int idTecnico;

        String SQL = "SELECT ID_Incidencia, estado, resultado_cierre, f_cierre, f_entrada, tipo_incidencia, ID_Usuario, ID_Tecnico, descripcion_incidencia, descripcion_solucion FROM Incidencias WHERE TIMESTAMPDIFF(DAY,f_cierre,CURDATE()) <= ? AND estado = 'cerrada'";
        try (Connection conn = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = conn.prepareStatement(SQL)) {
            pstm.setInt(1, Dias);
            try (ResultSet resul = pstm.executeQuery();) {
                while (resul.next()) {
                    idIncidencia = resul.getInt("ID_Incidencia");
                    estado = resul.getString("estado");
                    resultado_cierre = resul.getString("resultado_cierre");
                    fechaCierre = resul.getDate("f_cierre");
                    fechaEntrada = resul.getDate("f_entrada");
                    tipoIncidencia = resul.getString("tipo_incidencia");
                    descripcionIncidencia = resul.getString("descripcion_incidencia");
                    descripcionSolucion = resul.getString("descripcion_solucion");
                    idUsuario = resul.getInt("ID_Usuario");
                    idTecnico = resul.getInt("ID_Tecnico");
                    al.add(new Incidencia(idIncidencia, estado, resultado_cierre, fechaCierre, fechaEntrada, tipoIncidencia, descripcionIncidencia, descripcionSolucion, idUsuario, idTecnico));
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return al;
    }

    @Override
    public void close() throws Exception {
        con.close();
    }

}
