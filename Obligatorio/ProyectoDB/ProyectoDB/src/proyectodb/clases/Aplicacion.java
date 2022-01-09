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
class Aplicacion {
    
    protected int idApp;
    public String descripcion;

    public Aplicacion(int idApp, String descripcion) {
        this.idApp = idApp;
        this.descripcion = descripcion;
    }

    public int getIdApp() {
        return idApp;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
}
