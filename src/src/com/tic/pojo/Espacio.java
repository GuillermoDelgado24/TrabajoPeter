/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.com.tic.pojo;

/**
 *
 * @author alumno
 */
public class Espacio {

    private int idEspacio;
    private String descripcion;

    public Espacio(String descripcion) {
        this.descripcion = descripcion;
    }

    public Espacio(int idEspacio, String descripcion) {
        this.idEspacio = idEspacio;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdEspacio() {
        return idEspacio;
    }

    public void setIdEspacio(int idEspacio) {
        this.idEspacio = idEspacio;
    }

    @Override
    public String toString() {
        return "Espacio{" + "idEspacio=" + idEspacio + ", descripcion=" + descripcion + '}';
    }

}
