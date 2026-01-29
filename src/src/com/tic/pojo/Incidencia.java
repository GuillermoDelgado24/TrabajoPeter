/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.com.tic.pojo;

import java.util.Date;

/**
 *
 * @author alumno
 */
public class Incidencia {

    private int idIncidencia;
    private String estado;
    private String resultado_cierre;
    private Date fechaCierre;
    private Date fechaEntrada;
    private String tipoIncidencia;
    private int idUsuario;
    private int idTecnico;

    public Incidencia(String estado, String resultado_cierre, Date fechaCierre, Date fechaEntrada, String tipoIncidencia, int idUsuario, int idTecnico) {
        this.estado = estado;
        this.resultado_cierre = resultado_cierre;
        this.fechaCierre = fechaCierre;
        this.fechaEntrada = fechaEntrada;
        this.tipoIncidencia = tipoIncidencia;
        this.idUsuario = idUsuario;
        this.idTecnico = idTecnico;
    }

    public Incidencia(int idIncidencia, String estado, String resultado_cierre, Date fechaCierre, Date fechaEntrada, String tipoIncidencia, int idUsuario, int idTecnico) {
        this.idIncidencia = idIncidencia;
        this.estado = estado;
        this.resultado_cierre = resultado_cierre;
        this.fechaCierre = fechaCierre;
        this.fechaEntrada = fechaEntrada;
        this.tipoIncidencia = tipoIncidencia;
        this.idUsuario = idUsuario;
        this.idTecnico = idTecnico;
    }

    public int getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico;
    }

    public int getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getResultado_cierre() {
        return resultado_cierre;
    }

    public void setResultado_cierre(String resultado_cierre) {
        this.resultado_cierre = resultado_cierre;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getTipoIncidencia() {
        return tipoIncidencia;
    }

    public void setTipoIncidencia(String tipoIncidencia) {
        this.tipoIncidencia = tipoIncidencia;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Incidencia{" + "idIncidencia=" + idIncidencia + ", estado=" + estado + ", resultado_cierre=" + resultado_cierre + ", fechaCierre=" + fechaCierre + ", fechaEntrada=" + fechaEntrada + ", tipoIncidencia=" + tipoIncidencia + ", idUsuario=" + idUsuario + ", idTecnico=" + idTecnico + '}';
    }

}
