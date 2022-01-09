/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import accesosBD.Configuracion;
import accesosBD.PersonaRW;
import accesosBD.SolicitudRW;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Agustin
 */
public class AdministracionSolicitud {

    public static void insertarSolicitudHabilitarUsuarioNuevo(String usuario, Date fecha) {

        try {
            Connection con = Configuracion.getConnection();

            Date date = java.sql.Date.valueOf(java.time.LocalDate.now());
            String sql = SolicitudRW.INSERTAR_SOLICITUD;
            PreparedStatement stmt;
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "esperando");
            stmt.setDate(2, fecha);
            stmt.setDate(3, date);

            stmt.setInt(4, 1);
            stmt.setString(5, usuario);
            stmt.setString(6, usuario);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdministracionSolicitud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdministracionSolicitud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void insertarSolicitudCambiarRol(String usuario, String rol) {

        try {
            
            String descripcionRol = AdministracionRoles.getDescripcionDeUnRol(Integer.parseInt(rol));
            Connection con = Configuracion.getConnection();

            Date date = java.sql.Date.valueOf(java.time.LocalDate.now());
            String sql = SolicitudRW.INSERTAR_SOLICITUD;
            PreparedStatement stmt;
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "esperando");
            stmt.setDate(2, date);
            stmt.setDate(3, date);

            stmt.setInt(4, 2);//tipo de solicitud
            stmt.setString(5, usuario);
            stmt.setString(6, descripcionRol);//nuevo valor
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdministracionSolicitud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdministracionSolicitud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void cargarTablaSolicitud(String id_solicitud, String estado, String fecha_creacion, String fecha_actualizacion, String tipo_solicitud, String usuario_solicitante, String aplicacion, String rol, String usuario_autorizante, JTable tabla) throws SQLException, ClassNotFoundException {
        try (Connection con = Configuracion.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(SolicitudRW.filtrarSolicitud(id_solicitud, estado, fecha_creacion, fecha_actualizacion, tipo_solicitud, usuario_solicitante, aplicacion, rol, usuario_autorizante));
            ResultSet rs = stmt.executeQuery();
            insertarDatos(tabla, rs);
        }
    }

    private static void insertarDatos(JTable tabla, ResultSet rs) throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);

        modelo.addColumn("Id Solicitud");
        modelo.addColumn("Estado");
        modelo.addColumn("Fecha Creacion");
        modelo.addColumn("Ultima Actualizacion");
        modelo.addColumn("Tipo de Solicitud");
        modelo.addColumn("Usuario");
        modelo.addColumn("Aplicacion");
        modelo.addColumn("Rol Actual");
        modelo.addColumn("Autorizante");
        modelo.addColumn("Nuevo valor");

        while (rs.next()) {

            Object[] fila = new Object[10];
            for (int i = 0; i < 10; i++) {
                fila[i] = rs.getObject(i + 1);
            }
            modelo.addRow(fila);
        }
    }

    public static int get_tipo_solicitud(String id_solicitud) {
        int tipo = -1;
        try (Connection con = Configuracion.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(SolicitudRW.GET_TIPO_SOLICITUD);
            stmt.setInt(1, Integer.parseInt(id_solicitud));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tipo = rs.getInt("id_tipo_sol");
            }
            return tipo;
        } catch (SQLException ex) {
            Logger.getLogger(AdministracionSolicitud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdministracionSolicitud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tipo;
    }

    public static void autorizar(String id_solicitud, String id_usuario, String nuevo_valor) throws ClassNotFoundException, SQLException {
//cambia la solicitud y habilita el usuario o rol
        int tipo = get_tipo_solicitud(id_solicitud);
        switch (tipo) {
            case 1://nuevo usuario
                AdministracionUsuarios.Habilitar(id_usuario);
                //habilitar
                break;
            case 2://nmuevo rol
                //cambiar rol
                int idrol = AdministracionRoles.getIdRol(nuevo_valor);
                AdministracionUsuarios.editarRolUsuario(id_usuario, idrol);
                break;

            case 3://usuario bloqueado
                AdministracionUsuarios.Habilitar(id_usuario);
                //habilitar
                break;
            default:
                break;
        }
        Connection con = Configuracion.getConnection();
        String sql = SolicitudRW.APROBAR_SOLICITUD;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, Integer.parseInt(id_solicitud));
        stmt.executeUpdate();
        set_autorizante(id_solicitud);
    }

    public static void no_autorizar(String id_solicitud, String id_usuario) throws ClassNotFoundException, SQLException {
//cambia la solicitud y asigna el rol nulo o olimina el usuario
        int tipo = get_tipo_solicitud(id_solicitud);
        switch (tipo) {
            case 1:
                //eliminar
                Connection con = Configuracion.getConnection();
                String sql = SolicitudRW.QUITAR_SOLICITANTE;
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, id_usuario);
                stmt.setInt(2, Integer.parseInt(id_solicitud));

                stmt.executeUpdate();
                AdministracionUsuarios.eliminarUsuario(id_usuario);
                break;
            default:
                break;
        }
        Connection con = Configuracion.getConnection();
        String sql = SolicitudRW.NO_APROBAR_SOLICITUD;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, Integer.parseInt(id_solicitud));
        stmt.executeUpdate();
        set_autorizante(id_solicitud);
    }

    public static void insertarSolicitudHabilitarUsuarioBloqueado(String usuario) {

        try {
            Connection con = Configuracion.getConnection();

            Date date = java.sql.Date.valueOf(java.time.LocalDate.now());
            String sql = SolicitudRW.INSERTAR_SOLICITUD;
            PreparedStatement stmt;
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "esperando");
            stmt.setDate(2, date);
            stmt.setDate(3, date);

            stmt.setInt(4, 3);
            stmt.setString(5, usuario);
            stmt.setString(6, usuario);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdministracionSolicitud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdministracionSolicitud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void set_autorizante(String id_solicitud) throws SQLException, ClassNotFoundException {
        Connection con = Configuracion.getConnection();
        String sql = SolicitudRW.SET_AUTORIZANTE;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, Configuracion.usuario);
        stmt.setInt(2, Integer.parseInt(id_solicitud));
        stmt.executeUpdate();
    }
}
