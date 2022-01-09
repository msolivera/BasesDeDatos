/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import accesosBD.Configuracion;
import accesosBD.UsuarioRW;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import accesosBD.CryptWithMD5;

/**
 *
 * @author Usuario
 */
public class AdministracionUsuarios {

    public static boolean usuarioYContraseñaExisten(String usuario, String password) throws SQLException, ClassNotFoundException {
        Connection con = Configuracion.getConnection();
        String sql = UsuarioRW.GET_USUARIOYCONTRASENA;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, usuario);
        stmt.setString(2, CryptWithMD5.cryptWithMD5(password));
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }
    
    public static Integer getCantLogueosFallidos(String id) throws SQLException, ClassNotFoundException{
        int res = -1;
        Connection con = Configuracion.getConnection();
        String sql = UsuarioRW.GET_INTENTOS_FALLIDOS;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            res = Integer.valueOf(rs.getString("intentos_logueo_fallido"));
        }
        return res;
    }
    
    public static void updateLogsFallidos(String id, Integer cantidad) throws SQLException, ClassNotFoundException{
        Connection con = Configuracion.getConnection();
        String sql = UsuarioRW.UPDATE_INTENTOS_FALLIDOS;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, cantidad);
        stmt.setString(2, id);
        stmt.executeUpdate();
    }
    
    public static void updateHabilitado(String id, boolean cantidad) throws SQLException, ClassNotFoundException{
        Connection con = Configuracion.getConnection();
        String sql = UsuarioRW.UPDATE_HABILITADO;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setBoolean(1, cantidad);
        stmt.setString(2, id);
        stmt.executeUpdate();
    }

    public static boolean usuarioExiste(String usuario) throws SQLException, ClassNotFoundException {
        Connection con = Configuracion.getConnection();
        String sql = UsuarioRW.GET_USUARIO;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, usuario);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
       
    }
    
    public static void insertarUsuario(String idUsuario, String contrasena, String ci)
            throws SQLException, ClassNotFoundException {
        Connection con = Configuracion.getConnection();
        
        
        Date date = java.sql.Date.valueOf(java.time.LocalDate.now());
        String sql = UsuarioRW.INSERTAR_USUARIO;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, idUsuario);
        stmt.setString(2, CryptWithMD5.cryptWithMD5(contrasena));
        stmt.setDate(3, date);
        stmt.setNull(4, 0);
        stmt.setInt(5, Integer.parseInt(ci));
        stmt.setInt(6, 0);
        stmt.setBoolean(7, false);
        stmt.executeUpdate();
        //armo la solicitud para ser aprobada y que el usuario quede habilitado 
        AdministracionSolicitud.insertarSolicitudHabilitarUsuarioNuevo(idUsuario, date);
    }

    public static void editarNombreUsuario(String id_usuario, String id_usuario_nuevo) throws SQLException, ClassNotFoundException {
        Connection con = Configuracion.getConnection();
        String sql = UsuarioRW.EDITAR_USUARIO;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(2, id_usuario);
        stmt.setString(1, id_usuario_nuevo);
        stmt.executeUpdate();
    }
    public static void editarContrasenaUsuario(String id_usuario, String contrasena) throws SQLException, ClassNotFoundException {
        Connection con = Configuracion.getConnection();
        String sql = UsuarioRW.EDITAR_CONTRASENA;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(2, id_usuario);
        stmt.setString(1, CryptWithMD5.cryptWithMD5(contrasena));
        stmt.executeUpdate();
    }
    public static void editarRolUsuario(String id_usuario, int rol) throws SQLException, ClassNotFoundException {
        Connection con = Configuracion.getConnection();
        String sql = UsuarioRW.SET_ROL;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(2, id_usuario);
        stmt.setInt(1, rol);
        stmt.executeUpdate();
    }

    public static void eliminarUsuario(String id_usuario) throws SQLException, ClassNotFoundException {
        Connection con = Configuracion.getConnection();
        String sql = UsuarioRW.ELIMINAR_USUARIO;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, id_usuario);
        stmt.executeUpdate();
    }

    public static void cargarTablaUsuarios(String id_usuario, String ci, String fecha, String descripcion_rol, String nombre_aplicacion, String habilitado, JTable tabla) throws SQLException, ClassNotFoundException {
        try (Connection con = Configuracion.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(UsuarioRW.filtrarUsuarios(id_usuario, ci, fecha, descripcion_rol, nombre_aplicacion, habilitado));
            ResultSet rs = stmt.executeQuery();
            insertarDatos(tabla, rs);
        }
    }

    private static void insertarDatos(JTable tabla, ResultSet rs) throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);

        modelo.addColumn("Usuario");
        modelo.addColumn("CI");
        modelo.addColumn("Fecha");
        modelo.addColumn("Rol");
        modelo.addColumn("Aplicacion");
        modelo.addColumn("Habilitado");

        while (rs.next()) {
            Object[] fila = new Object[6];
            for (int i = 0; i < 6; i++) {
                fila[i] = rs.getObject(i + 1);
            }
            modelo.addRow(fila);
        }
    }

    public static boolean usuarioIsHabilitado(String usuario) throws SQLException, ClassNotFoundException {
        Connection con = Configuracion.getConnection();
        String sql = UsuarioRW.USUARIO_IS_HABILITADO;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, usuario);
        ResultSet rs = stmt.executeQuery();
        rs.next();
       return rs.getBoolean(1);
    }

    public static boolean usuarioIsAdmin(String usuario) throws SQLException, ClassNotFoundException {
        Connection con = Configuracion.getConnection();
        String sql = UsuarioRW.USUARIO_IS_ADMIN;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, usuario);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
       
    }

    public static int GetCI(String usuario) throws SQLException, ClassNotFoundException {
        int ci = -1;
        try (Connection con = Configuracion.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(UsuarioRW.GET_CI);
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ci = rs.getInt("ci");
            }
            return ci;
        }
    }
    public static int GetRol(String usuario) throws SQLException, ClassNotFoundException {
        int id_rol = -1;
        try (Connection con = Configuracion.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(UsuarioRW.GET_ROL);
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                id_rol = rs.getInt("id_rol");
            }
            return id_rol;
        }
    }
    
     public static void Habilitar(String id_usuario) throws SQLException, ClassNotFoundException {
        Connection con = Configuracion.getConnection();
        String sql = UsuarioRW.USUARIO_HABILITAR;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, id_usuario);
        stmt.executeUpdate();
    }

}
