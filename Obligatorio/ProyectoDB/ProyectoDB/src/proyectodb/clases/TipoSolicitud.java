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
public class TipoSolicitud {
    
    protected int idSolicitud;
    public String descripcion;

    public TipoSolicitud(int idSolicitud, String descripcion) {
        this.idSolicitud = idSolicitud;
        this.descripcion = descripcion;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    
}
