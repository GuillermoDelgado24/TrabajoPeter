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
import src.com.tic.pojo.Usuario;
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

    public void ponerEnEspera(int idIncidencia) throws Exception {
        String SQL = "UPDATE Incidencias "
                + "SET estado = NULL, "
                + "resultado_cierre = NULL "
                + "WHERE ID_Incidencia = ?;";
        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(SQL);) {
            pstm.setInt(1, idIncidencia);
            int r = pstm.executeUpdate();
            if (r > 0) {
                System.out.println("Registros afectados: " + r);
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public void asignarTecnico(int idTecnico, int idIncidencia) throws Exception {

        String SQL = "UPDATE Incidencias "
                + "SET estado = 'asignada', "
                + "ID_Tecnico = ? ,"
                + "resultado_cierre = NULL "
                + "WHERE ID_Incidencia = ?;";

        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(SQL);) {
            pstm.setInt(1, idTecnico);
            pstm.setInt(2, idIncidencia);
            int r = pstm.executeUpdate();
            if (r > 0) {
                System.out.println("Registros afectados: " + r);
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public void altaIncidencias(Incidencia incidencia) throws Exception {
        String SQL = "UPDATE Incidencias "
                + "SET estado = 'alta', "
                + "resultado_cierre = NULL "
                + "WHERE ID_Incidencia = ?;";
        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(SQL);) {
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

        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(sql); ResultSet resul = pstm.executeQuery();) {
            while (resul.next()) {
                incidencias.add(new Incidencia(resul.getInt("ID_Incidencia"), resul.getString("estado"), resul.getString("resultado_cierre"), resul.getDate("f_cierre"), resul.getDate("f_entrada"), resul.getString("tipo_incidencia"), resul.getString("descripcion_incidencia"), resul.getString("descripcion_solucion"), resul.getInt("ID_Usuario"), resul.getInt("ID_Tecnico")));
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

        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(sql); ResultSet resul = pstm.executeQuery();) {
            while (resul.next()) {
                incidencias.add(new Incidencia(resul.getInt("ID_Incidencia"), resul.getString("estado"), resul.getString("resultado_cierre"), resul.getDate("f_cierre"), resul.getDate("f_entrada"), resul.getString("tipo_incidencia"), resul.getString("descripcion_incidencia"), resul.getString("descripcion_solucion"), resul.getInt("ID_Usuario"), resul.getInt("ID_Tecnico")));
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

        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setString(1, tipoIncidencia);
            try (ResultSet resul = pstm.executeQuery();) {
                while (resul.next()) {
                    incidencias.add(new Incidencia(resul.getInt("ID_Incidencia"), resul.getString("estado"), resul.getString("resultado_cierre"), resul.getDate("f_cierre"), resul.getDate("f_entrada"), tipoIncidencia, resul.getString("descripcion_incidencia"), resul.getString("descripcion_solucion"), resul.getInt("ID_Usuario"), resul.getInt("ID_Tecnico")));
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

        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setInt(1, idTecnico);
            try (ResultSet resul = pstm.executeQuery();) {
                while (resul.next()) {
                    incidencias.add(new Incidencia(resul.getInt("ID_Incidencia"), resul.getString("estado"), resul.getString("resultado_cierre"), resul.getDate("f_cierre"), resul.getDate("f_entrada"), resul.getString("tipo_incidencia"), resul.getString("descripcion_incidencia"), resul.getString("descripcion_solucion"), resul.getInt("ID_Usuario"), resul.getInt("ID_Tecnico")));
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

    public Usuario getTecnicoById(int idTecnico) throws Exception {
        Usuario tecnico = null;
        String sql = "SELECT tec.ID_Tecnico, user.ID_Usuario, user.nombre_usuario, user.nombre_apellidos, user.correo_electronico, user.telefono FROM Usuarios user RIGHT JOIN Tecnicos tec ON user.ID_Usuario = tec.ID_Usuario WHERE tec.ID_Tecnico = ?";
        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, idTecnico);
            try (ResultSet resul = pstm.executeQuery()) {
                if (resul.next()) {
                    tecnico = new Usuario(resul.getInt("user.ID_Usuario"), resul.getString("user.nombre_usuario"), resul.getString("user.nombre_apellidos"), resul.getString("user.correo_electronico"), resul.getString("user.telefono"), null);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return tecnico;
    }

    @Override
    public ArrayList<Integer> getTecnicoDisp() throws Exception {
        ArrayList<Integer> idTecnicos = new ArrayList<Integer>();
        String sql = "Select tec.ID_Tecnico, count(*)  as IncidenciasAsignadas from Tecnicos as tec  join Incidencias as ini on ini.ID_Tecnico = tec.ID_Tecnico group by(ini.ID_Tecnico) having IncidenciasAsignadas < 5;";
        try (Connection con = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD); PreparedStatement pstm = con.prepareStatement(sql)) {
            try (ResultSet resul = pstm.executeQuery()) {
                while (resul.next()) {
                    idTecnicos.add(resul.getInt("tec.ID_Tecnico"));
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return idTecnicos;
    }
}

//Select ID_Tecnico, ID_Usuario, us.nombre_usuario, us.nombre_apellidos, us.correo_electronico,us.telefono from Tecnico as te join Usuarios as us on te.ID_Usuario=us.ID_Usuario join Incidencias as in on te.ID_Tecnico=in.ID_Tecnico group by(in.ID_Tecnico)
