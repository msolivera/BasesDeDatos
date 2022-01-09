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
public class Solicitud {
    
    protected int idSolicitud;
    protected String estado;
    public String fechaSolicitud;
    public String fechaAutorizado;
    public String descripcion;
    public TipoSolicitud tipoSol;
    public Persona solicitante;
    public Usuario autorizante;

    public Solicitud(int idSolicitud, String fechaSolicitud, String descripcion, TipoSolicitud tipoSol, Persona solicitante) {
        this.idSolicitud = idSolicitud;
        this.fechaSolicitud = fechaSolicitud;
        this.descripcion = descripcion;
        this.tipoSol = tipoSol;
        this.solicitante = solicitante;
        this.estado = "pendiente";
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public String getEstado() {
        return estado;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public String getFechaAutorizado() {
        return fechaAutorizado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public TipoSolicitud getTipoSol() {
        return tipoSol;
    }

    public Persona getSolicitante() {
        return solicitante;
    }

    public Usuario getAutorizante() {
        return autorizante;
    }

    public void setFechaAutorizado(String fechaAutorizado) {
        this.fechaAutorizado = fechaAutorizado;
    }

    public void setAutorizante(Usuario autorizante) {
        this.autorizante = autorizante;
    }
    
    
    
    
    
    
}
