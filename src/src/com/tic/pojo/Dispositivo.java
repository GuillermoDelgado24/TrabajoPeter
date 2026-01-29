/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.com.tic.pojo;

/**
 *
 * @author tarde
 */
public class Dispositivo {
    private int idDispositivo;
    private int idEspacio;
    private String descripcion;
    private String marca;
    private String modelo;

    public Dispositivo(int idDispositivo, int idEspacio, String descripcion, String marca, String modelo) {
        this.idDispositivo = idDispositivo;
        this.idEspacio = idEspacio;
        this.descripcion = descripcion;
        this.marca = marca;
        this.modelo = modelo;
    }

    public Dispositivo(int idEspacio, String descripcion, String marca, String modelo) {
        this.idEspacio = idEspacio;
        this.descripcion = descripcion;
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(int idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public int getIdEspacio() {
        return idEspacio;
    }

    public void setIdEspacio(int idEspacio) {
        this.idEspacio = idEspacio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
}
