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
public class Auditoria {
    
    protected int idAuditoria;
    public String fecha;
    protected Usuario usuarioRealiza;
    public Evento evento;
    public Rol rolAudit;
    public Usuario usuarioAudit;

    public Auditoria(int idAuditoria, String fecha, Usuario usuarioRealiza, Evento evento) {
        this.idAuditoria = idAuditoria;
        this.fecha = fecha;
        this.usuarioRealiza = usuarioRealiza;
        this.evento = evento;
    }

    public int getIdAuditoria() {
        return idAuditoria;
    }

    public String getFecha() {
        return fecha;
    }

    public Usuario getUsuarioRealiza() {
        return usuarioRealiza;
    }

    public Evento getEvento() {
        return evento;
    }

    public Rol getRolAudit() {
        return rolAudit;
    }

    public void setRolAudit(Rol rolAudit) {
        this.rolAudit = rolAudit;
    }

    public void setUsuarioAudit(Usuario usuarioAudit) {
        this.usuarioAudit = usuarioAudit;
    }

    public Usuario getUsuarioAudit() {
        return usuarioAudit;
    }
    
    
    
}
