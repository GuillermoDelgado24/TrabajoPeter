/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.com.tic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import src.com.tic.pojo.Incidencia;
import src.com.tic.pojo.Tecnico;
import src.com.tic.utils.Configuration;

/**
 *
 * @author alumno
 */
public class GestorDAOimpl implements GestorDAO, AutoCloseable {

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
    public boolean asignarTecnico(Tecnico tecnico, Incidencia incidencia) throws Exception {
        boolean resultado;
        int instanciasTecnico = -1;
        String SQL_Comprobacion = "SELECT COUNT(*) as instancias_tecnico FROM Incidencias WHERE ID_Tecnico = ?;";
        try (Connection con = DriverManager.getConnection(Configuration.URL); PreparedStatement pstm = con.prepareStatement(SQL_Comprobacion);) {
            pstm.setInt(1, tecnico.getIdTecnico());
            try (ResultSet resul = pstm.executeQuery();) {
                instanciasTecnico = resul.getInt("instancias_tecnico");
            }

        } catch (Exception e) {
            throw e;
        }
        String SQL;
        if (instanciasTecnico < 5 && instanciasTecnico >= 0) {
            SQL = "UPDATE Incidencias "
                    + "SET estado = 'asignada', "
                    + "resultado_cierre = NULL "
                    + "WHERE ID_Incidencia = ?;";
            resultado = true;
        } else {
            SQL = "UPDATE Incidencias "
                    + "SET estado = 'alta', "
                    + "resultado_cierre = NULL "
                    + "WHERE ID_Incidencia = ?;";
            resultado = false;
        }
        try (Connection con = DriverManager.getConnection(Configuration.URL); PreparedStatement pstm = con.prepareStatement(SQL);) {
            pstm.setInt(1, incidencia.getIdIncidencia());
            int r = pstm.executeUpdate();
            if (r > 0) {
                System.out.println("Registros afectados: " + r);
            }
        } catch (SQLException e) {
            throw e;
        }
        return resultado;
    }

    @Override
    public void altaIncidencias(Incidencia incidencia) throws Exception {
        String SQL = "UPDATE Incidencias "
                + "SET estado = 'alta', "
                + "resultado_cierre = NULL "
                + "WHERE ID_Incidencia = ?;";
        try (Connection con = DriverManager.getConnection(Configuration.URL); PreparedStatement pstm = con.prepareStatement(SQL);) {
            pstm.setInt(1, incidencia.getIdIncidencia());
            int r = pstm.executeUpdate();
            if (r > 0) {
                System.out.println("Registros afectados: " + r);
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public ArrayList<Incidencia> getIncidenciasByEspera() throws Exception {
        String sql = "SELECT ID_Incidencia, estado, resultado_cierre, f_cierre, f_entrada, tipo_incidencia, ID_Usuario, ID_Tecnico, descripcion_incidencia, descripcion_solucion FROM Incidencias WHERE estado = NULL OR estado = 'alta';";
        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();

        try (Connection con = DriverManager.getConnection(Configuration.URL); PreparedStatement pstm = con.prepareStatement(sql); ResultSet resul = pstm.executeQuery();) {
            while (resul.next()) {
                incidencias.add(new Incidencia(resul.getInt("ID_Incidencia"), resul.getString("estado"), resul.getString("resultado_cierre"), resul.getDate("f_cierre").toLocalDate(), resul.getDate("f_entrada").toLocalDate(), resul.getString("tipo_incidencia"), resul.getString("descripcion_incidencia"), resul.getString("descripcion_solucion"), resul.getInt("ID_Usuario"), resul.getInt("ID_Tecnico")));
            }

        } catch (Exception e) {
            throw e;
        }
        return incidencias;
    }

    @Override
    public ArrayList<Incidencia> getAllIncidencias() throws Exception {
        String sql = "SELECT ID_Incidencia, estado, resultado_cierre, f_cierre, f_entrada, tipo_incidencia, ID_Usuario, ID_Tecnico, descripcion_incidencia, descripcion_solucion FROM Incidencias;";
        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();

        try (Connection con = DriverManager.getConnection(Configuration.URL); PreparedStatement pstm = con.prepareStatement(sql); ResultSet resul = pstm.executeQuery();) {
            while (resul.next()) {
                incidencias.add(new Incidencia(resul.getInt("ID_Incidencia"), resul.getString("estado"), resul.getString("resultado_cierre"), resul.getDate("f_cierre").toLocalDate(), resul.getDate("f_entrada").toLocalDate(), resul.getString("tipo_incidencia"), resul.getString("descripcion_incidencia"), resul.getString("descripcion_solucion"), resul.getInt("ID_Usuario"), resul.getInt("ID_Tecnico")));
            }

        } catch (Exception e) {
            throw e;
        }
        return incidencias;
    }

    @Override
    public ArrayList<Incidencia> getIncidenciasByTipo(String tipoIncidencia) throws Exception {
        String sql = "SELECT ID_Incidencia, estado, resultado_cierre, f_cierre, f_entrada, tipo_incidencia, ID_Usuario, ID_Tecnico, descripcion_incidencia, descripcion_solucion FROM Incidencias WHERE tipo_incidencia = ?;";
        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();

        try (Connection con = DriverManager.getConnection(Configuration.URL); PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setString(1, tipoIncidencia);
            try (ResultSet resul = pstm.executeQuery();) {
                while (resul.next()) {
                    incidencias.add(new Incidencia(resul.getInt("ID_Incidencia"), resul.getString("estado"), resul.getString("resultado_cierre"), resul.getDate("f_cierre").toLocalDate(), resul.getDate("f_entrada").toLocalDate(), tipoIncidencia, resul.getString("descripcion_incidencia"), resul.getString("descripcion_solucion"), resul.getInt("ID_Usuario"), resul.getInt("ID_Tecnico")));
                }
            }

        } catch (Exception e) {
            throw e;
        }
        return incidencias;
    }

    @Override
    public ArrayList<Incidencia> getIncidenciasByTecnico(int idTecnico) throws Exception {
        String sql = "SELECT ID_Incidencia, estado, resultado_cierre, f_cierre, f_entrada, tipo_incidencia, ID_Usuario, ID_Tecnico, descripcion_incidencia, descripcion_solucion FROM Incidencias WHERER ID_Tecnnico = ?;";
        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();

        try (Connection con = DriverManager.getConnection(Configuration.URL); PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setInt(1, idTecnico);
            try (ResultSet resul = pstm.executeQuery();) {
                while (resul.next()) {
                    incidencias.add(new Incidencia(resul.getInt("ID_Incidencia"), resul.getString("estado"), resul.getString("resultado_cierre"), resul.getDate("f_cierre").toLocalDate(), resul.getDate("f_entrada").toLocalDate(), resul.getString("tipo_incidencia"), resul.getString("descripcion_incidencia"), resul.getString("descripcion_solucion"), resul.getInt("ID_Usuario"), resul.getInt("ID_Tecnico")));
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return incidencias;
    }

    @Override
    public void close() throws Exception {
        con.close();
    }
}
