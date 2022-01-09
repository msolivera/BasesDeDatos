/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesosBD;

/**
 *
 * @author Usuario
 */
public class AuditoriaRW {
    
    public static final String GET_AUDITORIA = "SELECT * FROM auditoria_y_eventos";
    public static final String FILTRAR_AUDITORIA = "SELECT * FROM auditoria_y_eventos where fecha = ? OR usuario_solicitante = ? OR descripcion = ? OR usuario_afectado = ? OR rol_afectado = ?";
    public static final String INSERTAR_FILA = "insert into auditoria (fecha,id_usuario_realiza, id_tipo_evento, id_usuario, id_rol) values (now(),?,?,?,?)";

    
}
