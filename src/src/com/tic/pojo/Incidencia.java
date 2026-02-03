/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.com.tic.pojo;

import java.time.LocalDate;

/**
 *
 * @author alumno
 */
public class Incidencia {

    private int idIncidencia;
    private String estado;
    private String resultado_cierre;
    private LocalDate fechaCierre;
    private LocalDate fechaEntrada;
    private String tipoIncidencia;
    private String descripcionIncidencia;
    private String descripcionSolucion;
    private int idUsuario;
    private int idTecnico;

    public Incidencia(LocalDate fechaEntrada, String tipoIncidencia, String descripcionIncidencia, int idUsuario) {
        this.fechaEntrada = fechaEntrada;
        this.tipoIncidencia = tipoIncidencia;
        this.descripcionIncidencia = descripcionIncidencia;
        this.idUsuario = idUsuario;
    }

    public Incidencia(int idIncidencia, String estado, String resultado_cierre, LocalDate fechaCierre, LocalDate fechaEntrada, String tipoIncidencia, String descripcionIncidencia, String descripcionSolucion, int idUsuario, int idTecnico) {
        this.idIncidencia = idIncidencia;
        this.estado = estado;
        this.resultado_cierre = resultado_cierre;
        this.fechaCierre = fechaCierre;
        this.fechaEntrada = fechaEntrada;
        this.tipoIncidencia = tipoIncidencia;
        this.descripcionIncidencia = descripcionIncidencia;
        this.descripcionSolucion = descripcionSolucion;
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

    public LocalDate getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDate fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
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

    public String getDescripcionIncidencia() {
        return descripcionIncidencia;
    }

    public void setDescripcionIncidencia(String descripcionIncidencia) {
        this.descripcionIncidencia = descripcionIncidencia;
    }

    public String getDescripcionSolucion() {
        return descripcionSolucion;
    }

    public void setDescripcionSolucion(String descripcionSolucion) {
        this.descripcionSolucion = descripcionSolucion;
    }

    @Override
    public String toString() {
        return "Incidencia{" + "idIncidencia=" + idIncidencia + ", estado=" + estado + ", resultado_cierre=" + resultado_cierre + ", fechaCierre=" + fechaCierre + ", fechaEntrada=" + fechaEntrada + ", tipoIncidencia=" + tipoIncidencia + ", descripcionIncidencia=" + descripcionIncidencia + ", descripcionSolucion=" + descripcionSolucion + ", idUsuario=" + idUsuario + ", idTecnico=" + idTecnico + '}';
    }

}
