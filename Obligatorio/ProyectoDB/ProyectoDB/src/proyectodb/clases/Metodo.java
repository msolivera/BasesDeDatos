/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectodb.clases;

/**
 *
 * @author Micaela
 */
public class Metodo {
    protected int idMetodo;
    public String descripcion;

    public Metodo(int idMetodo, String descripcion) {
        this.idMetodo = idMetodo;
        this.descripcion = descripcion;
    }

    public int getIdMetodo() {
        return idMetodo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
}
