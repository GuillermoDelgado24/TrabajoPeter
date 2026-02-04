/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.com.tic.dao;

import java.awt.List;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.Date;
import src.com.tic.pojo.Incidencia;
import src.com.tic.utils.Configuration;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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
        Date fechaCierre;
        Date fechaEntrada;
        String tipoIncidencia;
        String descripcionIncidencia;
        String descripcionSolucion;
        int idTecnico;
        String sql = "SELECT ID_Incidencia, estado, resultado_cierre, f_cierre, f_entrada, tipo_incidencia, ID_Usuario, ID_Tecnico, descripcion_incidencia, descripcion_solucion FROM Incidencias WHERE ID_Usuario = ?";

        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setInt(1, idUsuario);
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
                    idTecnico = resul.getInt("ID_Tecnico");

                    incidencias.add(new Incidencia(idIncidencia, estado, resultado_cierre, fechaCierre, fechaEntrada, tipoIncidencia, descripcionIncidencia, descripcionSolucion, idUsuario, idTecnico));

                }
            }
        } catch (Exception e) {
            throw e;
        }
        return incidencias;
    }

//    public Incidencia insertarEnIncidencia(LinkedList<Object> variable) {
//        LinkedList<Object> resultado = new LinkedList<Object>();
//        for (Object object : variable) {
//            if (object.getClass() == int.class) {
//                if (object == null) {
//                    resultado.add(-1);
//                }
//            } else if (object == null) {
//                resultado.add(null);
//            }
//        }
//        return new Incidencia((int) resultado.get(0), (String)resultado.get(1), (String)resultado.get(2), ((java.sql.Date)resultado.get(4)), (java.sql.Date)resultado.get(5), (String) resultado.get(6), (String)resultado.get(7), (String)resultado.get(8), (int)resultado.get(9), (int)resultado.get(10));
//    }
    @Override
    public Incidencia getIncidenciaPorId(int idIncidencia, int idUsuario) throws Exception {
        Incidencia incidencia = null;
        String estado;
        String resultado_cierre;
        Date fechaCierre;
        Date fechaEntrada;
        String tipoIncidencia;
        String descripcionIncidencia;
        String descripcionSolucion;
        int idTecnico;
        String sql = "SELECT ID_Incidencia, estado, resultado_cierre, f_cierre, f_entrada, tipo_incidencia, ID_Usuario, ID_Tecnico, descripcion_incidencia, descripcion_solucion FROM Incidencias WHERE ID_Incidencia = ? AND ID_Usuario = ?;";

        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setInt(1, idIncidencia);
            pstm.setInt(2, idUsuario);
            try (ResultSet resul = pstm.executeQuery()) {
                if (resul.next()) {
                    estado = resul.getString("estado");
                    resultado_cierre = resul.getString("resultado_cierre");
                    fechaCierre = resul.getDate("f_cierre");
                    fechaEntrada = resul.getDate("f_entrada");
                    tipoIncidencia = resul.getString("tipo_incidencia");
                    descripcionIncidencia = resul.getString("descripcion_incidencia");
                    descripcionSolucion = resul.getString("descripcion_solucion");
                    idUsuario = resul.getInt("ID_Usuario");
                    idTecnico = resul.getInt("ID_Tecnico");
                    incidencia = new Incidencia(idIncidencia, estado, resultado_cierre, fechaCierre, fechaEntrada, tipoIncidencia, descripcionIncidencia, descripcionSolucion, idUsuario, idTecnico);
                }
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

        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(SQL);) {
            pstm.setInt(1, idIncidencia);
            r = pstm.executeUpdate();
            if (r > 0) {
                System.out.println("Registros afectados: " + r);
                return true;

            }
        } catch (SQLException e) {
            throw e;
        }
        return false;
    }

    public ArrayList<String> getTiposIncidencias() throws Exception {
        ArrayList<String> al = new ArrayList<>();
        String SQL = "SELECT tipo_incidencia FROM Tipos_Incidencias;";
        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(SQL); ResultSet resul = pstm.executeQuery();) {
            while (resul.next()) {
                al.add(resul.getString("tipo_incidencia"));
            }
        } catch (Exception e) {
            throw e;
        }
        return al;
    }

    public LinkedHashMap<String, Integer> obtenerEspacios() throws Exception {
        LinkedHashMap<String, Integer> map = new LinkedHashMap();
        String SQL = "SELECT Descripcion, ID_Espacio FROM Espacios;";
        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(SQL); ResultSet resul = pstm.executeQuery();) {
            while (resul.next()) {
                map.put(resul.getString("Descripcion"), resul.getInt("ID_Espacio"));
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

        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS); PreparedStatement pstm2 = con.prepareStatement(SQL2)) {
            pstm.setString(1, incidencia.getTipoIncidencia());
            pstm.setInt(2, incidencia.getIdUsuario());
            pstm.setString(3, incidencia.getDescripcionIncidencia());

            pstm.executeUpdate();
            try (ResultSet rs = pstm.getGeneratedKeys()) {
                if (rs.next()) {
                    pstm2.setInt(1, idEspacio);
                    pstm2.setInt(2, rs.getInt(1));
                    pstm2.executeUpdate();

                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void close() throws Exception {
        con.close();
    }

}
