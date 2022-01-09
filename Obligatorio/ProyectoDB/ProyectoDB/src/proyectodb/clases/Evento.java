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
public class Evento {
   
    protected int idEvento;
    public String descripcion;

    public Evento(int idEvento, String descripcion) {
        this.idEvento = idEvento;
        this.descripcion = descripcion;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
}
